#!/usr/bin/env python2.7
# ticTacToe.py
# Tic Tac Toe game
# last update: 2/19/15
__author__ = 'Natalie'
# help from Spencer, Mr. Cochran, and Carey (kinda, really though?)

import random
import copy

def displayInstructions():
    print("\nWelcome to tic tac toe!...\n")
    print("You will make your move known by entering coordinates.\n")
    print("The numbers will correspond to the board position as illustrated (row and column number):\n")
    print("\n 0 0 | 0 1 | 0 2")
    print("----------------")
    print(" 1 0 | 1 1 | 1 2")
    print("----------------")
    print(" 2 0 | 2 1 | 2 2")


def assignTurn():
    # selects the computer of the user to go first randomly
    turn = random.randrange(0,2)
    if turn == 0:
        return ["computer", "user"]
    if turn == 1:
        return ["user", "computer"]

def boardFull(grid):
    # checks to see if the board is full
    for row in grid:
        for item in row:
            if item == " ":
                return False
    return True

def move(grid, row, col, user):
    if grid[row][col] != " ":
        print("Cannot place move there.")
    else:
        grid[row][col] = user

def userMove(grid, userSign):
    movePrint = userSign
    place = raw_input(movePrint + " make a move: \n")
    place = place.split(" ")
    move(grid, int(place[0]), int(place[1]), userSign)

# AI
# 1. win if possible
# 2. block if user is about to win
# 3. pick a corner
# 4. pick the center
# 5. pick a middle space on a random edge
def computerMove(grid, computerSign):
    print("\n")
    boardCopy = copy.deepcopy(grid)
    # checks to see if the computer win first
    for row in range(0,3):
        for col in range (0,3):
            if boardCopy[row][col] == " ":
                boardCopy[row][col] = computerSign
                if gameWon(boardCopy) != " ":
                    move(grid, row, col, computerSign)
                    return
                else:
                    boardCopy[row][col] = " "
    # check if the user will win the next move -- so computer must block
    for row in range(0,3):
        for col in range (0,3):
            if boardCopy[row][col] == " ":
                boardCopy[row][col] = "X" if computerSign == "O" else "O"
                if gameWon(boardCopy) != " ":
                    move(grid, row, col, computerSign)
                    return
                else:
                    boardCopy[row][col] = " "

    if grid[0][0] == " " or grid[0][2] == " " or grid[2][2] == " " or grid[2][0] == " ":
        while True:
            ranMove = random.randrange(0, 4)
            if ranMove == 0 and grid[0][0] == " ":
                move(grid, 0, 0, computerSign)
                return
            elif ranMove == 1 and grid[0][2] == " ":
                move(grid, 0, 2, computerSign)
                return
            elif ranMove == 2 and grid[2][0] == " ":
                move(grid, 2, 0, computerSign)
                return
            elif ranMove == 3 and grid[2][2] == " ":
                move(grid, 2, 2, computerSign)
                return

    elif grid[1][1] == " ":
        move(grid, 1, 1, computerSign)

    else:
        while True:
            ranMove = random.randrange(0, 4)
            if ranMove == 0 and grid[0][1] == " ":
                move(grid, 0, 1, computerSign)
                return
            elif ranMove == 1 and grid[1][0] == " ":
                move(grid, 1, 0, computerSign)
                return
            elif ranMove == 2 and grid[1][2] == " ":
                move(grid, 1, 2, computerSign)
                return
            elif ranMove == 3 and grid[2][1] == " ":
                move(grid, 2, 1, computerSign)
                return

def gameWon(grid):
    # win in rows
    if grid [0][0] != " " and grid [0][0] == grid [0][1] and \
               grid [0][1] == grid [0][2]:
        return grid[0][0]
    if grid [1][0] != " " and grid [1][0] == grid [1][1] and \
               grid [1][1] == grid [1][2]:
        return grid[1][0]
    if grid [2][0] != " " and grid [2][0] == grid [2][1] and \
               grid [2][1] == grid [2][2]:
        return grid[2][0]
    # win in columns
    if grid [0][0] != " " and grid [0][0] == grid [1][0] and \
               grid [1][0] == grid [2][0]:
        return grid[0][0]
    if grid [0][1] != " " and grid [0][1] == grid [1][1] and \
               grid [1][1] == grid [2][1]:
        return grid[0][1]
    if grid [0][2] != " " and grid [0][2] == grid [1][2] and \
               grid [1][2] == grid [2][2]:
        return grid[0][2]
    if grid [0][0] != " " and grid [0][0] == grid [1][1] and \
               grid [1][1] == grid [2][2]:
        return grid[0][0]
    if grid [0][2] != " " and grid [0][2] == grid [1][1] and \
               grid [1][1] == grid [2][0]:
        return grid[0][2]

    return " "

def printBoard(grid):
    # here is one way to print, called a for-each loop
    # solves the fencepost problem
    # prints the board to the terminal window
    for row in range(0, len(grid)):
        for col in range (0, len(grid[row])):
            print(grid[row][col]),
            if col < len(grid[row])-1:
                print("|"),
        if row < len(grid)-1:
            print("\n----------")
        else:
            print(" ")

def main():
    # main block of code the executes the program
    grid = []
    grid.append([])
    grid.append([])
    grid.append([])
    displayInstructions()
    # Assigns the player and computer X or O to play with
    while True:
        print("\nDo you want to be X or O? ")
        user = raw_input ("> ")
        if user.lower() == 'x':
            print "Ok, X is yours!"
            userSign = "X"
            computerSign = "O"
            break
        elif user.lower() == 'o':
            print "Ok, O is yours!"
            computerSign = "X"
            userSign = "O"
            break
        else:
            print "Sorry, please enter X or O"
    turn = assignTurn()
    for row in grid:
        # involves 2 types of for loops
        for i in range (0,3):
            # never stores the upper value
            row.append(" ")

    numTurns = 0
    place = " " # used for move placement
    row = -1
    col = -1
    movePrint = " "
    pleaseWorkForTheLoveOfGod = " "

    while(not boardFull(grid) and gameWon(grid) == " "):
        pleaseWorkForTheLoveOfGod = turn[numTurns%2]
        if pleaseWorkForTheLoveOfGod == "user":
            userMove(grid, userSign)
            numTurns += 1
        else:
            computerMove(grid, computerSign)
            numTurns += 1
        printBoard(grid)

    if(boardFull(grid)):
        print("The game ended in a tie")
    else:
        print(str(gameWon(grid)) + " wins the game!")

main()


