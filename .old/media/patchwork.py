from graphics import *


def getInputs():
    validColours = ['red', 'green', 'blue', 'magenta', 'cyan', 'orange',
                    'brown', 'pink']
    validPatchworkSizes = [5, 7, 9, 11]

    # input and validate patchwork size
    patchworkSize = int(input('Enter patchwork size (5, 7, 9, 11): '))
    while patchworkSize not in validPatchworkSizes:
        patchworkSize = int(input(
                    "Size not valid. Enter a size of either 5, 7, 9 or 11: "))

    # input, validate and store-in-list chosen colours
    chosenColours = []
    for i in range(3):
        chosenColour = input("\nEnter colour number {} from the range:\n{}:"
                             .format(i+1, validColours))

        while chosenColour not in validColours:
            chosenColour = input(
                "Chosen colour is not valid, enter another from the range:{}:"
                .format(validColours)
                                )

        chosenColours.append(chosenColour)
        validColours.remove(chosenColour)
    return patchworkSize, chosenColours


def drawWindow(patchworkSize):
    win = GraphWin('Patchwork', patchworkSize*100, patchworkSize*100)
    win.setCoords(0, 0, patchworkSize*100, patchworkSize*100)
    win.setBackground('white')
    return win


def drawPatchOfCircles(win, xCoord, yCoord, colour):
    for i in range(5):
        for j in range(5):
            circle = Circle(Point(xCoord + i*20 + 10, yCoord - j*20 - 10), 10)
            circle.setOutline(colour)

            if j % 2 == 0:
                circle.setFill(colour)
                circle.draw(win)
            else:
                circle.draw(win)
    Rectangle(Point(xCoord, yCoord), Point(xCoord+100, yCoord-100)).draw(win)


def patchOfPacMen(win, xCoord, yCoord, colour):
    for i in range(5):
        for j in range(5):
            if (i+j) % 2 != 0:
                # draw squares
                square = Rectangle(Point(xCoord + i*20, yCoord - j*20),
                                   Point(xCoord + i*20 + 20,
                                         yCoord - j*20 - 20))
                square.setFill(colour)
                square.setOutline(colour)
                square.draw(win)

            else:
                # draw pacmen
                circle = Circle(Point(xCoord+10 + i*20, yCoord-10 - j*20), 10)
                circle.setFill(colour)
                circle.setOutline(colour)
                circle.draw(win)

                if (i + 1) % 2 == 0:
                    triangle = Polygon(Point(xCoord + i*20, yCoord - j*20),
                                       Point(xCoord + i*20,
                                             yCoord - j*20 - 20),
                                       Point(xCoord + 10 + i*20,
                                             yCoord - 10 - j*20))
                else:
                    triangle = Polygon(Point(xCoord + i*20 + 20, yCoord - j*20),
                                       Point(xCoord + i*20 + 20,
                                             yCoord - j*20 - 20),
                                       Point(xCoord + 10 + i*20,
                                             yCoord - 10 - j*20))

                triangle.setFill('white')
                triangle.setOutline('white')
                triangle.draw(win)
    Rectangle(Point(xCoord, yCoord), Point(xCoord+100, yCoord-100)).draw(win)


def drawPatchwork(win, patchworkSize, chosenColours):
    listOfPatchesInfo = []

    colourSelect = 0
    for j in range(patchworkSize):
        for i in range(patchworkSize):
            patchInfo = []

            # determines which patch to draw on the patchwork
            if j+1 < int((patchworkSize+1)/2) or i+1 > int((patchworkSize+1)/2):

                patchOfPacMen(win, i*100, patchworkSize*100 - j*100,
                                            chosenColours[colourSelect])
                patchInfo.append(Point(i*100, patchworkSize*100 - j*100))
                patchInfo.append('pacman')
                patchInfo.append(chosenColours[colourSelect])

            else:
                drawPatchOfCircles(win, i*100, patchworkSize*100 - j*100,
                                   chosenColours[colourSelect])
                patchInfo.append(Point(i*100, patchworkSize*100 - j*100))
                patchInfo.append('circle')
                patchInfo.append(chosenColours[colourSelect])

            listOfPatchesInfo.append(patchInfo)

            # cycle through colours
            colourSelect += 1
            if colourSelect == 3:
                colourSelect = 0
    return listOfPatchesInfo


def singleTileSwap(win, listOfPatchesInfo, patchworkSize, tile1Column, tile1Row,
                   tile2Column, tile2Row):
    for i in range(patchworkSize**2):
        # swaps the design of the clicked tile and changes listOfPatchesInfo
        if (listOfPatchesInfo[i][0].getX() == tile1Column*100 and
             listOfPatchesInfo[i][0].getY() == tile1Row*100):

            if listOfPatchesInfo[i][1] == 'circle':
                patchOfPacMen(win, tile1Column*100,
                                            tile1Row*100,
                                            listOfPatchesInfo[i][2])
                listOfPatchesInfo[i][1] = 'pacman'
            else:
                drawPatchOfCircles(win, tile1Column*100,
                                   tile1Row*100,
                                   listOfPatchesInfo[i][2])
                listOfPatchesInfo[i][1] = 'circle'


def doubleTileSwap(win, listOfPatchesInfo, patchworkSize, tile1Column, tile1Row,
                   tile2Column, tile2Row):
    for i in range(patchworkSize**2):
        # swaps the two tile clicked and changes listOfPatchesInfo
        if (listOfPatchesInfo[i][0].getX() == tile1Column*100 and
             listOfPatchesInfo[i][0].getY() == tile1Row*100):

                if listOfPatchesInfo[i][1] == 'circle':
                    drawPatchOfCircles(win, tile2Column*100,
                                       tile2Row*100,
                                       listOfPatchesInfo[i][2])
                    listOfPatchesInfo[i][0] = Point(tile2Column*100,
                                                    tile2Row*100)
                else:
                    patchOfPacMen(win, tile2Column*100,
                                                tile2Row*100,
                                                listOfPatchesInfo[i][2])
                    listOfPatchesInfo[i][0] = Point(tile2Column*100,
                                                    tile2Row*100)

        elif (listOfPatchesInfo[i][0].getX() == tile2Column*100 and
              listOfPatchesInfo[i][0].getY() == tile2Row*100):

                if listOfPatchesInfo[i][1] == 'circle':
                    drawPatchOfCircles(win, tile1Column*100,
                                       tile1Row*100,
                                       listOfPatchesInfo[i][2])
                    listOfPatchesInfo[i][0] = Point(tile1Column*100,
                                                    tile1Row*100)
                else:
                    patchOfPacMen(win, tile1Column*100,
                                                tile1Row*100,
                                                listOfPatchesInfo[i][2])
                    listOfPatchesInfo[i][0] = Point(tile1Column*100,
                                                    tile1Row*100)


def swapAroundTiles(win, patchworkSize, chosenColours, listOfPatchesInfo):
    while True:
        clickPos = win.getMouse()
        clickPos2 = win.getMouse()

        # generate the columns and rows of the clicked tiles
        tile1Column = int(clickPos.getX()//100)
        tile1Row = int(clickPos.getY()//100) + 1
        tile2Column = int(clickPos2.getX()//100)
        tile2Row = int(clickPos2.getY()//100) + 1

        # cover the selected tiles with a white rectangle
        tipex1 = Rectangle(Point(tile1Column*100, tile1Row*100),
                           Point(tile1Column*100 + 100, tile1Row*100 - 100))
        tipex1.setFill('white')
        tipex1.draw(win)
        tipex2 = Rectangle(Point(tile2Column*100, tile2Row*100),
                           Point(tile2Column*100 + 100, tile2Row*100 - 100))
        tipex2.setFill('white')
        tipex2.draw(win)

        if tile1Column == tile2Column and tile1Row == tile2Row:
            singleTileSwap(win, listOfPatchesInfo, patchworkSize, tile1Column,
                           tile1Row, tile2Column, tile2Row)
        else:
            doubleTileSwap(win, listOfPatchesInfo, patchworkSize, tile1Column,
                           tile1Row, tile2Column, tile2Row)


def main():
    # get inputs
    patchworkSize, chosenColours = getInputs()
    # draw window
    win = drawWindow(patchworkSize)
    # draw patchwork
    listOfPatchesInfo = drawPatchwork(win, patchworkSize, chosenColours)
    # swap tiles
    swapAroundTiles(win, patchworkSize, chosenColours, listOfPatchesInfo)

main()
