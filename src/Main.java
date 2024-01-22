import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        char[][] board = new char[3][3];
        char activePlayer = 'X';
        int i;
        int j;

        completeTheBoard(board);
        printTheBoard(board);

        try (Scanner scanner = new Scanner(System.in)) {

            while (gameResult(board) == '1') {

                System.out.println("Player " + activePlayer + ", give both coordinates: ");
                i = scanner.nextInt();
                j = scanner.nextInt();

                while (checkCoordinates(board, i, j)) {
                    System.out.println("Values too big. Enter max value " + (board.length - 1) + ": ");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                }

                while (!updateTheBoard(board, i, j, activePlayer)) {
                    System.out.println("Field occupied. Enter other coordinates: ");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                }

                printTheBoard(board);

                if (activePlayer == 'X') {
                    activePlayer = '0';
                } else {
                    activePlayer = 'X';
                }
            }
        }
    }

    private static void completeTheBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printTheBoard(char[][] board) {

        int dim = board.length;
        System.out.print("\t");

        for (int i = 0; i < dim; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();

        for (int row = 0; row < dim; row++) {
            System.out.print(row + "\t");
            for (int col = 0; col < dim; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println();
        }
    }

    private static boolean checkCoordinates(char[][] board, int i, int j) {

        if (i >= board.length || j >= board.length) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean updateTheBoard(char[][] board, int i, int j, char activePlayer) {

        if (board[i][j] == '-') {
            board[i][j] = activePlayer;
            return true;
        } else {
            return false;
        }
    }

    private static char checkRows(char[][] board) {

        char row = ' ';

        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == board[i][1]) && board[i][1] == board[i][2] && board[i][0] != '-') {
                row = board[i][0];
            }
        }
        return row;
    }

    private static char checkColumns(char[][] board) {

        char col = ' ';

        for (int i = 0; i < board[0].length; i++) {
            if ((board[0][i] == board[1][i]) && board[1][i] == board[2][i] && board[0][i] != '-') {
                col = board[0][i];
            }
        }
        return col;
    }

    private static char checkDiagonals(char[][] board) {

        int x = 0;
        int y = 0;
        int z = 0;
        int g = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 'X') {
                x++;
            } else if (board[i][i] == '0') {
                y++;
            }
            if (board[i][board.length-i-1] == 'X') {
                z++;
            } else if (board[i][board.length-i-1] == '0') {
                g++;
            }
        }

        if (x == 3 || z == 3) {
            return 'X';
        } else if (y == 3 || g == 3) {
            return '0';
        } else {
            return ' ';
        }
    }

    private static char gameResult(char[][] board) {

        char result;

        result = checkColumns(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            return result;
        }

        result = checkRows(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            return result;
        }

        result = checkDiagonals(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            return result;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == '-'){
                    result = '1';
                }
            }
        }

        if (result != ' ') {
            return result;
        }
        else {
            System.out.println("Draw!");
            return 'd';
        }
    }
}