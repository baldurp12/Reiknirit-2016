
import random


def makeTests():

    numberList = [150, 200, 300, 400, 800, 1200, 1600, 3200, 6400, 12800];

    for x in numberList:
        testFile = open('inputTest-' + str(x) + '.txt', "w");
        testFile.write(str(x) + "\n");
        for y in range(0, x):
            testFile.write( str(random.randint(0,32768)) + " " + str(random.randint(0,32768)) + "\n");
        testFile.close();
        

