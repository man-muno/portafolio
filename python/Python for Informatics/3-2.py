while True:
 strHours = raw_input('Enter hours: ')
 strRate = raw_input('Enter rate: ')
 pay = 0.0
 try:
  hours = int(strHours)
  rate = int(strRate)
  if hours > 40 : 
   pay = ((hours-40) * (rate * 1.5))+(40*rate) 
  else:
   pay = (hours * rate) + pay
  print 'Pay: ' + str(pay)
  break
 except:
  print 'Enter numeric input'
  continue