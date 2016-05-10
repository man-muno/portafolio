import urllib
import json

urlStr = raw_input('Enter url:')
if len(urlStr) == 0:
    urlStr = 'http://python-data.dr-chuck.net/comments_42.json'


uh = urllib.urlopen(urlStr)
data = uh.read()
json_doc = json.loads(data)

total_sum = 0
comments = json_doc['comments']
for item in comments:
	total_sum += int(item['count'])
print total_sum