str = 'X-DSPAM-Confidence: 0.8475'
startPos = str.find(':') + 1
print float(str[startPos:len(str)])
