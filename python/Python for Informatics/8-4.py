numbers = []
while True:
	strNumber = raw_input('Enter number:')
	if(strNumber != 'done') :
		try:
			number = int(strNumber)
			numbers.append(number)
		except:
			print 'Invalid input'
			continue
	else:
		print "Maximum %d" % (max(numbers))
		print "Minimum %d" % (min(numbers))
		break