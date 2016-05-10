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
		print "%d %d %d" % (len(numbers), sum(numbers), sum(numbers)/len(numbers))
		break