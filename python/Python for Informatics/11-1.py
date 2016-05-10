import re
import string
file_name = raw_input('Enter file name:')
if len(file_name) < 1 : file_name = "regex_sum_42.txt"
try: 
	fhandle = open(file_name)
except:
	print 'Invalid file'
	exit()


number_list = list()
for line in fhandle :
	line = line.rstrip()
	x = re.findall('([0-9]+)',line)
	if len(x) > 0 :
		for element in x :
			number_list.append(int(element))
print sum(number_list)#, len(number_list)