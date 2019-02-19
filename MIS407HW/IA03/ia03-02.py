import csv
from collections import Counter
mydict = {}

filename = input("Enter file name: ")
with open(filename, newline='') as infile:
    reader = csv.reader(infile)
    next(reader, None)
    for row in reader:
        if row[8] in mydict:
            mydict[row[8]] = float(mydict[row[8]]) + float(row[22])
        else:
            mydict[row[8]] = row[22]


county = ""
liters = 0
i = 0
mydict2 = {}

for key in mydict:
    mydict2[key.title()] = mydict[key]


print("Top 5 counties alcohol sales by volume: ")
for key in sorted(mydict2):
    if not key:
        continue
    elif key.title() == county.title():
        liters = float(liters)
        liters += float(mydict2[key])
    else:
        if county != "":
            county = key
            liters = mydict2[key]


top_values = Counter(mydict2).most_common(5)
for line in top_values:
    i += 1
    print(str(i) + ".  %s: %s" % (line))
