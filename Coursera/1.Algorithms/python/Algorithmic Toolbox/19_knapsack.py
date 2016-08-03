# Uses python3
import sys

def optimal_weight(W, weights,values):
    # write your code here
    resp = [[0]*(W+1) for i in range(len(weights)+1)]
    for i in range(0,len(values)+1):
    	for w in range(0,W+1):
    		if i != 0 and w != 0:
	    		#Get weight in i
	    		weight = weights[i-1]
	    		if(weight<=w):
	    			#print(i,w)
	    			resp[i][w] = max(resp[i-1][w-weight]+values[i-1],resp[i-1][w])
	    		else:
	    			resp[i][w] = resp[i-1][w] 
    print(resp)
    return resp[len(weights)][W]



if __name__ == '__main__':
    input = sys.stdin.read()
    W, n, *w = list(map(int, input.split()))
    print(optimal_weight(W, w, w))
