# Tic-Tac-Toe-by-Katie-Heym

This project contains the code for 2 GUIs constructed using the MVC design pattern. 

The base of the model is rooted in the interface BoardGameKernel and its implementing class, GenericBoard. BoardGameKernel extends Map from the OSU CSE components package, which is similar to the Java standard Map except that it extends iterable and is thus easily navigable using for-each loops.

GenericBoard is a map from the custom interface type Location to the generic type T, used to mark the board. Interface type Location contains an enum with the 8 cardinal and diagonal directions hard-coded. This allows an implementing class to understand the locations relative to each other using the Navigate method, which can allow pieces to move, or in the case of this project, understand when pieces lie in a row, column, or diagonal to check for wins.

From this general, multipurpose framework, we have two classes and an interface specific to this project which provide a background for the model of the main GUI. TicTacToeBoard extends GenericBoard and specifies new methods helpful for modeling Tic-Tac-Toe specifically. TicTacToeBoard1 implements TicTacToeBoard as a map from Location to Integer. One player plays 1, the other -1, and 0 indicates empty. TicTacToeBoard1 provides the machinery for the GUI model to interact with the board, understanding the win and tie conditions as well as being able to play new moves.

TicTacToeLoc implements Location for use in TicTacToeBoard1. It implements the navigation features and overrides the standard methods which allow the Map model to function correctly; TicTacToeBoard1 can correctly identify content equality rather than reference equality. Among these classes and interfaces, there are commented out obsolete methods which had attemted to correct for the reference equality bug before the standard methods were overridden.

From there, the project codes for a GUI for the menu and a GUI for the game itself.

Menu:

Model: 
Interface: TicTacToeMenuModel / Implementing Class: TicTacToeMenuModel1
View:
Interface: TicTacToeMenuView / Implementing Class: TicTacToeMenuView1
Controller:
Interface: TicTacToeMenuController / Implementing Class: TicTacToeMenuController1

Main Game:

Model: 
Interface: TicTacToeModel / Implementing Class: TicTacToeModel1
View:
Interface: TicTacToeView / Implementing Class: TicTacToeView1
Controller:
Interface: TicTacToeController / Implementing Class: TicTacToeController1

The master file is TicTacToeGUI. It loads the menu, which loads the main game once the settings are confirmed. If the player chooses to play again, they are redirected back to the menu.

Of note is the AI used to play the computer's moves. The difficulty system is a simple linear sliding scale from 100% random to 100% AI-driven / "smart". The first computer move is hard-coded to reduce computation time. Then, the computer checks for an own win and then for an opponent win to block. The remaining moves are dictated by a decision tree which indicates which moves are most likely to result in a win or else a tie.
