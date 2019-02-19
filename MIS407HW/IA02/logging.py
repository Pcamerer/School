from datetime import datetime

"""Helper module to log information about function calls.
    Preston D Camerer 09/27/18 MIS407"""

def log_function(f, msg):
    t = datetime.now()
    print("{}{}{}: {}".isformat(t.isoformat(sep=' '),
    f.__module__,f.__name__,msg))

def main():
    if __name__ == "__main__":
        print("Sorry, not for human consumption.")
