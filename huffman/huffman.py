class Huffman(object):
    def __merge_rows__(self, a, b):
        """Returns a mix of both rows"""
        return [a[0] + b[0], a[1] + b[1]]

    def __sort_table__(self, table):
        """Returns a sorted table based on the value which is in the second position"""
        return sorted(table, key=lambda x: x[1], reverse=True)

    def codify(self, data, result=None, verbose=False):
        """Performs a Huffman codification.
        It mixes the forward and backward steps used when we codify manually but just in one pass.
        The way it works is generating a tree from the leaves to the root.
        First we create a result dict with bit sequences equals to ''.
        While branching, we insert the bit 0 or 1 at the beggining of the bit sequence, 0
        for a left branch 1 for a right branch, for the characters that are being branched.
        The way we chose to branch is smaller probability to the left, bigger to the right"""
        if result is None:
            ## first call
            ## result will be a dict
            result = {row[0]:'' for row in data }
            ## data[0] will be a list
            data = [[[x],y] for x, y in data]
            ## sorts the input, just in case
            data = self.__sort_table__(data)
        if len(data) > 1:
            ## the smaller goes to the left
            left = data[-1]
            ## the second smaller to the right
            right = data[-2]
            if verbose:
                print "Branching:"
                print "%s<-0- -1->%s"%(str(left), str(right))
            ## update binary codes
            for key in left[0]:
                result[key] = '0' + result[key]
            for key in right[0]:
                result[key] = '1' + result[key]
            ## creates a new row from left+right
            new_row = self.__merge_rows__(left, right)
            ## adds the new where there were left and right, and sorts
            data = self.__sort_table__(data[:-2] + [new_row])
            ## recursive call
            return self.codify(data, result, verbose)
        return result

    def translate(self, text, codification, verbose=False):
        """Translates an input text based on the table codification"""
        offset = 1
        output = ''
        for end in range(1, len(text) + 1):
            character = text[end - offset:end]
            if character in codification:
                offset = 1
                output += codification[character]
                if verbose:
                    print "%s: %s" %(character, codification[character])
            else:
                offset += 1
        return output     
