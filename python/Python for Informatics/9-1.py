import string
fhandle = open('short.txt')
dictionary = dict()
for line in fhandle :
	line.rstrip()
	line = line.translate(None, string.punctuation)
	line = line.lower()
	words = line.split()
	for word in words :
		dictionary[word] = dictionary.get(word,0) + 1
print dictionary