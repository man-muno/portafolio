fileName = raw_input('Enter file name:')
total = 0.0
count = 0
try:
	fhand = open(fileName)
	for line in fhand:
		if line.startswith('X-DSPAM-Confidence:'):
			startPos = line.find(' ')
			total = total + float(line[startPos:].rstrip())
			count = count + 1
	print 'Average spam confidence:' ,(total/count)
except :
	print 'File cannot be opened:', fname 
	exit()
