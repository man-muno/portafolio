order = "salad water hamburger salad hamburger"

def item_order(order):
	counter_salad = 0
	counter_water = 0
	counter_burger = 0
	splited_order = order.split( )
	for item in splited_order:
		if (item == 'salad'):
			counter_salad +=1
		elif (item == 'water'):
			counter_water += 1
		elif (item == 'hamburger'):
			counter_burger +=1
	return 'salad:' + str(counter_salad) + ' hamburger:' + str(counter_burger) + ' water:' + str(counter_water)

print item_order(order)