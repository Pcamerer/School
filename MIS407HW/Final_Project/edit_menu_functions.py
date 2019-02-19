import sqlite3
import csv
from tkinter import *


def connect():
    conn = sqlite3.connect('MIS407DB.sqlite')
    cur = conn.cursor()


def insert(category, name, price):
    conn = sqlite3.connect('MIS407DB.sqlite')
    cur = conn.cursor()
    cur.execute("INSERT INTO menu VALUES (NULL, ?, ?, ?)", (name, category, price))
    conn.commit()


def delete(id):
    conn = sqlite3.connect('MIS407DB.sqlite')
    cur = conn.cursor()
    query = "DELETE FROM menu WHERE Menu_item_ID = {}".format(id,)
    cur.execute(query)
    conn.commit()


def update(id, category, name, price):
    conn = sqlite3.connect('MIS407DB.sqlite')
    cur = conn.cursor()
    query = "UPDATE menu SET category = '{}', Name = '{}', Price = {} WHERE Menu_item_ID = {}".format(category, name, price, id)
    cur.execute(query)
    conn.commit()
    conn.close()


def search(category="", name=""):
    conn=sqlite3.connect('MIS407DB.sqlite')
    cur=conn.cursor()
    query = "SELECT * FROM menu WHERE category LIKE '{}' OR name LIKE '{}'".format(category, name)
    cur.execute(query)
    rows = cur.fetchall()
    conn.close()
    return rows


if __name__ == "__main__":
    connect()
