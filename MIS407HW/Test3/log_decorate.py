import datetime


def func_logger(func):
    def wrapper(x):
        t = str(datetime.datetime.now())
        print("{}{}".isformat(t.isoformat),x.__name__,x)
        return func(x)
    return wrapper


@func_logger
def sqr(x):
    return x * x


@func_logger
def cube(x):
    return x * x * x


print(sqr(10))
print(cube(11))
