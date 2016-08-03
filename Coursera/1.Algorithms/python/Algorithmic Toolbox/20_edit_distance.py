# Uses python3
def edit_distance(vertical, horizontal):
    #write your code here
    resp = [[0]*(len(horizontal)+1) for i in range(len(vertical)+1)]
    
    for i in range(0,len(vertical)+1):
    	for j in range(0,len(horizontal)+1):
    		if i==0 :
    			resp[i][j]=j
    		if j == 0:
    			resp[i][j]=i
    		if i != 0 and j != 0:
    			insertion = resp[i][j-1]+1
    			deletion = resp[i-1][j]+1
    			if vertical[i-1] == horizontal[j-1]:
    				resp[i][j] = min(insertion,deletion,resp[i-1][j-1])
    			else:
    				resp[i][j] = min(insertion,deletion,resp[i-1][j-1]+1)
    return resp[len(vertical)][len(horizontal)]

if __name__ == "__main__":
    print(edit_distance(input(), input()))
