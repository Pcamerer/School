

class Stats:
    def __init__(self):
        self.sum = 0.0
        self.count = 0

    def add(self, value):
        # Finish the code to handle a new value
        self.sum += value
        self.count += 1

    def get_average(self):
        # Finish the code to compute the average
        if self.count == 0:
            return 0
        return self.sum / self.count

    def get_sum(self):
        # Finish the code to return the sum
        return self.sum

    def get_count(self):
        # Finish the code to return the count
        return self.count


statistics = Stats()
done = False
while not done:
    value_str = input("Enter number, or 'quit' to finish: ")
    if value_str == 'quit':
        done = True
    else:
        value = float(value_str)
        statistics.add(value)

print("Sum: {}".format(statistics.get_sum()))
print("Average: {}".format(statistics.get_average()))
