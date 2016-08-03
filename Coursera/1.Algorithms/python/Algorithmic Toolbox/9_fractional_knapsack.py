# Uses python3
import sys

def get_pos_bigger_density(densities):
	pos=-1
	biggest=-1
	for i in range(0,len(densities)):
		if(densities[i]>biggest):
			biggest = densities[i]
			pos = i
	if(pos != -1):
		densities[pos]=-1
	return pos

def get_optimal_value(capacity, weights, values):
    value = 0.
    # write your code here
    densities = list()
    for i in range(0,len(weights)):
    	densities.append(values[i]/weights[i])

    full = False
    while (full is False):
    	currentItemPos = get_pos_bigger_density(densities)
    	if(currentItemPos == -1):
    		# No more items to check. Not full but it should end
    		full = True
    	elif(capacity >= weights[currentItemPos]):
    		# There is enough capacity to add the weight directly
    		currentValue = values[currentItemPos]
    		capacity -= weights[currentItemPos]
    		value += values[currentItemPos]
    	else:
    		# Got to get a chuck of the current biggest item. Get it and mark as full
    		density = values[currentItemPos]/weights[currentItemPos]
    		value += (density*capacity)
    		full = True
    	
    return value


if __name__ == "__main__":
    data = list(map(int, sys.stdin.read().split()))
    n, capacity = data[0:2]
    values = data[2:(2 * n + 2):2]
    weights = data[3:(2 * n + 2):2]
    opt_value = get_optimal_value(capacity, weights, values)
    print("{:.10f}".format(opt_value))
