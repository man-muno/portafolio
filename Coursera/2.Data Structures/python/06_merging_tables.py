# python3

import sys

n, m = map(int, sys.stdin.readline().split())
lines = list(map(int, sys.stdin.readline().split()))
rank = [1] * n
parents = list(range(0, n))
ans = 1

def getParent(table):
    # find parent and compress path
    parent = parents[table]
    old = -1
    while  old != parent:
        old = parent
        parent = parents[parent]
    return parent

def merge(destination, source):
    realDestination, realSource = getParent(destination), getParent(source)

    if realDestination == realSource:
        return False

    # merge two components

    parents[realSource] = realDestination
    rank[realDestination] += rank[realSource]


    # use union by rank heuristic 
    # update ans with the new maximum table size
    global ans
    ans=max(rank[realDestination], ans)

    return True

for i in range(m):
    destination, source = map(int, sys.stdin.readline().split())
    merge(destination - 1, source - 1)
    print(ans)
    
