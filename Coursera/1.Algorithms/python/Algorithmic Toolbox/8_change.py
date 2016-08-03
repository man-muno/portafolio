# Uses python3
import sys

def get_change(n):
    #write your code here
    coin1 = 1;
    coin5 = 5;
    coin10 = 10;
    	
    total10Coins = int(n/coin10)
    coin10Value = total10Coins*coin10
    total5Coins = int((n-coin10Value)/coin5)
    coin5Value = total5Coins * coin5
    total1coins= int((n-coin10Value-coin5Value)/coin1)

    return total10Coins+total5Coins+total1coins

#if __name__ == '__main__':
#    n = int(sys.stdin.read())
print(get_change(2))
print(get_change(28))
