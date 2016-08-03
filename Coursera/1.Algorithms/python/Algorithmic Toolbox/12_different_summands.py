# Uses python3
import sys

def optimal_summands(n):
    summands = []
    #write your code here

    k=n
    l=1
    while(not(k<=l)):
    	summands.append(l)
    	k -=1
    	l *=2

    if(k > 3):
    	summands.append(k-1)
    else:
    	summands.append(k)

    return summands

if __name__ == '__main__':
    input = sys.stdin.read()
    n = int(input)
    summands = optimal_summands(n)
    print(len(summands))
    for x in summands:
        print(x, end=' ')
