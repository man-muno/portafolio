import os
import shutil
count = 0
for (dirname, dirs, files) in os.walk('/Users/manuel/Projects/'):
	for curr_dir in dirs:
		if curr_dir.startswith('.git') :
			thefolder = os.path.join(dirname,curr_dir)
			print os.path.getsize(thefolder), thefolder
			shutil.rmtree(thefolder)
			print 'deleted'
			#count = count + 1 
#print 'Files:', count