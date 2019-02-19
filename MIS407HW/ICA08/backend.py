import sqlite3

db_name = 'testdb.sqlite'


def connect():
    """ Connect to the database """
    conn = sqlite3.connect(db_name)
    cur = conn.cursor()
    conn.commit()
    conn.close()


def execute(statement):
    """ Execute a given SQL statement and yield the header and rows """
    if statement[:-1] is not ';':
        statement += ';'
    if not sqlite3.complete_statement(statement):
        yield ['Invalid statement']
        return
    conn = sqlite3.connect(db_name)
    cur = conn.cursor()
    cur.execute(statement)
    if not statement.lstrip().upper().startswith("SELECT"):
        yield ['Completed']
    else:
        headers = [desc[0] for desc in cur.description]
        print(headers)
        yield headers
        for row in cur.fetchall():
            yield row
    conn.commit()
    conn.close()
    return
