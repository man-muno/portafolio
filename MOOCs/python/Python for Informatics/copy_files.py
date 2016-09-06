import os
import shutil
count = 0
for (dirname, dirs, files) in os.walk('/Users/manuel/Desktop/Assist/'):
	for file_name in files:
		if file_name.endswith('.pdf') and file_name.find('ath') != -1:
			file_path = os.path.join(dirname,file_name)
			thefile = os.path.join(dirname,file_name) 
			print thefile
			shutil.copy(str(thefile),'/Users/manuel/Desktop/tmp/')