# Uses python3
import sys

def evalt(a, b, op):
    if op == '+':
        return a + b
    elif op == '-':
        return a - b
    elif op == '*':
        return a * b
    else:
        assert False


def minmax(i,j,dataset,maxValues,minValues):
    min_value = sys.maxsize
    max_value = -sys.maxsize

    for k in range(i,j):
        op = dataset[k*2+1]
        a = evalt(maxValues[i][k],maxValues[k+1][j],op)
        b = evalt(maxValues[i][k],minValues[k+1][j],op)
        c = evalt(minValues[i][k],maxValues[k+1][j],op)
        d = evalt(minValues[i][k],minValues[k+1][j],op)
        min_value = min(min_value,a,b,c,d)
        max_value = max(max_value,a,b,c,d)

    return (min_value,max_value)

def get_maximum_value(dataset):
    length = int((len(dataset)+1)/2)
    maxValues = [[0]*(length) for i in range(length)]
    minValues = [[0]*(length) for i in range(length)]


    for i in range(0,length):
        maxValues[i][i]=float(dataset[i*2])
        minValues[i][i]=float(dataset[i*2])

    for s in range(1,1+length):
        for i in range(1,1+length-s):
            j = i+s
            min_max = minmax(i-1,j-1,dataset,maxValues,minValues)
            maxValues[i-1][j-1] = min_max[1]
            minValues[i-1][j-1] = min_max[0]
    return maxValues[0][length-1]


if __name__ == "__main__":
    print(get_maximum_value(input()))
