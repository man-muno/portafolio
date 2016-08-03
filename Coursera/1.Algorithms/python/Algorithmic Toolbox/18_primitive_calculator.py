# Uses python3
import sys

def optimal_sequence(n):
    sequence = []
    operations = []
    response = []
    sequence.append(-1)
    sequence.append(1)
    sequence.append(1)
    sequence.append(1)
    operations.append(-1)
    operations.append(-1)
    operations.append(1)
    operations.append(1)

    i = 4
    while i <= n :
        mult3 = sys.maxsize
        mult2 = sys.maxsize
        mult1 = sys.maxsize
        if i % 3 == 0:
            #multiple of 3
            mult3 = sequence[int(i/3)]
        if i % 2 == 0:
            #multiple of 
            mult2 = sequence[int(i/2)]
        #just add 1
        temp_index = i-1
        mult1 = sequence[i-1]

        value = min(mult3,mult2,mult1)

        if value == mult3 :
            sequence.insert(i,mult3+1)
            operations.insert(i,int(i/3))
        elif value == mult2 :
            sequence.insert(i,mult2+1)
            operations.insert(i,int(i/2))
        else:
            sequence.insert(i,mult1+1)
            operations.insert(i,i-1)
        i+=1

    return operations

input = sys.stdin.read()
n = int(input)
operations = list(optimal_sequence(n))
response = []

while(n>=1):
    response.append(n)
    n = operations[n]

response = reversed(response)

for x in response:
    print(x, end=' ')
