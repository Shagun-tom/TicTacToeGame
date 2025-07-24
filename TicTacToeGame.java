import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    // declare a 2D array
    static char[][] board;

    // constructor
    TicTacToe() {
        //initialization of 2D array
        board = new char[3][3];
        initBoard();
    }

    // method for enter  space as it's element  in 2D array
    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // method for display board
   static void displayBoard() {
        System.out.println(" _____________");
        for (int i = 0; i < board.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" _____________");
        }
    }

    // method for place mark
   static void placeMark(int row, int col, char mark) {
        // condition for execution
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid position");
        }
    }

    // method for check column wise winner
    static boolean colWiseWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // method for check rows wise winner
    static boolean rowWiseWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // method for diagonal win
    static boolean diagonalWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
}

// PARENT CLASS
abstract class Player {
    String name;
    char mark;

    abstract  void makeMove();

    boolean inValidMove(int row, int col){
        if(row <= 2 && row >= 0 && col >= 0 && col <= 2){
            if( TicTacToe.board[row][col] == ' '){
                return true ;
            }
        }
        return false;
    }

}

    // human player
    class HumanPlayer extends Player {


        HumanPlayer(String name, char marks){
            this.name = name;
            this.mark = marks;
        }

       void makeMove(){
       Scanner sc = new Scanner(System.in);
       int row;
       int col;

       do{
           System.out.println("Enter row and column ");
           row = sc.nextInt();
           col = sc.nextInt();
       } while (!inValidMove(row,col));
       TicTacToe.placeMark(row,col,mark);
        }

    }


// AI player
class AIPlayer extends Player{

    AIPlayer(String name, char marks){
        this.name = name;
        this.mark = marks;
    }

    void makeMove(){
        Scanner sc = new Scanner(System.in);
        int row;
        int col;

        do{
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!inValidMove(row,col));
        TicTacToe.placeMark(row,col,mark);
    }

}


// main function
    public class TicTacToeGame {
        public static void main(String[] args) {
            TicTacToe t = new TicTacToe();
          HumanPlayer p1 = new HumanPlayer("Shagun",'X');
          AIPlayer p2 = new AIPlayer("TAI", 'O');
          Player cp ;
          cp = p1;
          while(true) {
              System.out.println(cp.name + " turn");
              cp.makeMove();
              TicTacToe.displayBoard();
              if (TicTacToe.rowWiseWin() || TicTacToe.colWiseWin() || TicTacToe.diagonalWin()) {
                  System.out.println("Congratulation! " + cp.name + " for winning  ");
                  break;
              } else {
                  if (cp == p1) {
                      cp = p2;
                  } else {
                      cp = p1;
                  }
              }
          }
    }
}