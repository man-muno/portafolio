import string
file_name = raw_input('Enter file name:')
if len(file_name) < 1 : file_name = "short.txt"
try: 
	fhandle = open(file_name)
except:
	print 'Invalid file'
	exit()

dictionary = dict()
for line in fhandle :
	if line.startswith('From '):
		line = line.rstrip()
		words = line.split()
		word = words[5]
		hour = word.split(':')
		dictionary[hour[0]] = dictionary.get(hour[0],0) + 1

email_list = list()
for k,v in dictionary.items() :
	email_list.append((k,v))
email_list.sort()
for item in email_list:
	print item[0] , item[1]