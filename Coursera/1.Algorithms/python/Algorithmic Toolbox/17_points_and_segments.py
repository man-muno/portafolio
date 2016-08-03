# Uses python3
import sys
import random

def partition2(a, l, r):
    x = a[l][0]
    j = l;
    for i in range(l + 1, r + 1):
        if a[i][0] <= x:
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
    m = partition2(a, l, r)
    randomized_quick_sort(a, l, m - 1);
    randomized_quick_sort(a, m + 1, r);

def fast_count_segments(items, num_points):
    cnt = [0] * num_points
    #write your code here
    current_segment = 0
    point_counter = 0
    for item in items:
        item_type = item[1]
        if item_type == -1:
            # segment start
            current_segment+=1
        elif item_type == 0:
            current_segment-=1
        else:
            cnt[point_counter] = current_segment
            point_counter+=1
    return cnt

def naive_count_segments(starts, ends, points):
    cnt = [0] * len(points)
    for i in range(len(points)):
        for j in range(len(starts)):
            if starts[j] <= points[i] <= ends[j]:
                cnt[i] += 1
    return cnt

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))

    n = data[0]
    m = data[1]
    temp_starts = data[2:2 * n + 2:2]
    items = list()
    for start in temp_starts:
        items.append((start,-1))
    temp_ends   = data[3:2 * n + 2:2]
    for end in temp_ends:
        items.append((end,0))
    temp_points = data[2 * n + 2:]
    for point in temp_points:
        items.append((point,1))

    randomized_quick_sort(items, 0, len(items)-1)
    #use fast_count_segments
    cnt = fast_count_segments(items,m)
    for x in cnt:
        print(x, end=' ')
