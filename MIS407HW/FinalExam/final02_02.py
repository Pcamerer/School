import csv
from tkinter import *
from tkinter import messagebox

filename = "CornProduction2007-2017.csv"

def read_cornprod(filename, county):
    with open(filename, newline='') as infile:
        reader = csv.reader(infile)
        for row in reader:
            dict = {'year': years, 'county': counties, 'value': values}


def report_command():
    sum = 0
    county = county_text.get()
    if county == "" :
        error=Label(window, text="Please enter a county")
        error.grid(row=0, column=3)
    else:
        with open(filename, newline='') as infile:
            reader = csv.reader(infile)
            for row in reader:
                if county.upper() == row[1]:
                    list1.insert(END, "{} {}".format(row[0], row[2]))
                    sum += int(row[2])
                    total_prod_text.set(sum)
                else:
                    pass



    # with open(filename, newline='') as infile:
    #     reader = csv.reader(infile)
    #     for row in reader:
    #         list1.insert(END, row)

    # """ Search for the specified county data in county_text field
    #     and show it in the list1 listbox. Also compute and show the
    #     total production for the county in total_prod_text. """


def clear_command():
    e1.delete(0, END)
    e2.delete(0, END)
    list1.delete(0, END)


def exit_command():
    if messagebox.askokcancel("Quit", "Do you really wish to quit?"):
        window.destroy()


window = Tk()  # TK method that creates a windows object
window.wm_title("County Corn Production Report")

#########
# Display Labels
l1 = Label(window, text="County")
l1.grid(row=0, column=0)

l2 = Label(window, text="Total Production")
l2.grid(row=1, column=0)

#########
# Display text entry fields
county_text = StringVar()
e1 = Entry(window, textvariable=county_text, width=35)
e1.grid(row=0, column=1)

total_prod_text = StringVar()
e2 = Entry(window, textvariable=total_prod_text, width=15)
e2.grid(row=1, column=1)

############################
# display listbox and attached a Scrollbar
list1 = Listbox(window, height=20, width=30)
list1.grid(row=2, column=0, rowspan=4, columnspan=2)

# list1.bind("<<ListboxSelect>>", get_selected_row)

sb1 = Scrollbar(window)
sb1.grid(row=2, column=2, rowspan=4, sticky=N+S+W)

list1.configure(yscrollcommand=sb1.set)
sb1.configure(command=list1.yview)

# Display Buttons
b1 = Button(window, text="Report", width=10, command=report_command)
b1.grid(row=2, column=3)
b1 = Button(window, text="Clear", width=10, command=clear_command)
b1.grid(row=3, column=3)
b6 = Button(window, text="Exit", width=10, command=exit_command)
b6.grid(row=4, column=3)

window.mainloop()
