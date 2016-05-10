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
biggest_value = None
biggest_key = None
for key,value in dictionary.items():
	if biggest_value is None or value > biggest_value :
		biggest_key = key
		biggest_value = value
print biggest_key, biggest_value
