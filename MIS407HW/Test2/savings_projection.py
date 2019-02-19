ibal = float(input("Enter initial account balance: "))
year = float(input("Enter the number of years to simulate: "))
interest = float(input("Enter the anual interest rate: "))
deposit = float(input("Enter the monthly amount deposited to the account: "))
currentbal = ibal

montly_interest_rate = interest/12


for y in range(1, 13):
    monthly_interest = montly_interest_rate * currentbal
    currentbal += monthly_interest
    print((y+1) + float(monthly_interest) + float(currentbal))
