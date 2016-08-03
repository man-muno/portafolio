# Uses python3
import sys
from collections import namedtuple

Segment = namedtuple('Segment', 'start end')


def swap(segments, firstIndex, secondIndex):
    temp = segments[firstIndex]
    segments[firstIndex] = segments[secondIndex]
    segments[secondIndex] = temp

def partition(segments,p,r):
    q = p
    for j in range(p,r):
        if (segments[j].end <= segments[r].end) :
            swap(segments, j, q)
            q+=1
    swap(segments, r, q)
    return q


def quickSort(segments,p,r):
    if(r-p>0):
        pivot = partition(segments,p,r)
        quickSort(segments,p,pivot-1)
        quickSort(segments,pivot+1,r)


def optimal_points(segments):
    points = []
    #write your code here
    print (range(0,len(segments)-1))
    for i in range(0,len(segments)-1):
        segmentToTest = segments[i]
        if segmentToTest is not None : 
            for j in range(i,len(segments)-1):
                secondSegmentToTest = segments[j]
                if secondSegmentToTest.start <= segmentToTest.end and segmentToTest.end <= secondSegmentToTest.end :
                    segments[j] = None
            points.append(segmentToTest.end)
    return points

if __name__ == '__main__':
    input = sys.stdin.read()
    n, *data = map(int, input.split())
    segments = list(map(lambda x: Segment(x[0], x[1]), zip(data[::2], data[1::2])))
    quickSort(segments,0,len(segments)-1)
    points = optimal_points(segments)
    print(len(points))
    for p in points:
        print(p, end=' ')
