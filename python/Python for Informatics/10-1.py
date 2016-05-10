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
		word = words[1]
		dictionary[word] = dictionary.get(word,0) + 1

email_list = list()
for k,v in dictionary.items() :
	email_list.append((v,k))
email_list.sort(reverse=True)
print email_list