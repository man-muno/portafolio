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
	line = line.translate(None, string.punctuation)
	line = line.translate(None, string.digits)
	line = line.lower()
	words = line.split()
	for word in words: 
		for character in word:
			dictionary[character] = dictionary.get(character,0) + 1

char_list = list()
for k,v in dictionary.items() :
	char_list.append((k,v))
char_list.sort()
print char_list


