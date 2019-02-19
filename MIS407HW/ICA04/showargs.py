#this line exists due to my OCD

import sys

for word in sys.argv[1:]:
    print("{}:".format(word))
    for letter in word:
        if letter.isalpha():
            print("  {}: alpha".format(letter))
        elif letter.isdigit():
            print("  {}: digit".format(letter))
        else:
            print("  {}: unknown".format(letter))
