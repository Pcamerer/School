# http://127.0.0.1:8080/

from flask import Flask, render_template, request

app = Flask(__name__)


@app.route('/')
def home():
    return render_template("savings-form.html")

@app.route('/compute', methods=['POST'])
def compute():

    rate = request.form['rate']
    years = request.form['years']
    initial_balance = request.form['initial_balance']
    monthly_deposit = request.form['monthly_deposit']
    savings_table = []

    i=0
    balance=0

    monthly_rate = float(rate) / 12.0
    months = int(years) * 12

    current_balance=float(initial_balance)
    interest=float(initial_balance) * monthly_rate


    for i in range(0, months):
        interest=float(current_balance) * monthly_rate
        current_balance+=float(monthly_deposit) + interest

        new_dict = {'month': str(i+1), 'deposit': monthly_deposit, 'interest': interest, 'balance': current_balance}

        savings_table.append(new_dict)

    balance+=current_balance



    return render_template("savings-table.html", rate=rate, years=years,
                           monthly_deposit=monthly_deposit,
                           final_balance=balance,
                           savings_table=savings_table)


if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=8080)
