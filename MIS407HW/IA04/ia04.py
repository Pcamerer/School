from tkinter import *
from tkinter import messagebox

window = Tk()
window.title("Savings Plan")

#Functions
def compute_command():

    i=0
    finalbal=0
    monthly_rate = float(rate.get()) / 12.0
    months = int(year.get()) * 12

    current_balance=float(initial_balance.get())
    interest=float(initial_balance.get()) * monthly_rate

    list1.delete(0, END)

    for i in range(0, months):
        interest=float(current_balance) * monthly_rate
        current_balance+=float(monthly_deposit.get()) + interest

        list1.insert(END, "{} {} {} {}".format(i+1, monthly_deposit.get(), interest, current_balance))

    finalbal+=current_balance
    i+=1

    finalBalVar.set(finalbal)

def clear_command():
    list1.delete(0, END)
    e1.delete(0, END)
    e2.delete(0, END)
    e4.delete(0, END)
    e5.delete(0, END)
    finalBalVar.set(0)

def exit_command():
    if messagebox.askokcancel("Quit", "Do you really wish to quit?"):
        window.destroy()

#Display Labels
l1=Label(window, text="Years")
l1.grid(row=0, column=0)

l2=Label(window, text="Interest Rate")
l2.grid(row=1, column=0)

l3=Label(window, text="Final Balance")
l3.grid(row=2, column=0)

l4=Label(window, text="Initial Balance")
l4.grid(row=0, column=2)

l5=Label(window, text="Monthly Deposit")
l5.grid(row=1, column=2)

finalBalVar=StringVar()
finalBalVar.set(0)
l6=Label(window, textvariable=finalBalVar)
l6.grid(row=2, column=1)

#Display Text Fields
year=StringVar()
e1=Entry(window, textvariable=year)
e1.grid(row=0, column=1)

rate=StringVar()
e2=Entry(window, textvariable=rate)
e2.grid(row=1, column=1)

initial_balance=StringVar()
e4=Entry(window, textvariable=initial_balance)
e4.grid(row=0, column=3)

monthly_deposit=StringVar()
e5=Entry(window, textvariable=monthly_deposit)
e5.grid(row=1, column=3)

#Display listbox and attach a scrollbar
list1=Listbox(window, height=12, width=65)
list1.grid(row=3, column=0, rowspan=12, columnspan=3)

sb1=Scrollbar(window)
sb1.grid(row=3, column=3, rowspan=12, sticky=N+S+W)

list1.configure(yscrollcommand=sb1.set)
sb1.configure(command=list1.yview)

#Insert Display Buttons
b1=Button(window, text="Compute", width=12, command=compute_command)
b1.grid(row=3, column=4)
b2=Button(window, text="Clear", width=12, command=clear_command)
b2.grid(row=4, column=4)
b3=Button(window, text="Exit", width=12, command=exit_command)
b3.grid(row=5, column=4)

window.mainloop()
