# Uses python3
import sys

def get_number_of_inversions(a, b, left, right):
    number_of_inversions = 0
    if right  <= left + 1:
        if(a[left] > a[right]):
            a[left],a[right] = a[right],a[left]
            number_of_inversions +=1
        return number_of_inversions


    ave = (left + right) // 2
    number_of_inversions += get_number_of_inversions(a, b, left, ave)
    number_of_inversions += get_number_of_inversions(a, b, ave+1, right)
    #write your code here

    #Create array and copy values
    low_array = a[left:ave+1]
    high_array = a[ave+1:right+1]

    low_index = 0
    high_index = 0
    n = left

    while n<=right and low_index < len(low_array) and high_index < len(high_array) :
    #for n in range(left,right+1):
        if(low_array[low_index] > high_array[high_index]):
            a[n] = high_array[high_index]
            high_index+=1
            number_of_inversions +=(len(low_array)-low_index)
        else:
            a[n] = low_array[low_index]
            low_index+=1
        n+=1

    while low_index < len(low_array):
        a[n] = low_array[low_index]
        n+=1
        low_index+=1
    while high_index < len(high_array):
        a[n] = high_array[high_index]
        n+=1
        high_index+=1
    return number_of_inversions

if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
    b = n * [0]
    print(get_number_of_inversions(a, b, 0, len(a)-1))
    print (a)
    