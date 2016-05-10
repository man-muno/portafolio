file = raw_input("Enter a file name:")
count = 0
addresses = []
try:
	fhandle = open(file)
	for line in fhandle: 
		line.rstrip()
		if line.startswith('From ') :
			words = line.split()
			print words[1]
			count = count + 1
	print "There were %d lines in the file with From as the first word" % (count)
except :
	print 'File cannot be opened:', fname 
	exit()