from huffman import Huffman
import random

def readfile( filename ):
    """Returns an 2*N matrix"""
    with open( filename , 'r') as f:
        rows = []
        for line in f.readlines():
            cells = line.rstrip().split(' ')
            rows.append([cells[0], float(cells[1])])
    return rows


def sequence( probs, length=1 ):
    offset = 0
    new_probs = {}
    ## first we discretize the probabilities
    for character, probability in probs:
        new_probs[character] = [offset, offset + probability]
        offset += probability

    #print new_probs
    new_sequence = ''  
    ## genrates as many random numbers as wanted
    for _ in range(length):
        number = random.randint(0, 100)
        ## searchs for the character based on the probability
        for key, value in new_probs.items():
            ## if the number if between the range
            if number > value[0] and number <= value[1]:
                new_sequence += key
                
    return new_sequence


## Loads the library
encoder = Huffman()

## A)
simbolos = [
    ['R', 30.],
    ['K', 20.],
    ['Q', 20.],
    ['J', 15.],
    ['10', 10.],
    ['9', 5.]
    ]
huffman_table = encoder.codify(simbolos)
print "A)"
print "The huffman table"
for line in sorted(huffman_table.items(), key=lambda t:t[0], reverse=True):
    print "%s %s" %(line[0], line[1])

## B)
text = 'RKQJQJQJQ10K10R9'
print
print "B)"
print "Codifying the text: %s" % text
print encoder.translate(text, huffman_table)


## C)
length = 20
print
print "C)"
print "Generating a random sequence of length %s:" % length
print sequence(simbolos, length)

## D)
# TODO
length = 100000
secuencia = sequence(simbolos, length)
print "El factor de compresion en esta secuencia de %i caracteres es:" % length
print "%f / %f bits = %f" %(length*7, len(secuencia), length*7./len(secuencia))

