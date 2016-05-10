def chop(list):
	del list[0]
	del list[len(list)-1]
	return None

def middle(list):
	return list[1:len(list)-1]


list = [1,2,3]
chop(list)
print list
list = [1,2,3]
print middle(list)