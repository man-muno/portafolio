import string
fhandle = open('short.txt')
dictionary = dict()
for line in fhandle :
	line.rstrip()
	if line.startswith('From'):
		words = line.split()
		if len(words) > 3 :
			word = words[2]
			dictionary[word] = dictionary.get(word,0) + 1
print dictionary