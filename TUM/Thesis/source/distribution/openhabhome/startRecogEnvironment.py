import time
import subprocess
import actionlib
import rospy, sys
from face_recognition.msg import FaceRecognitionActionResult
from face_recognition.msg import FaceRecognitionGoal
from face_recognition.msg import FaceRecognitionAction
from diagnostic_msgs.msg import KeyValue
import os


def recog(itemName, itemValue):
    #How long to sleep
    sleepTime = 4

    #Start the iot_bridge. Starts roscore too
    subprocess.Popen(["screen","-dmS", "roslaunchBridge", "roslaunch","iot_bridge","iot.launch"])
    #Wait for the server to start
    time.sleep(sleepTime)

    #Start the camera
    subprocess.Popen(["screen","-dmS", "roslaunchCamera", "roslaunch","/home/administrator/catkin_ws/start-cam.launch"])
    #wait for the camera to start
    time.sleep(sleepTime)

    #Set the confidence_value for the face recognition server.No need to wait
    subprocess.Popen(["rosparam" ,"set", "/face_recognition/confidence_value", "0.80"])
    subprocess.Popen(["rosparam" ,"set", "/face_recognition/show_screen_flag", "false"])
 
    #Start the Fserver
    subprocess.Popen(["screen","-dmS", "rosrunServer", "rosrun","face_recognition","Fserver"])
    #Wait for the server to start
    time.sleep(sleepTime)

    #Init the client node
    rospy.init_node('recog_node', anonymous=True)
    # Creates the SimpleActionClient, passing the type of the action
    client = actionlib.SimpleActionClient('face_recognition', FaceRecognitionAction)

    # Waits until the action server has started up and started
    # listening for goals.
    client.wait_for_server()

    # Creates a goal to send to the server.
    goal = FaceRecognitionGoal()
    goal.order_id = 0
    goal.order_argument = "none"

    # Sends the goal to the action server.
    client.send_goal(goal)

    # Waits for the server to finish performing the action.
    client.wait_for_result()

    # Prints out the result of executing the action
    print(client.get_result().names[0] + ":" + str(client.get_result().confidence[0]))


    pub = rospy.Publisher('openhab_command', KeyValue, queue_size=10)
    time.sleep(1)
    pub.publish(itemName, itemValue)
    time.sleep(sleepTime)

def killAll():
    subprocess.Popen(["screen","-S", "rosrunServer", "-X", "quit"])
    subprocess.Popen(["screen","-S", "roslaunchCamera", "-X", "quit"])
    #subprocess.Popen(["screen","-S", "roscoreMain", "-X", "quit"])
    subprocess.Popen(["screen","-S", "roslaunchBridge", "-X", "quit"])

if __name__ == '__main__':
    try:
	#Kill all screens before, just in case
	killAll()
	#main method
	if (len(sys.argv) > 2):
	        recog(sys.argv[1], sys.argv[2])
	else:
		print "Usage:  item_name  Value"
	#Kill all to exit
	#killAll()
    except rospy.ROSInterruptException:
	killAll()
        pass
