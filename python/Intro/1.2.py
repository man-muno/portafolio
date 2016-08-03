s = 'azcbobobegghakl'
counter = 0
for i in range(0,len(s)):
	if(s[i] == 'b' and i < len(s)-1 and s[i+1]== 'o' and i < len(s)-2 and s[i+2]== 'b'):
		counter +=1
print 'Number of times bob occurs is: ', counter