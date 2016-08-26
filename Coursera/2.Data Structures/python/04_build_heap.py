# python3
import sys
class HeapBuilder:
  def __init__(self):
    self._swaps = []
    self._data = []

  def swap(self, i,j):
    self._swaps.append((i, j))
    self._data[i], self._data[j] = self._data[j], self._data[i]

  def getRightChild(self, pos):
    return 2*pos + 2

  def getLeftChild(self, pos):
    return 2*pos + 1

  def checkHeapCondition(self, pos):
    left_child = self.getLeftChild(pos)
    right_child = self.getRightChild(pos)

    if left_child < len(self._data):
      if right_child < len(self._data):
        if self._data[left_child] > self._data[right_child]:
          smallest = right_child
        else:
          smallest = left_child
      else:
        smallest = left_child
      if self._data[pos] > self._data[smallest]:
        self.swap(pos,smallest)
        self.checkHeapCondition(smallest)

  def ReadData(self):
    n = int(input())
    #self._data = [int(s) for s in input().split()]
    self._data  = list(map(int, sys.stdin.readline().split()))
    assert n == len(self._data)

  def WriteResponse(self):
    print(len(self._swaps))
    for swap in self._swaps:
      print(swap[0], swap[1])

  def GenerateSwaps(self):
    # The following naive implementation just sorts 
    # the given sequence using selection sort algorithm
    # and saves the resulting sequence of swaps.
    # This turns the given array into a heap, 
    # but in the worst case gives a quadratic number of swaps.
    #
    # TODO: replace by a more efficient implementation
    print (self._data)
    for pos in range(1+len(self._data)/2,-1,-1):
      self.checkHeapCondition(pos)
    print (self._data)

  def Solve(self):
    self.ReadData()
    self.GenerateSwaps()
    self.WriteResponse()

if __name__ == '__main__':
    heap_builder = HeapBuilder()
    heap_builder.Solve()
