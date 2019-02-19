import csv
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
total = 0


for key in mydict:
    mydict2[key.title()] = mydict[key]


print("Alcohol sales by county: ")
for key in sorted(mydict2):
    if not key:
        continue
    elif key.title() == county.title():
        liters = float(liters)
        liters += float(mydict2[key])
    else:
        if county != "":
            print (str(i) + ".  %s:  %10s ".format() % (county, str(liters)))
        county = key
        liters = mydict2[key]
        total = total + liters
    i += 1


print (str(i) + ".  %s: %s" % (county, str(liters)))
print("Total: " + str(total) + " liters")
