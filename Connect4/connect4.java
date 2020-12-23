import java.util.InputMismatchException;
import java.util.Scanner;

public class connect4 {

    public String[][] createBoard() {
        int rows = 6;
        int columns = 7;
        String[][] board = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = "-";
            }
        }

        return board;
    }

    public void printBoard(String[][] board) {
        System.out.println("");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public int checkPlacement(String[][] board, int col) {

        for (int i = 0; i < board.length; i++) {
            if (board[i][col - 1] != "-") {
                return i;
            }
        }

        return board.length;
    }

    public String[][] placePiece(String[][] board, int col, int row, String player) {

        switch (player) {
            case "Red":
                board[row - 1][col - 1] = "R";
                break;
            case "Blue":
                board[row - 1][col - 1] = "B";
                break;
        }

        return board;
    }

    public String changePlayer(String player) {
        if (player == "Red") {
            player = "Blue";
        } else {
            player = "Red";
        }

        return player;
    }

    public Boolean checkForWinner(String[][] board) {
        // Horizontal
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                try {
                    if(board[i][j] == "R" && board[i][j + 1] == "R" && board[i][j + 2] == "R" && board[i][j + 3] == "R") {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        // Diagonal (/)
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                try {
                    if(board[i][j] == "R" && board[i - 1][j + 1] == "R" && board[i - 2][j + 2] == "R" && board[i - 3][j + 3] == "R") {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        // Vertical
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                try {
                    if(board[i][j] == "R" && board[i + 1][j] == "R" && board[i + 2][j] == "R" && board[i + 3][j] == "R") {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        // Diagonal (\)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                try {
                    if(board[i][j] == "R" && board[i + 1][j + 1] == "R" && board[i + 2][j + 2] == "R" && board[i + 3][j + 3] == "R") {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        

        return false;
    }

    public static void main(String[] args) {
        connect4 connect4 = new connect4();
        String[][] board = connect4.createBoard();
        connect4.printBoard(board);

        Scanner userFeed = new Scanner(System.in);
        boolean running = true;
        String currentPlayer = "Red";
        while (running) {
            System.out.print(currentPlayer + " Enter column number (1-7): ");
            userFeed = new Scanner(System.in);
            int colNum;
            try {
                colNum = userFeed.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a number from the given range.");
                continue;
            }

            int newRow;
            try {
                newRow = connect4.checkPlacement(board, colNum);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid number");
                continue;
            }
            board = connect4.placePiece(board, colNum, newRow, currentPlayer);
            connect4.printBoard(board);
            if (connect4.checkForWinner(board)) {
                System.out.println("Player " + currentPlayer + " Won!");
                running = false;
            } else {
                currentPlayer = connect4.changePlayer(currentPlayer);
            }
        }
        userFeed.close();
    }
}