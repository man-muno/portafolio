# Uses python3
import sys

def gcd(a, b):
	current_mod = a % b;

	while (current_mod != 0):
		a=b
		b=current_mod
		current_mod = a % b

	return b


#if __name__ == "__main__":
#input = sys.stdin.read()
#a, b = map(int, input.split())
print(gcd(18, 35))
print(gcd(28851538, 1183019))