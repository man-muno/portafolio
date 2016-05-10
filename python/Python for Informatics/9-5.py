import string
file_name = raw_input('Enter file name:')
try: 
	fhandle = open(file_name)
	dictionary = dict()
	for line in fhandle :
		line.rstrip()
		if line.startswith('From '):
			words = line.split()
			word = words[1]
			at_pos = word.find('@') + 1
			word = word[at_pos:]
			dictionary[word] = dictionary.get(word,0) + 1
	print dictionary
except:
	print 'Invalid file'
	exit()