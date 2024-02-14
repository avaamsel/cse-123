// Ava Nunes
// 01/19/24
// CSE 123
// TA: Ben Wang
// this class allows the client to play a game of connect four with the addition of allowing the
// player the ability whether they want to remove a token from the bottom row for their turn or
// add a new token to the board

import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    private char[][] board;
    private boolean redTurn;

    // behavior: contructs a new connect four board
    // parameters: n/a
    // returns: n/a, constructor
    public ConnectFour() {
        board = new char[][]{{'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},                                                         
                             {'-', '-', '-', '-', '-', '-', '-'}};
        redTurn = true;
    }

    // behavior: constructs and returns a string describing how to play the game
    // parameters: n/a
    // returns: instuctions on how to play the game
    public String instructions() {
       return "words and stuff and whatever and blah blah blah yada yada yada";
    }

    // behavior: constructs and returns a string representation of the current game state
    // parameters: n/a 
    // returns: result - string representation of the current game state
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result += board[i][j] + " ";
            }

            result += "\n";
        }

        return result;
    }

    // behavior: returns whether or not the game has ended
    // parameters: n/a 
    // returns: true if the game has ended, false if otherwise
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // behavior: returns the player who won the game, if came if completed
    // parameters: n/a
    // returns: winner who has won, -1 if the game isn't over
    public int getWinner() {
        int redCount = 0;
        int yellowCount = 0;
        for(int i = board.length - 1; i > 3; i--) {
            for(int j = 0; j < board[i].length - 1; j++) {
                char curr = board[i][j];
                if(board[i][j] != '-') {
                    if(board[i][j + 1] == curr && board[i][j + 2] == curr && board[i][j + 3] == curr) {
                        if(curr == 'R') {
                            return 1;
                        }

                        else if (curr == 'Y') {
                            return 2;
                        }
                    }

                    else if(board[i - 3][j] == curr && board[i - 2][j] == curr && board[i - 1][j] == curr) {
                        if(curr == 'R') {
                            return 1;
                        }

                        else if (curr == 'Y') {
                            return 2;
                        }
                    }
                }
            }
        }

        if(redCount != 4 || yellowCount != 4) {
            return -1;
        }

        return 0;
    }

    // behavior: returns index of the play who's turn is next
    // parameters: n/a
    // returns: index of next player, -1 if the game has completed
    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        }

        if(redTurn) {
            return 1;
        }

        else {
            return 2;
        }
    }

    // behavior: takes input from the parameter to specify the move the player with the
    //           next turn wishes to make, then completes that move
    // parameters: input - player's choice of move
    // returns: n/a, void method
    // exceptions: throws a new IllegalArgumentException if the position inputted is invalid
    public void makeMove(Scanner input) {
        System.out.print("Would you like to add or remove a token? Type A or R: ");
        String answer = input.next();

        if(answer.equals("A")) {
            System.out.print("What column do you want to add to? ");
            int space = input.nextInt();
            if(space < 0 || space >= board[0].length) {
                throw new IllegalArgumentException("Invalid board position: " + space);
            }

            if(freeRow(space) == -1) {
                throw new IllegalArgumentException("No free space in column " + space + "!");
            }

            char currentPlayer = '-';
            if(redTurn) {
                currentPlayer = 'R';
            }

            else {
                currentPlayer = 'Y';
            }

            board[freeRow(space)][space] = currentPlayer;
            redTurn = !redTurn;
        }

        else if (answer.equals("R")) {
            char currentPlayer = '-';
            if(redTurn) {
                currentPlayer = 'R';
            }

            else {
                currentPlayer = 'Y';
            }

            System.out.println("Which of your tokens would you like to remove?");
            System.out.print("From which column? ");
            int column = input.nextInt();

            if(board[board.length - 1][column] == currentPlayer) {
                System.out.println("works");
                for (int i = board.length - 1; i > 0; i--) {
                    board[i][column] = board[i - 1][column];
                } 

                board[0][column] = '-';
            }  

            redTurn = !redTurn; 
        }
    }

    // behavior: figures out the bottom-most free row in the player's chosen position
    // parameters: space - column chosen by the player to place their marker
    // returns: row - bottom-most available row for the player's marker to go, returns -1 if
    //                there are no free spaces in the selected column
    private int freeRow(int space) {
        int row = -1;
        for(int i = board.length - 1; i >= 0; i--) {
            if(board[i][space] == '-') {
                row = i;
                i = 0;
            }
        }

        return row;
    }
}
