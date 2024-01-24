import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {

            String continueOrGameOver;
            String playerName;
            int numberOfBoardsWon = 0;

            Map<Character, String> playersMap = new HashMap<>();
            Map<String, Integer> mapOfWinningBoards = new HashMap<>();

            System.out.println("Please enter the name of the first player(as player X): ");
            playerName = scanner.nextLine();
            playersMap.put('X', playerName);
            mapOfWinningBoards.put(playerName, numberOfBoardsWon);

            System.out.println("Please enter the name of the second player(as player 0): ");
            playerName = scanner.nextLine();
            playersMap.put('0', playerName);
            mapOfWinningBoards.put(playerName, numberOfBoardsWon);

            do {
                int boardDimension;
                char activePlayer = 'X';
                int i;
                int j;

                System.out.println("Please provide the board dimension (min 3, max 10): ");
                boardDimension = scanner.nextInt();

                    while (boardDimension < 3 || boardDimension > 10) {
                    System.out.println("Incorrect data provided. Enter the board dimension again (min 3, max 10): ");
                    boardDimension = scanner.nextInt();
                    }

                char[][] board = new char[boardDimension][boardDimension];

                completeTheBoard(board);
                printTheBoard(board);

                    while (gameResult(board, playersMap, mapOfWinningBoards) == '1') {
                        System.out.println("Player " + activePlayer + ", give both coordinates: ");
                        i = scanner.nextInt();
                        j = scanner.nextInt();

                        while (checkCoordinates(board, i, j)) {
                            System.out.println("Values too big. Enter max value " + (board.length-1) + ": ");
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
                System.out.println("Shall we play again? Enter YES or NO.");
                scanner.nextLine();
                continueOrGameOver = scanner.nextLine();
            }
            while (continueOrGameOver.equalsIgnoreCase("YES"));
                System.out.println("Results:\n" +
                                   playersMap.get('X') + " : " + mapOfWinningBoards.get(playersMap.get('X')) + " board won\n" +
                                   playersMap.get('0') + " : " + mapOfWinningBoards.get(playersMap.get('0')) + " board won\n");
                System.out.println("Thank you for the game!");
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

    private static boolean updateTheBoard(char[][] board,
                                          int i,
                                          int j,
                                          char activePlayer) {

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
            if (board[i][0] != '-') {
                row = board[i][0];
                row = checkLines(board, i, row, 'r');
            }
            if (row != ' ') {
                return row;
            }
        }
        return row;
    }

    private static char checkColumns(char[][] board) {

        char col = ' ';

        for (int i = 0; i < board.length; i++) {
            if (board[0][i] != '-') {
                col = board[0][i];
                col = checkLines(board, i, col, 'c');
            }
            if (col != ' ') {
                return col;
            }
        }
        return col;
    }

    private static char checkLines(char[][] board,
                                   int i,
                                   char oneCharacterInLine,
                                   char type) {

                for (int j = 1; j < board[i].length; j++) {
                    if (type == 'c') {
                        if (board[j][i] != oneCharacterInLine) {
                            oneCharacterInLine = ' ';
                        }
                    } else {
                        if (board[i][j] != oneCharacterInLine) {
                            oneCharacterInLine = ' ';
                        }
                    }
                }
                return oneCharacterInLine;
    }

    private static char checkDiagonals(char[][] board) {

        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 'X') {
                w++;
            } else if (board[i][i] == '0') {
                x++;
            }
            if (board[i][board.length-i-1] == 'X') {
                y++;
            } else if (board[i][board.length-i-1] == '0') {
                z++;
            }
        }

        if (w == board.length || y == board.length) {
            return 'X';
        } else if (x == board.length || z == board.length) {
            return '0';
        } else {
            return ' ';
        }
    }

    private static char gameResult(char[][] board,
                                   Map<Character, String> playersMap,
                                   Map<String, Integer> mapOfWinningBoards) {

        char result;

        result = checkColumns(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            mapOfWinningBoards.replace(playersMap.get(result),
                                       mapOfWinningBoards.get(playersMap.get(result))+1);
            return result;
        }

        result = checkRows(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            mapOfWinningBoards.replace(playersMap.get(result),
                                       mapOfWinningBoards.get(playersMap.get(result))+1);
            return result;
        }

        result = checkDiagonals(board);

        if (result != ' ') {
            System.out.println("The winner is the player: " + result + ". Congratulations!");
            mapOfWinningBoards.replace(playersMap.get(result),
                                       mapOfWinningBoards.get(playersMap.get(result))+1);
            return result;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    result = '1'; // '1' means the game continues
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