import random

lvowels = ['a', 'e', 'i', 'o', 'u']
uvowels = ['A', 'E', 'I', 'O', 'U']
vowels = lvowels + uvowels

fname = input("Enter your first name: ")

new_name = ""
for i in range(len(fname)):
    if fname[i] not in vowels:
        # Handle a consonant
        new_name += fname[i]
        if i + 1 <= len(fname) and fname[i + 1] not in vowels:
            new_name += random.choice(lvowels)
    else:
        # Handle a vowel: Replace it with a randomly-chosen vowel
        if fname[i].isupper():
            new_name += random.choice(uvowels)
        else:
            new_name += random.choice(lvowels)

print(new_name)
