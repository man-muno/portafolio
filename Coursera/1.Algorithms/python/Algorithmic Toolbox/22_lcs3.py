#Uses python3

import sys

def lcs3(seq1, seq2, seq3):
    #write your code here
    resp_1_2 = [[0]*(len(seq2)+1) for i in range(len(seq1)+1)]
    resp_1_3 = [[0]*(len(seq3)+1) for i in range(len(seq1)+1)]

    for i in range(0,len(seq1)+1):
        for j in range(0,len(seq2)+1):
            if i==0 :
                resp_1_2[i][j]=j
            if j == 0:
                resp_1_2[i][j]=i
            if i != 0 and j != 0:
                insertion = resp_1_2[i][j-1]+1
                deletion = resp_1_2[i-1][j]+1
                if seq1[i-1] == seq2[j-1]:
                    resp_1_2[i][j] = min(insertion,deletion,resp_1_2[i-1][j-1])
                else:
                    resp_1_2[i][j] = min(insertion,deletion,resp_1_2[i-1][j-1]+1)

        for j in range(0,len(seq3)+1):
            if i==0 :
                resp_1_3[i][j]=j
            if j == 0:
                resp_1_3[i][j]=i
            if i != 0 and j != 0:
                insertion = resp_1_3[i][j-1]+1
                deletion = resp_1_3[i-1][j]+1
                if seq1[i-1] == seq3[j-1]:
                    resp_1_3[i][j] = min(insertion,deletion,resp_1_3[i-1][j-1])
                else:
                    resp_1_3[i][j] = min(insertion,deletion,resp_1_3[i-1][j-1]+1)

    return min(resp_1_2[len(seq1)][len(seq2)],resp_1_3[len(seq1)][len(seq3)])

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    an = data[0]
    data = data[1:]
    a = data[:an]
    data = data[an:]
    bn = data[0]
    data = data[1:]
    b = data[:bn]
    data = data[bn:]
    cn = data[0]
    data = data[1:]
    c = data[:cn]
    print(lcs3(a, b, c))
