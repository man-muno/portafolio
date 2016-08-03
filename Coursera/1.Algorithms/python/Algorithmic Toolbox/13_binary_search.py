# Uses python3
import sys


def binary_search_it(a, x):
    start = 0
    end = len(a)-1
    while(end>start):
        middle  = int((start + end )/2)
        if(a[middle] == x ):
            return middle
        elif(a[middle]>x):
            end = middle-1
        elif(a[middle]<x):
            start = middle+1
    return -1


def binary_search_rec(a, x, start, end):
    middle  = int((start + end )/2)
    if(a[middle] == x ):
        return middle
    elif(end <= start):
        return -1
    elif(a[middle]>x):
        return binary_search_rec(a,x,start,middle-1)
    elif(a[middle]<x):
        return binary_search_rec(a,x,middle+1,end)



def binary_search(a, x):
    left, right = 0, len(a)
    # write your code here
    return binary_search_rec(a,x,0,len(a)-1)

def linear_search(a, x):
    for i in range(len(a)):
        if a[i] == x:
            return i
    return -1

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    m = data[n + 1]
    a = data[1 : n + 1]
    for x in data[n + 2:]:
        # replace with the call to binary_search when implemented
        print(binary_search_it(a, x), end = ' ')
