# Uses python3
import sys

def gcd(a, b):
	current_mod = a % b;

	while (current_mod != 0):
		a=b
		b=current_mod
		current_mod = a % b

	return b

def lcm(a, b):
    #write your code here
    return (a*b)/ gcd(a, b)

#input = sys.stdin.read()
#a, b = map(int, input.split())
print(lcm(6, 8))
print(lcm(28851538,1183019))

