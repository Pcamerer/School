""" Program that executes a SQL statement and shows results.
    Enter and execute a SQL statement
    Exit the application
"""

from tkinter import *
from tkinter import messagebox

import backend as backend


#########
# Create our button handlers (see where we create the buttons and notice how
# we hook these to the appropriate button using the commmand= option)
def execute_command():
    """ Execute the SQL statement and update the listbox with the results """
    """ Finish the code to backend.execute() the SQL statement and stuff
        the results into list1. """
    list1.delete(0, END)

    list1.insert(END, "Eecute command: " + statement_text.get())
    for row in backend.execute(statement_text.get()):
        list1.insert(END, row)


def exit_command():
    """ Ask user whether to really quit. """
    if messagebox.askokcancel("Quit", "Do you really wish to quit?"):
        window.destroy()


window = Tk()  # TK method that creates a windows objective
window.wm_title("Database Commands")

#########
# Display Labels
l1 = Label(window, text="SQL")
l1.grid(row=0, column=0)


#########
# Display text entry fields
statement_text = StringVar()
e1 = Entry(window, textvariable=statement_text, width=80)
e1.grid(row=0, column=1)

############################
# display listbox and attach a Scrollbar
list1 = Listbox(window, height=20, width=100)
list1.grid(row=2, column=0, rowspan=20, columnspan=3)

#list1.bind("<<ListboxSelect>>", get_selected_row)

sb1 = Scrollbar(window)
sb1.grid(row=2, column=3, rowspan=20, sticky=N+S+W)

list1.configure(yscrollcommand=sb1.set)
sb1.configure(command=list1.yview)

# Display Buttons
b1 = Button(window, text="Execute", width=25, command=execute_command)
b1.grid(row=0, column=2, columnspan=2)
b6 = Button(window, text="Exit", width=25, command=exit_command)
b6.grid(row=1, column=2, columnspan=2)

window.mainloop()
