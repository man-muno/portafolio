# python3
import sys

class Worker:
  def __init__(self, id, priority):
    self.id = id;
    self.priority = priority;

  def getId(self):
    return self.id

  def getPriority(self):
    return self.priority

  def setId(self,id):
    self.id=id

  def setPriority(self,priority):
    self.priority = priority

  def __str__(self):
    return str(self.id)



class Heap:
  def __init__(self):
    self._data = []

  def __str__(self):
    return self._data

  def swap(self, i,j):
    self._data[i], self._data[j] = self._data[j], self._data[i]

  def getRightChild(self, pos):
    return 2*pos + 2

  def getLeftChild(self, pos):
    return 2*pos + 1

  def getParent(self,pos):
    return int(pos/2)

  def load(self,worker):
    self._data.append(worker)


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


  def getPriorityWorker(self):
    worker = self._data.pop(0)
    self.checkHeapCondition(0)
    return worker


  def siffUp(self, pos) :
    parentPos = self.getParent(pos)
    if self._data[pos].getPriority() < self._data[parentPos].getPriority() :
      self.swap(pos,parentPos)

  def addWorker(self,worker):
    self._data.append(worker)
    self.siffUp(len(self._data)-1)

class JobQueue:
  workers = Heap()

  def read_data(self):
    #self._data  = list(map(int, sys.stdin.readline().split()))
    self.num_workers, m = map(int, sys.stdin.readline().split()) 
    self.jobs = list(map(int, sys.stdin.readline().split()))
    assert m == len(self.jobs)
    for i in range(0,self.num_workers):
      self.workers.load(Worker(i,0))

  def write_response(self):
    for i in range(len(self.jobs)):
      print(self.assigned_workers[i], self.start_times[i]) 

  def assign_jobs(self):
    # TODO: replace this code with a faster algorithm.
    self.assigned_workers = [None] * len(self.jobs)
    self.start_times = [None] * len(self.jobs)
    next_free_time = [0] * self.num_workers
    for i in range(len(self.jobs)):
      duration = self.jobs[i]
      worker = self.workers.getPriorityWorker()
      self.assigned_workers[i] = worker.getId()
      self.start_times[i] = worker.getPriority()
      worker.setPriority(worker.getPriority()+duration)
      self.workers.addWorker(worker)


      #next_worker = 0
      #for j in range(self.num_workers):
      #  if next_free_time[j] < next_free_time[next_worker]:
      #    next_worker = j
      #self.assigned_workers[i] = next_worker
      #self.start_times[i] = next_free_time[next_worker]
      #next_free_time[next_worker] += self.jobs[i]

  def solve(self):
      self.read_data()
      self.assign_jobs()
      self.write_response()

if __name__ == '__main__':
  job_queue = JobQueue()
  job_queue.solve()

