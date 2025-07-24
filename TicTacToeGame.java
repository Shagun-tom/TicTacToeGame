package Projects;
/*
Author : Shagun
Game : Tic-Tac-Toe Console Game
Language : Java
Description : A 2-player turn-based console game win and draw detection.
*/

/*
**Divide this game into small parts**
1. Print the Board
2. Take input from players
3. Switch turn
4. Check for win
5. Check for draw
*/

import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        // Step 1: Initialize the 3x3 game board with empty spaces
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
        printBoard(board);
        char currentPlayer = 'X';
        Scanner sc = new Scanner(System.in);
        for (int turn = 0; turn < 9; turn++) {
            System.out.println("Player " + currentPlayer + "'s turn ");
                System.out.print("Enter row(0-2) : ");
                int row = sc.nextInt();
                System.out.print("Enter col(0-2) : ");
                int col = sc.nextInt();
                if(row<0||row>2||col<0||col>2){
                    System.out.println("Invalid input! Enter numbers between 0 and 2.");
                    turn--;
                    continue;
                }
                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                } else {
                    System.out.println("Spot already taken! Try again");
                    turn--;
                    continue;
                }
                printBoard(board);

                if (checkWin(board, currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " win🎉");
                    System.out.println("Do you want to play again? (Y/N) : ");
                    char ans = sc.next().charAt(0);
                    if(ans == 'Y' || ans == 'y') {
                        main(new String[]{});
                    }
                          return; // exit current game
                    }else if (turn == 8) {
                    System.out.println("It's draw😅");
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            System.out.println("____________________________________________________________");
            }
        }
    public static void printBoard(char[][] board){
        System.out.println("----------------------");
        for(int r=0; r<3; r++){
            System.out.print("|");
            for(int c=0; c<3; c++){
                System.out.print("  "+board[r][c]+"   |");
            }
            System.out.println();
            System.out.println("----------------------");
        }

    }
    // Function to check if the current player has won the game
    public static boolean checkWin(char[][] board, char currentPlayer){
        // row wise win
        for(int row=0; row<3; row++){
            if(board[row][0]==currentPlayer && board[row][1]==currentPlayer && board[row][2]==currentPlayer)
                return true;
        }

        // column wise win
        for(int col=0; col<3; col++){
            if(board[0][col]==currentPlayer && board[1][col]==currentPlayer && board[2][col]==currentPlayer)
                return true;
        }

        // Diagonal wise win
        if(board[0][0]==currentPlayer && board[1][1]==currentPlayer && board[2][2]==currentPlayer)
            return true;
        if(board[0][2]==currentPlayer && board[1][1]==currentPlayer && board[2][0]==currentPlayer)
            return true;
        return  false;
    }
}
