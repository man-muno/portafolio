
import urllib
urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
	urlStr = 'http://www.py4inf.com/code/romeo.txt'

url_handle = urllib.urlopen(urlStr)

all_data = ""
for line in url_handle :
	all_data += line
print all_data[:3001]