import time
import subprocess
import actionlib
import rospy, sys
from face_recognition.msg import FaceRecognitionActionResult
from face_recognition.msg import FaceRecognitionGoal
from face_recognition.msg import FaceRecognitionAction
from diagnostic_msgs.msg import KeyValue
import os


def talker():
	
     
    #How long to sleep
    sleepTime = 4
	
    #Starts the script on screen due to process and environmtns
    subprocess.Popen(["screen","-dmS", "pythonScreen", "python","startRecogEnvironment.py", "Recognized_Face", "ON"])
    #Wait for the server to start
    time.sleep(sleepTime)
    print("recog.py executed")

  

if __name__ == '__main__':
    try:
        talker()
    except rospy.ROSInterruptException:
        pass
