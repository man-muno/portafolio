fileName = raw_input('Enter file name:')
try:
	fhand = open(fileName)
	for line in fhand:
		print line.upper().rstrip()
except :
	print 'File cannot be opened:', fname 
	exit()
