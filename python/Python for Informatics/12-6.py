import urllib
from BeautifulSoup import *
urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
	urlStr = 'http://python-data.dr-chuck.net/known_by_Fikret.html'

timesStr = raw_input('Enter count:')
if len(timesStr) == 0:
	i = 4
else :
	i = int(timesStr)

posStr = raw_input('Enter position:')
if len(posStr) == 0:
	link_pos = 3
else :
	link_pos = int(posStr)

link_pos -= 1
last_name = ""
while i > 0 :
	html = urllib.urlopen(urlStr).read()
	soup = BeautifulSoup(html)
	# Retrieve all of the anchor tags 
	tags = soup('a')
	urlStr = tags[link_pos].get('href', None)
	last_name = tags[link_pos].contents[0]
	i -= 1 
	#print last_name
print last_name