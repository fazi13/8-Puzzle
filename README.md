# 8-Puzzle-Solver
PuzzleTester is the main program that has user input. 
PuzzleTesterRandom will generate 100 random puzzles and solve them with the h1 and h2 heuristics and only output the depth count and number of nodes. 

PuzzleTester has 3 input options, user inputs a puzzle board, generate a random board, or read boards from a file. 

User input accepts a one line string of numbers with or without spaces or a 3 x 3 multi-line input. 
	When the user is done with the input, press enter twice. 

Random will generate one random board for the user.

Read from file will ask the user for the filename and read puzzles in a one line string of numbers with or without spaces. 


Verbose mode outputs every step the solver took, accepts y/n input. 
Otherwise, only the depth count and number of nodes will be outputted. 

Heuristic accepts "h1" or "h2" depending on which heuristic the user wants to use.
