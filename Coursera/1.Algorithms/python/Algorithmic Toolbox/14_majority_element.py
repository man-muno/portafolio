# Uses python3
import sys

def get_majority_element_it(a, left, right):

    middle = int(len(a)/2)
    element = a[0]
    i = 0
    equal = True
    exist_other_half = False
    while(i<middle and equal) :
        if(element != a[i]):
            equal = False
        i+=1

    if(equal):
        # half of the array is equal. Then check the oder half for the element
        i = middle
        while(i<len(a)-1 and not exist_other_half):
            if(a[i] == element):
                exist_other_half = True
            i+=1
    else:
        # didnt found majority on the first half. Must check the other
        element = a[middle]
        i = middle
        equal = True
        exist_other_half = False
        while(i<len(a)-1 and equal) :
            if(element != a[i]):
                equal = False
            i+=1
        
        if(equal):
            while(i<middle and not exist_other_half):
                if(a[i] == element):
                    exist_other_half = True
                i+=1

    if (exist_other_half):
        return 1
    else:
        return -1



def get_majority_element(a, left, right):
    if left == right:
        return -1
    if left + 1 == right:
        return a[left]
    #write your code here
    return -1

if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
    if get_majority_element_it(a,0,len(a)) != -1:
        print(1)
    else:
        print(0)
