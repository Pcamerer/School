import itertools

def anagram(text):
    for w in itertools.permutations(word):
        yield ''.join(w)

text = input("Enter word to search: ")
with open('dictionary_words.txt', 'r') as f:
    for line in f:
        word = line.rstrip('\n')
    for w in anagram(text):
        if w in line:
            print(w)
