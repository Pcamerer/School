import csv
from tkinter import *
from tkinter import messagebox
import sys

window = Tk()
window.title("Test4")

def read_file(filename):
    filename = 'sample.csv'
    with open(filename, newline='') as infile:
        reader = csv.reader(infile)
        for row in reader:
            list1.insert(END, row)

    l2 = Label(window, text = filename)
    l2.grid(row=0, column=1, sticky=W)

def exit_command():
    if messagebox.askokcancel("Quit", "Do you really wish to quit?"):
        window.destroy()

# Display Titles
l1 = Label(window, text="Input File")
l1.grid(row=0, column=0)

# Display text fields
file_field = StringVar()
e1 = Entry(window, textvariable=file_field, state='readonly', width=50)
e1.grid(row=0, column=1)

# Display Buttons
b3 = Button(window, text="Exit", width=5, command=exit_command)
b3.grid(row=0, column=2)

############################
# display listbox and attach a scrollbar
list1 = Listbox(window, height=5, width=80)
list1.grid(row=1, column=0, rowspan=4, columnspan=3)

sb1 = Scrollbar(window)
sb1.grid(row=1, column=3, rowspan=4, sticky=N+S+W)

list1.configure(yscrollcommand=sb1.set)
sb1.configure(command=list1.yview)

read_file(sys.argv[1])

window.mainloop()
