
import socket
import re
urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
	urlStr = 'http://www.pythonlearn.com/code/intro-short.txt'

address = urlStr.split('/')[2]

mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
mysock.connect((address, 80))
mysock.send('GET '+ urlStr + ' HTTP/1.0\n\n')

all_data = ""
while True:
	data = mysock.recv(512) 
	if(len(data)<1):
		break 
	all_data += data
mysock.close()
string_to_find = 'Content-Type: text/plain\r'
pos = all_data.find(string_to_find) + 1
print all_data[pos+len(string_to_find):]