import csv

filename = input("Enter name of input file: ")
with open(filename, newline='') as csvfile:
    sampleReader = csv.reader(csvfile)
    sampleData = list(sampleReader)
    print(sampleData[0][0:])
    print(sampleData)
