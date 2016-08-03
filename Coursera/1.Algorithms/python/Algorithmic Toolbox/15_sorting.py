# Uses python3
import sys
import random

def partition3(a, l, r):
    #write your code here
    pivot = a[l]
    start_pivot = l
    end_pitov = l
    start_bigger = r

    i = l + 1
    while(i<=start_bigger):
        if(a[i]<pivot):
            a[start_pivot], a[i] = a[i], a[start_pivot]
            start_pivot+=1
            end_pitov+=1
        elif (a[i]>pivot):
            a[start_bigger], a[i] = a[i], a[start_bigger]
            i-=1
            start_bigger-=1
        else:
            end_pitov+=1
        i+=1

    return start_pivot

def partition2(a, l, r):
    x = a[l]
    j = l;
    for i in range(l + 1, r + 1):
        if a[i] <= x:
            j += 1
            a[i], a[j] = a[j], a[i]
    a[l], a[j] = a[j], a[l]
    return j


def randomized_quick_sort(a, l, r):
    if l >= r:
        return
    k = random.randint(l, r)
    a[l], a[k] = a[k], a[l]
    #use partition3
    m = partition3(a, l, r)
    randomized_quick_sort(a, l, m - 1);
    randomized_quick_sort(a, m + 1, r);


if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
    randomized_quick_sort(a, 0, n - 1)
    for x in a:
        print(x, end=' ')
