# python3

import sys

class Bracket:
    def __init__(self, bracket_type, position):
        self.bracket_type = bracket_type
        self.position = position

    def match(self, c):
        if self.bracket_type == '[' and c == ']':
            return True
        if self.bracket_type == '{' and c == '}':
            return True
        if self.bracket_type == '(' and c == ')':
            return True
        return False

    def __str__(self):
        return "(" + self.bracket_type + "," + str(self.position) + ")"

if __name__ == "__main__":
    text = sys.stdin.read()
    response = Bracket('',-1)
    opening_brackets_stack = []
    for i, next in enumerate(text):
        if next == '(' or next == '[' or next == '{':
            # Process opening bracket, write your code here
            #print(Bracket(next,i+1))
            opening_brackets_stack.append(Bracket(next,i+1))

        if next == ')' or next == ']' or next == '}':
            # Process closing bracket, write your code here
            if(len(opening_brackets_stack)>0):
                bracket = opening_brackets_stack.pop()
                if(not bracket.match(next)):
                    response = bracket
                    bracket.position = i + 1
                    break
        else:
            response.position = i + 1

    # Printing answer, write your code here


    if(response == -1):
        print(len(opening_brackets_stack))
    else:
        print(response.position)
