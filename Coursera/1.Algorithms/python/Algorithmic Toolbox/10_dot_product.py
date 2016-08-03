#Uses python3

import sys

def min_dot_product(a, b):
    #write your code here
    res = 0
    for i in range(len(a)):
        res += a[i] * b[len(b) - i - 1]
    return res

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    a = data[1:(n + 1)]
    b = data[(n + 1):]
    a.sort()
    b.sort()
    print(min_dot_product(a, b))
    
