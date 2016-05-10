import urllib
from BeautifulSoup import *
urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
	urlStr = 'http://python-data.dr-chuck.net/comments_42.html'

html = urllib.urlopen(urlStr).read()
soup = BeautifulSoup(html)
# Retrieve all of the anchor tags 
tags = soup('span')
total_sum = 0
for tag in tags:
    total_sum += int(tag.contents[0])
print total_sum