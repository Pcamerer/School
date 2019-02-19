import sqlite3

sqlite_file = 'corny.sqlite'
table_name = 'prod'   # name of the table to be queried

conn = sqlite3.connect(sqlite_file)

story_co_corn_prod = \
    [
        {'year': 2007, 'bushels': 33822000},
        {'year': 2008, 'bushels': 27000000},
        {'year': 2009, 'bushels': 29782000},
        {'year': 2010, 'bushels': 26790000},
        {'year': 2011, 'bushels': 28220000},
        {'year': 2012, 'bushels': 27086000},
        {'year': 2013, 'bushels': 21689000},
        {'year': 2014, 'bushels': 26923000},
        {'year': 2015, 'bushels': 30286000},
        {'year': 2016, 'bushels': 36402000},
        {'year': 2017, 'bushels': 33264000},
    ]

cur = conn.cursor()
cur.execute("CREATE TABLE IF NOT EXISTS {tn} (year INTEGER PRIMARY KEY, "
            "bushels INTEGER NOT NULL)".format(tn=table_name))
# Remove any rows that might already be in the table
cur.execute("DELETE FROM {tn}".format(tn=table_name))
# Insert the rows
for row in story_co_corn_prod:
    cur.execute('INSERT INTO {tn} (year, bushels) VALUES ({yr}, {bu})'.
                format(tn=table_name, yr=row['year'], bu=row['bushels']))
cur.execute('commit')
# Add the missing code to query and print the MIN(), AVG(), and MAX() bushels
# from the prod table.
cur.execute("SELECT AVG(bushels) FROM prod")
average = cur.fetchone()
print("{}{}".format("Average bushels: ", average))
cur.execute("SELECT MIN(bushels) FROM prod")
min = cur.fetchone()
print("{}{}".format("Minimum bushels: ", min))
cur.execute("SELECT MAX(bushels) FROM prod")
max = cur.fetchone()
print("{}{}".format("Maximum bushels: ", max))
