from huffman import Huffman
import random
import math

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


def entropy(table):
    norm_table = [ x[1]/100. for x in table]
    return -math.fsum([ prob * math.log(prob, 2) for prob in norm_table])

## Loads the library
encoder = Huffman()

simbolos = [
    ['R', 30.],
    ['K', 20.],
    ['Q', 20.],
    ['J', 15.],
    ['10', 10.],
    ['9', 5.]
    ]

entropia = entropy(simbolos)
##
print
print
print "Simbols ", simbolos
print "Entropia %.4f" % entropia
## A)
huffman_table = encoder.codify(simbolos)
print "A)"
print "Taula Huffman"
for line in sorted(huffman_table.items(), key=lambda t:t[0], reverse=True):
    print "%s %s" %(line[0], line[1])

## B)
text = 'RKQJQJQJQ10K10R9'
print
print "B)"
print "Text codificat: %s" % text
print encoder.translate(text, huffman_table)


## C)
length = 20
print
print "C)"
print "Generant una sequencia aleatoria de %s simbols:" % length
print sequence(simbolos, length)

## D)
print
print "D)"
lengths = [1000, 10000, 100000, 1000000]
secuencia = sequence(simbolos, length)
print "Generant", len(lengths), "sequencies aleatories amb ", lengths, " simbols"
for i, length in enumerate(lengths):
    sequencia = sequence(simbolos, length)
    sequencia_codificada = encoder.translate(sequencia, huffman_table)
    print "Test", i+1, "-"*70
    print "Sequencia amb %i simbols" % (length)
    print "Sense codificar es necesiten 3 bits per simbol, el que dona un "
    print "total de %i bits per a la sequencia" %(length * 3)
    print "Amb la codificacio Huffman necessitem un total de %i bits" %(len(sequencia_codificada))
    print
    print "El factor de compresio es %i/%i --> %.4f:1" %(length * 3,
                                                   len(sequencia_codificada),
                                                   length * 3. /  len(sequencia_codificada))
    print "En aquesta sequencia concreta per cada bit d'informacio codificada estem "
    print "enviant %.4f bits d'informacio" %(length * 3. /  len(sequencia_codificada))
    print
print "Conclusions " + "-"*65
print "El factor de compresio teoric diu que %i/%.4f --> %.4f:1" %(3,entropia, 3./entropia)
print "Per tant, amb un 1 bit d'informacio codificada podem generar %f bits d'informacio " %(3./entropia)
print "sense codificar"
print "Veiem que en els 4 casos anteriors ens hem acostat prou a les capacitats teoriques"

