# Uses python3
import sys

def get_fibonaccihuge(n, m):
	# write your code here
	if (n <= 1):	
		return n;

	numbers = [0,1]
	pisano = [0,1]

	frequency=0
	for i in range(2, n):
		numbers.append(numbers[i-1] + numbers[i-2])
		pisano.append(numbers[i]%m)

		if(i>3 and pisano[i]==1 and pisano[i-1]==1 and pisano[i-2]==0):
			frequency = i-2
			break
		if frequency != 0:
			break

	pos = n % frequency
	return pisano[pos]

print(get_fibonaccihuge(2015, 3))
