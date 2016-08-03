# Uses python3
import sys

def get_fibonacci_last_digit(n):
    # write your code here
    if (n <= 1):
    	return n

    numbers = [1,1];
    for i in range(2, n):
    	numbers.append((numbers[i-1] + numbers[i-2]) % 10)

    return numbers[n-1]


n = int(input())
print(get_fibonacci_last_digit(n))
