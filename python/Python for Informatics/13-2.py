import urllib
import xml.etree.ElementTree as ET

urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
    urlStr = 'http://python-data.dr-chuck.net/comments_42.xml'


print 'Retrieving', urlStr
uh = urllib.urlopen(urlStr)
data = uh.read()
print 'Retrieved',len(data),'characters'
tree = ET.fromstring(data)

total = 0
#results = tree.findall('commentinfo/comments/comment/count')
results = tree.findall('.//count')
for result in results:
    total += int(result.text)
print total
#lat = results[0].find('geometry').find('location').find('lat').text
