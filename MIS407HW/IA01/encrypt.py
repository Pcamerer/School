alph_main = 'abcdefghijklmnopqrstuvwxyz '

enter = raw_input('Enter key (26 letters): ')
enter = enter.lower()
alph = enter + ' '

def encrypt(c):
    i = alph_main.find(c)
    return alph[i]

error = 0

if len(enter) != 26:
    print("Error: key must be 26 letters")
    error = 1

output = ""

message = raw_input("Message to Encrypt: ")

if error != 1:
    for i in message:
        output = output + encrypt(i)

print("Encrypted Text: " + output)
