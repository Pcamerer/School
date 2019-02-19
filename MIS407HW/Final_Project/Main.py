from tkinter import *
import sqlite3
import edit_menu_functions as editMenu
from functools import partial
import csv

global conn, c
conn = sqlite3.connect('MIS407DB.sqlite')
c = conn.cursor()

root = Tk()
root.wm_title("User Selection")

# put all the functions here

# populating the menu table when the progam is ran for the first time
c.execute("CREATE TABLE IF NOT EXISTS menu (Menu_item_ID 'INTEGER' PRIMARY KEY AUTOINCREMENT, Name 'TEXT', Category 'TEXT', Price 'REAL')")
conn.commit()

c.execute("SELECT COUNT(*) FROM menu") # this is to prevent the table being poulated everytime the program is ran
menu_populated = c.fetchone()

global Data
if menu_populated[0] is 0:
    csv_file_name = "MenuFile.csv"
    with open(csv_file_name, newline='') as csvfile:
        Reader = csv.reader(csvfile)
        headers = next(Reader)
        Data = list(Reader)

        for row in Data:
            c.execute("INSERT INTO menu VALUES (NULL, '{}', '{}', {})".format(row[0], row[1], float(row[2])))
            conn.commit()



def Manager_Database():

    c.execute("CREATE TABLE IF NOT EXISTS manager (ID 'INTEGER' PRIMARY KEY AUTOINCREMENT, username 'TEXT' not null, password 'TEXT' not null)")
    conn.commit()
    c.execute("SELECT count(*) FROM manager")

    if 0 in c.fetchone():
        c.execute("INSERT INTO manager VALUES (NULL, 'admin', 'admin')")
        conn.commit()

    c.execute("SELECT * FROM manager WHERE username= '" + Username_text.get() + "' AND password = '" + Password_text.get() + "'")

    if c.fetchone() is not None:
        w2_l4.config(text="Success", fg="green") # can probably delete this
        manager_main_menu()
    else:
        w2_l4.config(text="Invalid username or password", fg="red")



def manager_login_window():
    global window2
    root.withdraw()
    window2 = Toplevel()

    window2.wm_title("Manager Log In")
    width = 426
    height = 170
    window2.geometry("%dx%d" % (width, height))


    w2_l1 = Label(window2, text="Manager Log In", width=12, font=('TkDefaultFont', 24))
    w2_l1.grid(row=0, column=1, columnspan=3)
    w2_l5 = Label(window2, text="", width=10)
    w2_l5.grid(row=0, column=4)
    w2_l2 = Label(window2, text="Username", width=10, font=('TkDefaultFont', 14))
    w2_l2.grid(row=2, column=0)
    w2_l3 = Label(window2, text="Password", width=10, font=('TkDefaultFont', 14))
    w2_l3.grid(row=3, column=0)
    global w2_l4
    w2_l4 = Label(window2)
    w2_l4.grid(row=5, column=1)
    w2_l6 = Label(window2, text="")
    w2_l6.grid(row=1, column=4)

    global Username_text
    Username_text = StringVar()
    w2_e1 = Entry(window2, textvariable=Username_text, width=35)
    w2_e1.grid(row=2, column=1, columnspan=3)
    global Password_text
    Password_text = StringVar()
    w2_e2 = Entry(window2, textvariable=Password_text, show="*", width=35)
    w2_e2.grid(row=3, column=1, columnspan=3)

    w2_b1 = Button(window2, text="Enter",  width=35, command=Manager_Database)
    w2_b1.grid(row=4, column=1)
    w2_b2 = Button(window2, text='Back', command=Back_to_user_selection_manager)
    w2_b2.grid(row=5, column=4)


def Back_to_user_selection_manager():
    window2.destroy()
    root.deiconify()

def close_credit_payment():
    window18.destroy()
    window17.destroy()
    window15.destroy()
    window14.destroy()
    window6.deiconify()

def close_cash_payment():

    window16.destroy()
    window15.destroy()
    window14.destroy()
    window6.deiconify()


def back_from_analytics():
    window13.destroy()
    window3.deiconify()

def Back_to_user_selection_server():
    window5.destroy()
    root.deiconify()

def Back_to_user_selection_from_manager_MM():
    window3.destroy()
    window2.destroy()
    root.deiconify()

def Back_to_user_selection_from_server_MM():
    logged_in_server = None
    window6.destroy()
    window5.destroy()
    root.deiconify()

def Back_from_add_server():
    window4.destroy()
    window3.deiconify()

def Back_from_remove_server():
    window7.destroy()
    window3.deiconify()

def back_from_edit_menu():
    window8.destroy()
    window3.deiconify()

def back_from_open_tab():
    window9.withdraw()
    window6.deiconify()

def back_from_close_tab():
    window14.withdraw()
    window6.deiconify()


def back_from_analytics():
    window13.destroy()
    window3.deiconify()

def back_from_input_an_order_second_window():
    w10_l2.config(text="")
    window11.withdraw()
    window10.deiconify()

def manager_main_menu():
    global window3
    window2.withdraw()
    window3 = Toplevel()

    window3.wm_title("Manager Main Menu")

    w3_l1= Label(window3, text='Manager Main Menu', width=16, font=('TkDefaultFont', 24))
    w3_l1.grid(row=0, column=0, columnspan=3)
    w3_l2= Label(window3, text='')
    w3_l2.grid(row=2, column=1)

    w3_b1 = Button(window3, text="Add Server", width=12, height=4, font=('TkDefaultFont', 16), command=add_server) # add a command
    w3_b1.grid(row=1, column=0, padx=(50,5), pady=(25,5))
    w3_b2 = Button(window3, text="Remove Server", width=12, height=4, font=('TkDefaultFont', 16), command=remove_server_window) # add a command
    w3_b2.grid(row=1, column=2, padx=(5,50), pady=(25,5))
    w3_b3 = Button(window3, text="Edit Menu", width=12, height=4, font=('TkDefaultFont', 16), command=edit_menu_screen) # add a command
    w3_b3.grid(row=2, column=0, padx=(50,5), pady=(5,50))
    w3_b4 = Button(window3, text="Run Analytics", width=12, height=4, font=('TkDefaultFont', 16), command=analytics_screen) # add a command
    w3_b4.grid(row=2, column=2, padx=(5,50), pady=(5,50))
    w3_b5= Button(window3, text="Log Out", width=6, command=Back_to_user_selection_from_manager_MM)
    w3_b5.grid(row=3, column=3)



def add_server_enter_button_command():
    if fname_text.get() in (None, "") or lName_text.get() in (None, ""):
        w4_l4.config(text="The first name and/or last name entry field is empty.", fg="red")
    else:
        c.execute("SELECT Server_ID FROM servers WHERE (fName='{}' AND lName='{}')".format(fname_text.get(), lName_text.get()))
        server_ID_precheck=c.fetchone()

        if server_ID_precheck is None:
            c.executescript("INSERT INTO servers VALUES (NULL, '{}', '{}', 0)".format(fname_text.get(), lName_text.get()))
            conn.commit()
            c.execute("SELECT Server_ID FROM servers WHERE (fName='{}' AND lName='{}')".format(fname_text.get(), lName_text.get()))
            added_server_ID=c.fetchone()
            print_server_ID = fname_text.get() + " " + lName_text.get() + " has been assigned ID number " + str(added_server_ID[0])
        else:
            print_server_ID = fname_text.get() + " " + lName_text.get() + " has ALREADY been assigned ID number " + str(server_ID_precheck[0])

        if "ALREADY" in print_server_ID:
             w4_l4.config(text=print_server_ID, fg="red")
        else:
            w4_l4.config(text=print_server_ID, fg="green")
        w4_e1.delete(0, END)
        w4_e2.delete(0, END)

def add_server():
    global window4
    window3.withdraw()
    window4 = Toplevel()

    c = conn.cursor()
    c.execute("CREATE TABLE IF NOT EXISTS servers (Server_ID 'INTEGER' PRIMARY KEY AUTOINCREMENT, fName 'TEXT', lName 'TEXT', Tip_balance 'REAL')")
    conn.commit()

    window4.wm_title("Add Servers")

    w4_l1 = Label(window4, text="Add New Server", width=20, font=('TkDefaultFont', 24))
    w4_l1.grid(row=0, column=0, columnspan=5, pady=(10,0))

    # Display Labels
    w4_l2=Label(window4, text="First Name")
    w4_l2.grid(row=1,column=0, pady=(30,0), padx=(30,0))
    w4_l3=Label(window4, text="Last Name")
    w4_l3.grid(row=1,column=2, pady=(30,0), padx=(30,0))
    global w4_l4
    w4_l4 = Label(window4)
    w4_l4.grid(row=3, column=0, columnspan=5, pady=(10,0))

    # Display text entry fields
    global fname_text
    fname_text=StringVar()
    global w4_e1
    w4_e1=Entry(window4,textvariable=fname_text, width=25)
    w4_e1.grid(row=1,column=1, pady=(30,0))
    global lName_text
    lName_text=StringVar()
    global w4_e2
    w4_e2=Entry(window4,textvariable=lName_text, width=25)
    w4_e2.grid(row=1,column=3, pady=(30,0), padx=(0,30))

    # Display Buttons
    w4_b1=Button(window4, text="Enter", width=15, command=add_server_enter_button_command)
    w4_b1.grid(row=2, column=0,columnspan=5, pady=(30,0))
    w4_b2=Button(window4, text="Back", command=Back_from_add_server)
    w4_b2.grid(row=3, column=4, sticky=SE)

def remove_server_button():
    index = w7_list1.curselection()[0] # may need to make changes here, as it seems selected_tuple[0] doesnt work for double digit numbers
    selected_tuple = w7_list1.get(index)
    if selected_tuple[0] is "I":
        pass
    else:
        c.execute("DELETE FROM servers WHERE Server_ID=" + selected_tuple[:2])
        conn.commit()
        w7_list1.delete(index)

def remove_server_window():
    global window7
    window3.withdraw()
    window7 = Toplevel()

    window7.wm_title("Server Main Menu")

    w7_l1= Label(window7, text='Remove Servers', width=16, font=('TkDefaultFont', 24))
    w7_l1.grid(row=0, column=0, columnspan=3)

    global w7_list1
    w7_list1=Listbox(window7,height=30, width=50)
    w7_list1.grid(row=1, column=0, columnspan=3)

    w7_b1 = Button(window7, text="Remove", width=9, height=3, font=('TkDefaultFont', 16), command=remove_server_button) # add a command
    w7_b1.grid(row=1, column=3, sticky=N, padx=5)
    w7_b1.bind("<<ListboxSelect>>", remove_server_button)
    w7_b2=Button(window7, text="Back", command=Back_from_remove_server)
    w7_b2.grid(row=2, column=3, sticky=SE)

    c=conn.cursor()
    c.execute("SELECT * FROM servers")
    all_rows_server=c.fetchall()

    w7_list1.insert(END,"{:<5s} {:^30s} {:^30s}".format("ID", "First Name", "Last Name"))
    for row in all_rows_server:
        w7_list1.insert(END,"{:<5d} {:^30s} {:^30s}".format(row[0], row[1], row[2]))

def edit_menu_screen():
    global window8
    window3.withdraw()
    window8 = Toplevel()

    window8.wm_title("Edit Menu")

    l1=Label(window8, text="Category")
    l1.grid(row=0,column=0)
    l2=Label(window8, text="Name")
    l2.grid(row=1,column=0)
    l3=Label(window8, text="Price")
    l3.grid(row=2,column=0)

    global category
    category=StringVar()
    global cat_entry
    cat_entry=Entry(window8,textvariable=category,width=24)
    cat_entry.grid(row=0,column=1, columnspan=2)
    global name
    name=StringVar()
    global name_entry
    name_entry=Entry(window8,textvariable=name, width=24)
    name_entry.grid(row=1,column=1, columnspan=2)
    global price
    price=StringVar()
    global price_entry
    price_entry=Entry(window8,textvariable=price, width=24)
    price_entry.grid(row=2,column=1, columnspan=2)

    global w8_list1
    w8_list1=Listbox(window8,height=6, width=50)
    w8_list1.grid(row=0, column=3, rowspan=20, columnspan=5 )
    w8_list1.bind("<<ListboxSelect>>", get_selected_row)

    sb1 = Scrollbar(window8)
    sb1.grid(row=0, column=9, rowspan=10, columnspan=3, sticky=N+S+E)

    w8_list1.configure(yscrollcommand=sb1.set)
    sb1.configure(command=w8_list1.yview)

    b1=Button(window8, text="All Menu Items", width=12, command= lambda: edit_menu("View")) ##view menu from edit_menu_functions
    b1.grid(row=5, column=1)
    b2=Button(window8, text="Search Menu ", width=12, command = lambda: edit_menu("Search"))
    b2.grid(row=5, column=2)
    b3=Button(window8, text="Add Item", width=12, command= lambda: edit_menu("Add"))
    b3.grid(row=6, column=1)
    b4=Button(window8, text="Update Item", width=12, command= lambda :edit_menu("Update"))
    b4.grid(row=6, column=2)
    b5=Button(window8, text="Delete Item", width=12, command= lambda: edit_menu("Delete"))
    b5.grid(row=7, column=1)
    b6=Button(window8, text="Back", width=12, command=back_from_edit_menu)
    b6.grid(row=7, column=2)

    cur=conn.cursor()
    cur.execute("SELECT * FROM menu ORDER BY Category")
    rows = cur.fetchall()
    w8_list1.delete(0, END)
    for row in rows:
        w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))


def edit_menu(buttonName):
    cur=conn.cursor()
    MenuQuery = "SELECT * FROM menu ORDER BY Category"
    cur.execute(MenuQuery)
    menu = cur.fetchall()
    if buttonName == "View":
        w8_list1.delete(0, END)
        for row in menu:
            w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))
    elif buttonName == "Add":
        editMenu.insert(category.get(), name.get(), price.get())
        w8_list1.delete(0, END)
        cur.execute(MenuQuery)
        menu = cur.fetchall()
        for row in menu:
            w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))
    elif buttonName == "Delete":
        editMenu.delete(selected_id)
        w8_list1.delete(0, END)
        for row in menu:
            w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))
    elif buttonName == "Update":
        editMenu.update(selected_id, category.get(), name.get(), price.get())
        w8_list1.delete(0, END)
        cur.execute(MenuQuery)
        menu = cur.fetchall()
        for row in menu:
            w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))
    elif buttonName == "Search":
        w8_list1.delete(0, END)
        for row in editMenu.search(category.get(), name.get()):
            w8_list1.insert(END, "{} {} {} {}".format(row[0], row[2], row[1], row[3]))
    cat_entry.delete(0, END)
    name_entry.delete(0, END)
    price_entry.delete(0, END)


def get_selected_row(event):
    index = w8_list1.curselection()[0]
    selected_tuple = w8_list1.get(index)
    id = selected_tuple[0]+selected_tuple[1]
    global selected_id
    selected_id=int(id.strip())

    conn = sqlite3.connect('MIS407DB.sqlite')
    cur = conn.cursor()
    cur.execute("SELECT * FROM menu WHERE Menu_item_ID = ?", (selected_id,))
    conn.commit()
    rows = cur.fetchall()
    for row in rows:
        cat_entry.delete(0,END)
        cat_entry.insert(END, row[2])
        name_entry.delete(0,END)
        name_entry.insert(END, row[1])
        price_entry.delete(0,END)
        price_entry.insert(END, row[3])


def server_login_enter_button_command():
    c = conn.cursor()
    if Server_ID_text.get().isdigit() is False:
        w5_l3.config(text='Invalid Server ID. Please try entering a number.', fg="red")
    else:
        c.execute("SELECT Server_ID FROM servers WHERE Server_ID= {}".format(int(Server_ID_text.get())))
        server_ID_login_precheck=c.fetchone()

        if server_ID_login_precheck is None:
             w5_l3.config(text='Invalid Server ID.', fg="red")
        else:
            global logged_in_server
            logged_in_server=Server_ID_text.get()
            w5_l3.config(text='success', fg="green") # can probably delete this line
            server_main_menu()


def server_login_window():
    global window5
    root.withdraw()
    window5 = Toplevel()

    window5.wm_title("Server Log In")

    w5_l1= Label(window5, text='Server Log In', width=16, font=('TkDefaultFont', 28))
    w5_l1.grid(row=0, column=0, columnspan=3, pady=(0,20))
    w5_l2= Label(window5, text='Server ID', font=('TkDefaultFont', 16))
    w5_l2.grid(row=1, column=0)
    global w5_l3
    w5_l3= Label(window5)
    w5_l3.grid(row=3, column=1)

    global Server_ID_text
    Server_ID_text = StringVar()
    global w5_e1
    w5_e1=Entry(window5, textvariable=Server_ID_text, show="*", font=('TkDefaultFont', 16))
    w5_e1.grid(row=1, column=1)

    w5_b1 = Button(window5, text="Enter", width=15, font=('TkDefaultFont', 12), command=server_login_enter_button_command)
    w5_b1.grid(row=2, column=1, pady=(20,0), padx=(0,20))
    w5_b2 = Button(window5, text='Back', font=('TkDefaultFont', 12), command=Back_to_user_selection_server)
    w5_b2.grid(row=3, column=2, pady=(20,0))

def server_main_menu():
    global window6
    window5.withdraw()
    window6 = Toplevel()
    window6.wm_title("Server Main Menu")

    w6_l1= Label(window6, text='Server Main Menu', width=16, font=('TkDefaultFont', 24))
    w6_l1.grid(row=0, column=0, columnspan=3)
    w6_l2= Label(window6, text='')
    w6_l2.grid(row=2, column=1)

    w6_b1 = Button(window6, text="Start a Tab", width=12, height=4, font=('TkDefaultFont', 16), command=open_tab_window) # add a command
    w6_b1.grid(row=1, column=0, padx=(50,5), pady=(25,5))
    w6_b2 = Button(window6, text="Input an Order", width=12, height=4, font=('TkDefaultFont', 16), command=input_an_order_first_window) # add a command
    w6_b2.grid(row=1, column=2, padx=(5,50), pady=(25,5))
    w6_b3 = Button(window6, text="Print a Bill", width=12, height=4, font=('TkDefaultFont', 16), command=print_tab) # add a command
    w6_b3.grid(row=2, column=0, padx=(50,5), pady=(5,50))
    w6_b4 = Button(window6, text="Close a Tab", width=12, height=4, font=('TkDefaultFont', 16), command=close_tab_window) # add a command
    w6_b4.grid(row=2, column=2, padx=(5,50), pady=(5,50))
    w6_b5= Button(window6, text="Log Out", width=6, command=Back_to_user_selection_from_server_MM)
    w6_b5.grid(row=3, column=3)

def open_tab_button():
    open_tab_index = w9_list1.curselection()[0]
    selected_tuple = w9_list1.get(open_tab_index)

    c.execute("UPDATE tables SET occupied_Y_N='Y', server_id=" + logged_in_server + " WHERE Table_number=" + selected_tuple[:2])
    conn.commit()
    w9_list1.delete(open_tab_index)
    c.execute("CREATE TABLE IF NOT EXISTS tabs (Tab_ID 'INTEGER' PRIMARY KEY AUTOINCREMENT, Table_number 'INTEGER', Balance 'REAL', Open_or_closed 'TEXT', Server_ID 'INTEGER', FOREIGN KEY(Server_ID) REFERENCES servers(Server_ID), FOREIGN KEY(Table_number) REFERENCES tables(Table_number))") # will need to update the FK stuff here
    conn.commit()
    c.execute("INSERT INTO tabs VALUES (NULL, '{}', 0, 'O', {})".format(selected_tuple[:2], logged_in_server))
    conn.commit()

def open_tab_window():
    global window9
    window6.withdraw()
    window9 = Toplevel()
    window9.wm_title("Open Tab")

    w9_l1= Label(window9, text='Availabe Tables', width=16, font=('TkDefaultFont', 24))
    w9_l1.grid(row=0, column=0, columnspan=3)

    global w9_list1
    w9_list1=Listbox(window9,height=30, width=50)
    w9_list1.grid(row=1, column=0, columnspan=3)

    w9_b1 = Button(window9, text="Open Tab", width=9, height=3, font=('TkDefaultFont', 16), command=open_tab_button)
    w9_b1.grid(row=1, column=3, sticky=N, padx=5)
    w9_b1.bind("<<ListboxSelect>>", open_tab_button)
    w9_b2=Button(window9, text="Back", command=back_from_open_tab)
    w9_b2.grid(row=2, column=3, sticky=SE)


    c.execute("CREATE TABLE IF NOT EXISTS tables (Table_number 'INTEGER' PRIMARY KEY AUTOINCREMENT, occupied_Y_N 'TEXT', server_id 'INTEGER', FOREIGN KEY(server_id) REFERENCES servers(Server_ID))")
    conn.commit()

    c.execute("SELECT count(*) FROM tables")
    if 0 in c.fetchone():
        for i in range(20): #this creates all the tables in the restaurant. Would change the 20 if we wanted something differern
            c.execute("INSERT INTO tables VALUES (NULL, 'N', NULL)")
            conn.commit()
    c.execute("SELECT Table_number FROM tables WHERE occupied_Y_N='N'")
    all_rows=c.fetchall()

    for row in all_rows:
        w9_list1.insert(END,"{:<10d} ".format(int(row[0])))

def back_from_input_an_order_first_window():
    window10.withdraw()
    window6.deiconify()

def input_an_order_first_window():
    global window10
    window6.withdraw()
    window10 = Toplevel()

    window10.wm_title("Input an Order")

    w10_l1= Label(window10, text='Occupied Tables', width=16, font=('TkDefaultFont', 24))
    w10_l1.grid(row=0, column=0, columnspan=3)
    global w10_l2
    w10_l2=Label(window10)
    w10_l2.grid(row=0, column=3)

    global w10_list1
    w10_list1=Listbox(window10,height=30, width=50)
    w10_list1.grid(row=1, column=0, columnspan=3)

    w10_b1 = Button(window10, text="Select Table", width=9, height=3, font=('TkDefaultFont', 16), command=input_an_order_second_window) # add command that takes you to the stuff Preston has bee doing
    w10_b1.grid(row=1, column=3, sticky=N, padx=5)
    w10_b1.bind("<<ListboxSelect>>", input_an_order_second_window)
    w10_b2=Button(window10, text="Back", command=back_from_input_an_order_first_window)
    w10_b2.grid(row=2, column=3, sticky=SE)

    c.execute("SELECT Table_number FROM tables WHERE (occupied_Y_N='Y' AND server_id=" + str(logged_in_server) +")")
    occupied_tables_all_rows=c.fetchall()

    for row in occupied_tables_all_rows:
        w10_list1.insert(END,"{:<10d} ".format(int(row[0])))


def show_food(n):
    w11_list1.delete(0, END) # clear the contents of the list box everytime ...
    # a new button is clicked so it is empty before new data is inserted

    button_ID = (button_identities[n])
    category = button_ID['text']  # this line is used to figure out what button was pushed

    c = conn.cursor()
    c.execute("SELECT * FROM menu WHERE category='{}'".format(category))
    all_rows = c.fetchall()

    w11_list1.insert(END,"{:<40s} {:>15s}".format("Name", "Price"))
    for row in all_rows:
        w11_list1.insert(END,"{:<40s} {:>15.2f}".format(row[1], row[3]))

def add_to_order():
     if len(w11_list1.curselection()) == 0:
         w11_l1.config(text="Please select a menu item", fg="red")
     else:
        add_to_order_index = w11_list1.curselection()
        add_to_order_selected_tuple = w11_list1.get(add_to_order_index)
        stripped_menu_item=add_to_order_selected_tuple[:40].rstrip()
        c.execute("CREATE TABLE IF NOT EXISTS tab_order_line (ID 'INTEGER' PRIMARY KEY AUTOINCREMENT, Item_ordered_ID 'TEXT', Tab_ID 'INTEGER', FOREIGN KEY(Tab_ID) REFERENCES tabs(Tab_ID), FOREIGN KEY(Item_ordered_ID) REFERENCES menu(Menu_item_ID))")
        conn.commit()
        c.execute("SELECT Tab_ID FROM tabs WHERE (Table_number =" + selected_table_for_order + " AND Open_or_closed='O')")
        tab_id_for_item_order=c.fetchone()
        c.execute("SELECT Menu_item_ID FROM menu WHERE Name='{}'".format(stripped_menu_item))
        menu_item_id_for_item_order=c.fetchone()
        c.execute("INSERT INTO tab_order_line VALUES (NULL, {}, {})".format(str(menu_item_id_for_item_order[0]), str(tab_id_for_item_order[0])))
        c.execute("SELECT balance FROM tabs WHERE Tab_ID=" + str(tab_id_for_item_order[0]))
        current_balance=c.fetchone()[0]
        c.execute("SELECT Price FROM menu WHERE Menu_item_ID=" + str(menu_item_id_for_item_order[0]))
        price=c.fetchone()[0]
        new_balance = current_balance + price
        c.execute("UPDATE tabs SET balance=" + str(new_balance) + " WHERE Tab_ID=" + str(tab_id_for_item_order[0]))
        w11_l1.config(text="Added " + stripped_menu_item + " to the tab!", fg="green")


def input_an_order_second_window():

    if len(w10_list1.curselection()) == 0:
        w10_l2.config(text="Please select a table", fg="red")
    else:
        table_for_input_order_index = w10_list1.curselection()
        selected_tuple = w10_list1.get(table_for_input_order_index)
        global selected_table_for_order
        selected_table_for_order=selected_tuple[:2] #this will be needed for adding items to the a tab

        global window11
        window10.withdraw()
        window11=Toplevel()

        global w11_l1
        w11_l1=Label(window11)
        w11_l1.grid(row=10, column=2)

        #buttons------------------------------------------------------------------------
        w11_b1=Button(window11, text="Add To Tab", width=12, height=4, command=add_to_order)
        w11_b1.grid(row=10, column=4, pady=(50,0))
        w11_b1.bind("<<ListboxSelect>>", add_to_order)

        w11_b2=Button(window11, text="Back", command=back_from_input_an_order_second_window)
        w11_b2.grid(row=12, column=5, sticky=SE)

        buttonNameList = []
        global button_identities
        button_identities = []
        categoryNum = 0
        row_count=0
        c = conn.cursor()
        c.execute("SELECT DISTINCT category FROM menu")
        categories=c.fetchall()
        for category in categories:
                buttonNameList.append("Button" + str(categoryNum))
                if categoryNum is 0:
                    buttonNameList[categoryNum]=Button(window11, text=category[0], width=12, height=4, command=partial(show_food, categoryNum))
                    buttonNameList[categoryNum].grid(row=0, column=4)
                    button_identities.append(buttonNameList[categoryNum])
                    categoryNum += 1
                elif categoryNum > 0 and categoryNum % 2 != 0:
                    buttonNameList[categoryNum]=Button(window11, text=category[0],  width=12, height=4, command=partial(show_food, categoryNum))
                    buttonNameList[categoryNum].grid(row=row_count, column=5)
                    button_identities.append(buttonNameList[categoryNum])
                    categoryNum += 1
                    row_count +=1
                else:
                    buttonNameList[categoryNum]=Button(window11, text=category[0],  width=12, height=4, command=partial(show_food, categoryNum))
                    buttonNameList[categoryNum].grid(row=row_count, column=4)
                    button_identities.append(buttonNameList[categoryNum])
                    categoryNum += 1

        #Listbox & Scrollbar------------------------------------------------------------
        global w11_list1
        w11_list1=Listbox(window11, height=15, width=60,  font=("Consolas",11))
        w11_list1.grid(row=0, column=0, rowspan=4, columnspan=3)


def analytics_screen():

    global window13
    window3.withdraw()
    window13 = Toplevel()
    window13.wm_title("Store Analytics")

    global w13_list1
    w13_list1=Listbox(window13,height=6, width=75,  font=("Consolas",11))
    w13_list1.grid(row=1, column=0, rowspan=20, columnspan=5 )


    w13_l1=Label(window13, text="Group By:", width=6) ##view menu from edit_menu_functions
    w13_l1.grid(row=0, column=0)
    tkvar = StringVar(window13)
    tkvar2 = StringVar(window13)
    GroupBychoices = {'Server', 'Items', 'Category'}
    tkvar.set('Server')
    GroupByOptionMenu=OptionMenu(window13, tkvar, *GroupBychoices)
    GroupByOptionMenu.grid(row=0, column=1)
    w13_b4=Button(window13, text="Back", width=12, command=back_from_analytics)
    w13_b4.grid(row=1, column=6)
    w13_b5=Button(window13, text="Filter Data", width=12, command=lambda: analytics_buttons(tkvar.get()))
    w13_b5.grid(row=0, column=6)
    sb1 = Scrollbar(window13)
    sb1.grid(row=0, column=9, rowspan=10, columnspan=3, sticky=N+S+E)

    w13_list1.configure(yscrollcommand=sb1.set)
    sb1.configure(command=w13_list1.yview)


def analytics_buttons(groupBy):
    cur=conn.cursor()
    if groupBy == "Server":
        input1 = "s.fname"
        query = "SELECT s.fname, COUNT(m.price), SUM(m.price) FROM tab_order_line ol JOIN menu m ON ol.Item_ordered_ID = m.Menu_item_ID JOIN tabs t ON ol.Tab_ID = t.Tab_ID JOIN servers s ON t.server_id = s.server_id GROUP BY {} ORDER BY COUNT(m.price) DESC ".format(input1,)
        cur.execute(query)
        results = cur.fetchall()
        w13_list1.delete(0, END)
        w13_list1.insert(END, "{:30} {:20} {:20}".format("Name", "Num Transactions", "Total Amount"))
        for row in results:
            w13_list1.insert(END, "{:20} {:20} {:20.2f}".format(row[0], row[1], row[2]))
    elif groupBy == "Items":
        input1 = "m.name"
        query = "SELECT m.name, COUNT(m.price), SUM(m.price) FROM tab_order_line ol JOIN menu m ON ol.Item_ordered_ID = m.Menu_item_ID JOIN tabs t ON ol.Tab_ID = t.Tab_ID JOIN servers s ON t.server_id = s.server_id GROUP BY {} ORDER BY COUNT(m.price) DESC ".format(input1,)
        cur.execute(query)
        results = cur.fetchall()
        w13_list1.delete(0, END)
        w13_list1.insert(END, "{:30} {:20} {:20}".format("Item Name", "Num Transactions", "Total Amount"))
        for row in results:
            w13_list1.insert(END, "{:20} {:20} {:20.2f}".format(row[0], row[1], row[2]))
    elif groupBy == "Category":
        input1 = "m.category"
        query = "SELECT m.category, COUNT(m.price), SUM(m.price) FROM tab_order_line ol JOIN menu m ON ol.Item_ordered_ID = m.Menu_item_ID JOIN tabs t ON ol.Tab_ID = t.Tab_ID JOIN servers s ON t.server_id = s.server_id GROUP BY {} ORDER BY COUNT(m.price) DESC ".format(input1,)
        cur.execute(query)
        results = cur.fetchall()
        w13_list1.delete(0, END)
        w13_list1.insert(END, "{:30} {:20} {:20}".format("Category", "Num Transactions", "Total Amount"))
        for row in results:
            w13_list1.insert(END, "{:20} {:20} {:20.2f}".format(row[0], row[1], row[2]))

def payment_window():
    global window15
    window15 = Toplevel()
    window15.wm_title("Payment")
    w15_l1= Label(window15, text='Payment', width=16, font=('TkDefaultFont', 24))
    w15_l1.grid(row=0, column=0, columnspan=3)

    close_tab_index = w14_list1.curselection()[0]
    selected_tuple = w14_list1.get(close_tab_index)
    c.execute("SELECT Tab_ID FROM tabs WHERE (Table_number =" + str(selected_tuple[:2]) + " AND Open_or_closed='O')")
    tab_id_for_close_tab=c.fetchone()

    close_tab_index = w14_list1.curselection()[0]
    selected_tuple = w14_list1.get(close_tab_index)
    c.execute("SELECT Tab_ID FROM tabs WHERE (Table_number =" + str(selected_tuple[:2]) + " AND Open_or_closed='O')")
    tab_id_for_close_tab=c.fetchone()
    global close_tab_id
    close_tab_id=tab_id_for_close_tab[0]


    w15_b1 = Button(window15, text="Cash", width=9, height=3, font=('TkDefaultFont', 16), command=cash_window)
    w15_b1.grid(row=1, column=1, sticky=N, padx=5)

    w15_b2=Button(window15, text="Credit", width=9, height=3, font=('TkDefaultFont', 16), command=credit_window)
    w15_b2.grid(row=1, column=3, sticky=SE, padx=5)

    c.execute("UPDATE tables SET occupied_Y_N='N', server_id=" + logged_in_server + " WHERE Table_number=" + selected_tuple[:2])
    conn.commit()



def cash_window():
    global window16
    window16 = Toplevel()
    window16.wm_title("Cash Payment")

    def Calculate():
        a = float(Payment.get())
        b = float(Total.get())
        e = a - b
        e = round(e, 2)

        Change.set(str(e))

        c.execute("UPDATE tabs SET balance=" + "0" + " WHERE Tab_ID=" + str(close_tab_id))
        conn.commit()



    w16_l112= Label(window16, text='Cash Payment', width=16, font=('TkDefaultFont', 24))
    w16_l112.grid(row=0, column=0, columnspan=3)
    w16_b1 = Button(window16, text="Calculate", command=Calculate)
    w16_b1.grid(row=4, column=0)

    w16_l2=Label(window16, text="Payment")
    w16_l2.grid(row=1,column=0)


    w16_l1=Label(window16, text="Total")
    w16_l1.grid(row=2,column=0)



    w16_l3=Label(window16, text="Change")
    w16_l3.grid(row=3, column=0)



    Payment = StringVar()
    w16_e1 = Entry(window16, textvariable=Payment, width=10)
    w16_e1.grid(row=1, column=1)
    Total = StringVar()
    c.execute("SELECT balance FROM tabs WHERE Tab_ID=" + str(close_tab_id))
    total_balance=c.fetchone()[0]
    w16_e2 = Entry(window16, textvariable=Total, width=10, state="readonly")
    w16_e2.grid(row =2, column=1)
    Total.set(str(round(total_balance*1.07,2)))
    Change = StringVar()
    w16_e3 = Entry(window16, textvariable=Change, width=10, state="readonly")
    w16_e3.grid(row=3, column=1)

    w16_b2= Button(window16, text="Exit",command=close_cash_payment)
    w16_b2.grid(row=4, column=1)



    #update tabs table


def print_tab():
    global window12
    window6.withdraw()
    window12=Toplevel()
    window12.wm_title("Print Bill")

    w12_l1= Label(window12, text='List of Tabs Opened', width=16, font=('TkDefaultFont', 24))
    w12_l1.grid(row=0, column=0, columnspan=3)

    global w12_list1
    w12_list1=Listbox(window12,height=30, width=50)
    w12_list1.grid(row=1, column=0, columnspan=3)

    w12_b1 = Button(window12, text="Print Tab", width=9, height=3, font=('TkDefaultFont', 16), command=print_tab_receipt)
    w12_b1.grid(row=1, column=3, sticky=N, padx=5)
    w12_b1.bind("<<ListboxSelect>>", print_tab_receipt)
    w12_b2=Button(window12, text="Back", command=back_from_print_tab)
    w12_b2.grid(row=2, column=3, sticky=SE)

    c.execute("SELECT Table_number FROM tables WHERE occupied_Y_N='Y'")
    all_rows=c.fetchall()

    for row in all_rows:
        w12_list1.insert(END,"{:<10d} ".format(int(row[0])))

def print_tab_receipt():
    open_tab_index = w12_list1.curselection()[0]
    selected_tuple = w12_list1.get(open_tab_index)

    c.execute("SELECT Tab_ID FROM tabs WHERE (Table_number =" + str(selected_tuple[:2]) + " AND Open_or_closed='O')")
    this_tuple=c.fetchone()

    c.execute("SELECT menu.Name, menu.price FROM menu, tab_order_line, tabs WHERE (tab_order_line.Item_ordered_ID=menu.Menu_item_ID AND tab_order_line.Tab_ID=tabs.Tab_ID AND tabs.Tab_ID={})".format(str(this_tuple[0])))
    items_orderd=c.fetchall()

    with open("Tab" + str(this_tuple[0]) + ".txt", "w") as f:
        for item in items_orderd:
            f.write("{:<40s} ${}\n".format(item[0], item[1]))
        c.execute("SELECT balance FROM tabs WHERE Tab_ID={}".format(str(this_tuple[0])))
        balance_printed=c.fetchone()
        f.write("{:<40s} -------\n".format(""))
        f.write("{:<40s} ${}\n".format("Sub Total", balance_printed[0]))
        tax=float(balance_printed[0]) * .07
        f.write("{:<40s} ${}\n".format("Tax", round(tax,2)))
        f.write("{:<40s} -------\n".format(""))
        bill_total=(float(balance_printed[0]) + float(tax))
        f.write("{:<40s} ${}\n".format("Total", round(bill_total,2)))

def back_from_print_tab():
    window12.withdraw()
    window6.deiconify()


def credit_window():
    global window17
    window17 = Toplevel()
    window17.wm_title("Credit Payment")
    def swiped():
        def designated_tip():
            #update servers table with Tip_balance
            c.execute("SELECT Tip_balance FROM servers WHERE Server_id=" + logged_in_server)
            current_balance=c.fetchone()[0]

            new_balance = current_balance +float(Amount.get())
            c.execute("UPDATE servers SET Tip_balance=" + str(new_balance) + " WHERE Server_id=" + logged_in_server)
            Tip.set("Thank You")
        global window18
        window18 = Toplevel()
        Status = StringVar()
        w17_l112= Label(window18, text='Payment Successful', width=16, font=('TkDefaultFont', 24))
        w17_l112.grid(row=0, column=0, columnspan=3)
        Tip=StringVar()
        Tip.set("Designated Tip")
        w18_e2=Entry(window18, textvariable=Tip, width=20, state="readonly")
        w18_e2.grid(row=1, column=0)
        Amount=StringVar()
        w18_e3=Entry(window18, textvariable=Amount, width=20)
        w18_e3.grid(row=1, column=1)
        w18_b3=Button(window18, text="Confirm", command=designated_tip)
        w18_b3.grid(row=1, column=2)
        w18_b2= Button(window18, text="Exit",command=close_credit_payment)
        w18_b2.grid(row=2, column=2)
        c.execute("UPDATE tabs SET balance=" + "0" + " WHERE Tab_ID=" + str(close_tab_id))
        conn.commit()




    w17_l1= Label(window17, text='Please Swipe Card', width=16, font=('TkDefaultFont', 24))
    w17_l1.grid(row=0, column=0, columnspan=3)
    w17_b1 = Button(window17, text="Completed", width=9, height=3, font=('TkDefaultFont', 16), command=swiped)
    w17_b1.grid(row=1, column=3, sticky=N, padx=5)


    #update tabs table

    Tip.set("Thank You")

def close_tab_window():
    global window14
    window6.withdraw()
    window14 = Toplevel()
    window14.wm_title("Close Tab")


    w14_l1= Label(window14, text='Occupied Tables', width=16, font=('TkDefaultFont', 24))
    w14_l1.grid(row=0, column=0, columnspan=3)

    global w14_list1
    w14_list1=Listbox(window14,height=30, width=50)
    w14_list1.grid(row=1, column=0, columnspan=3)

    w14_b1 = Button(window14, text="close Tab", width=9, height=3, font=('TkDefaultFont', 16), command=payment_window)
    w14_b1.grid(row=1, column=3, sticky=N, padx=5)
    w14_b1.bind("<<ListboxSelect>>", payment_window)
    w14_b3=Button(window14, text="Back", command=back_from_close_tab)
    w14_b3.grid(row=2, column=3, sticky=SE)




    c.execute("SELECT Table_number FROM tables WHERE (occupied_Y_N='Y' AND server_id=" + str(logged_in_server) +")")
    all_rows=c.fetchall()

    for row in all_rows:
        w14_list1.insert(END,"{:<10d} ".format(int(row[0])))




window1 = Frame(root, height=200)
window1.pack(side=TOP, pady=20)

w1_l1 = Label(window1, text="", width=10)
w1_l1.grid(row=0, column=0)
w1_l2 = Label(window1, text="", width=10)
w1_l2.grid(row=4, column=2)
w1_l3 = Label(window1, text="")
w1_l3.grid(row=2, column=1)

w1_b1 = Button(window1, text="Manager",  width=15, command=manager_login_window)
w1_b1.grid(row=1, column=1, sticky=S)
w1_b3 = Button(window1, text="Server",  width=15, command=server_login_window)
w1_b3.grid(row=3, column=1, sticky=N)


root.mainloop()
