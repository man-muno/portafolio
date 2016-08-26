# python3

import sys, threading
sys.setrecursionlimit(10**7) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size

class Node:
    def __init__(self, value):
        self.value = value
        self.children = list()

    def addNode(self,parentValue, childValue):
        if self.value==parentValue:
            self.children.append(Node(childValue))
        else:
            for child in self.children :
                child.addNode(parentValue,childValue)

    def compute_height(self):
        height = -1
        for child in self.children:
            child_heigth = child.compute_height()
            if child_heigth > height:
                height = child_heigth

        return 1 + height





class Tree:
    def __init__(self):
        self.root = None

    def addNode(self,parentValue, childValue):
        if self.root == None:
            self.root = Node(childValue)
        else:
            self.root.addNode(parentValue,childValue)

    def read(self):
        n = int(sys.stdin.readline())
        parents = list(map(int, sys.stdin.readline().split()))
        queue = list()
        for i in range(0,len(parents)):
            if(parents[i]==-1):
                self.addNode(parents[i],i)
                queue.append(i)
                break
        
        while len(queue)!= 0 :
            parent = queue.pop(0)
            for i in range(0,len(parents)):
                if parent == parents[i]:
                    self.addNode(parent,i)
                    queue.append(i)


    def compute_height(self):
        if self.root == None:
            return 0
        else :
            return 1+self.root.compute_height();




class TreeHeight:
        def read(self):
                self.n = int(sys.stdin.readline())
                self.parent = list(map(int, sys.stdin.readline().split()))

        def compute_height(self):
                # Replace this code with a faster implementation
                maxHeight = 0
                for vertex in range(self.n):
                        height = 0
                        i = vertex
                        while i != -1:
                                height += 1
                                i = self.parent[i]
                        maxHeight = max(maxHeight, height);
                return maxHeight;

def main():
  tree = Tree()
  tree.read()
  print(tree.compute_height())

threading.Thread(target=main).start()
