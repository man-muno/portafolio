def count(word, letter) :
	count = 0
	for letter in word:
		if letter == 'a': 
			count = count + 1
	return count
print count('banana','a')
