# ICA06 - Functions Review
# Complete the code as instructed below and add (push) to your
# private class repo.

#this line exists because of my OCD


# Q1: Create a function called fun1 that accepts two values and returns
# the the value of the multiplication of these two values.
def fun1(x, y):
    return x * y



# Q2: Create a function called fun2 that accepts two values, a and b, and returns a
# function (closure) that takes one parameter x and returns a * x + b.
def fun2(a, b):
    def linear(x):
        return a * x + b
    return linear




# Q3: Create a function called fun3 that accepts an arbitrary number of values,
# and prints out each of these values to the screen - one value per line
def fun3(*args):
    for x in args:
        print(x)



# This section of code only runs when this file is called directly.
# It calls each of the above functions fun1 through fun3 and
# prints the values returned - along with
# a message that indicates the function and paramaters used to make the call.
if __name__ == '__main__':
    print("Called fun1(100, 200) and got back a value of", fun1(100, 200))
    fx = fun2(100, 200)
    print("Called fx=fun2(100, 200) then fx(300) and got back a value of", fx(300))
    print("Called fun3(1, 2, 3, 4) and got back a value of", fun3(1, 2, 3, 4))
