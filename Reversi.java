import java.util.Scanner;

/**
 * CS18000 - Fall 2018
 * <p>
 * Project 2 - Reversi
 * <p>
 * Abstraction of and launcher for a Reversi game
 *
 * @author Imtiaz Karim, karim7@purdue.edu
 * @author Marina Haliem, mwadea@purdue.edu
 * @author Evan Wang, wang3407@purdue.edu
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 10/17/2018
 */
public class Reversi {

    public static boolean isEmpty(Point[] p) {
        /**
         * @param P The game board to be checked
         * @return true if there are valid moves; false if there are no valid moves
         */
        boolean output = false;
        for (int i = 0; i < p.length; i++) {
            if (p[i].x != -1 && p[i].y != -1) {
                output = true;
                i = p.length;
            } else {
                output = false;
            }
        }
        return output;
    }

    //Check whether a Point is the Points array or not 

    public static boolean contains(Point[] points, Point p) {
        /**
         * @param points The game board to be checked
         * @param p The point to be checked for validity
         * @return true if the board contains the point; false if the board does not contain the point
         */

        boolean value = false;

        for (int i = 0; i < points.length; i++) {
            if (points[i].x == p.x && points[i].y == p.y) {
                value = true;
                i = points.length;
            } else {
                value = false;
            }
        }

        return value;

    }

    public static void start(Game g) {
        /**
         * @param g The Reversi game currently in play
         */

        String draw = "It is a draw.";
        String whiteWins;
        String blackWins;
        String exit = "Exiting!";

        String blackMove = "Place move (Black): row then column: ";
        String blackSkipping = "Black needs to skip... Passing to white";
        String invalidBlackMove = "Invalid move!\nPlace move (Black): row then column: ";
        String blackScoreShow;

        String whiteSkipping = "White needs to skip... Passing to Black";
        String whiteMove = "Place move (White): row then column: ";
        String invalidWhiteMove = "Invalid move!\nPlace move (White): row then column: ";
        String whiteScoreShow;

        boolean keepGoing = true;
        boolean notValid;
        boolean notInt;
        int inputNumber = 0;
        int row;
        int column;
        String input;
        Point p;

        Scanner scanner = new Scanner(System.in);


        while (keepGoing) {
            char player = 'B';
            char opponent = 'W';
            Point[] points = g.getPlaceableLocations(player, opponent);
            g.showPlaceableLocations(points, player, opponent);
            g.displayBoard(g);
            System.out.println(blackMove);
            do {
                notInt = false;
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println(exit);
                    keepGoing = false;
                    break;
                }

                if (input.length() != 2) {
                    System.out.println(invalidBlackMove);
                    notInt = true;
                } else {
                    try {
                        inputNumber = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println(invalidBlackMove);
                        notInt = true;
                    }
                }

                if (notInt) {
                    System.out.println(invalidBlackMove);
                    notValid = true;
                } else {
                    row = inputNumber / 10;
                    column = inputNumber - (row * 10);
                    row = row - 1;
                    column = column - 1;
                    p = new Point(row, column);
                    if (!isEmpty(points)) {
                        notValid = false;
                        System.out.println(blackSkipping);
                        g.updateScores();
                        blackScoreShow = "Black: " + g.bScore + " White: " + g.wScore;
                        System.out.println(blackScoreShow);
                        g.displayBoard(g);
                    } else if (contains(points, p)) {
                        notValid = false;
                        g.placeMove(p, player, opponent);
                        g.updateScores();
                        blackScoreShow = "Black: " + g.bScore + " White: " + g.wScore;
                        System.out.println(blackScoreShow);
                    } else {
                        System.out.println(invalidBlackMove);
                        notValid = true;
                    }
                }
            } while (notValid);

            if (!keepGoing) {
                break;
            }

            player = 'W';
            opponent = 'B';
            points = g.getPlaceableLocations(player, opponent);
            g.showPlaceableLocations(points, player, opponent);
            g.displayBoard(g);
            System.out.println(whiteMove);

            do {
                notInt = false;
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println(exit);
                    keepGoing = false;
                    break;
                }

                if (input.length() != 2) {
                    System.out.println(invalidWhiteMove);
                    notInt = true;
                } else {
                    try {
                        inputNumber = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println(invalidWhiteMove);
                        notInt = true;
                    }
                }

                if (notInt) {
                    System.out.println(invalidWhiteMove);
                    notValid = true;
                } else {
                    row = inputNumber / 10;
                    column = inputNumber - (row * 10);
                    row = row - 1;
                    column = column - 1;
                    p = new Point(row, column);
                    if (!isEmpty(points)) {
                        notValid = false;
                        System.out.println(whiteSkipping);
                        g.updateScores();
                        whiteScoreShow = "White: " + g.wScore + " Black: " + g.bScore;
                        System.out.println(whiteScoreShow);
                    } else if (contains(points, p)) {
                        notValid = false;
                        g.placeMove(p, player, opponent);
                        g.updateScores();
                        whiteScoreShow = "White: " + g.wScore + " Black: " + g.bScore;
                        System.out.println(whiteScoreShow);
                    } else {
                        System.out.println(invalidWhiteMove);
                        notValid = true;
                    }
                }

            } while (notValid);

            if (!isEmpty(g.getPlaceableLocations(player, opponent)) && !isEmpty(g.getPlaceableLocations(opponent,
                    player))) {
                keepGoing = false;
            }
        }

        char player = 'B';
        char opponent = 'W';

        Point[] blackPoints = g.getPlaceableLocations(player, opponent);
        Point[] whitePoints = g.getPlaceableLocations(opponent, player);

        if (g.gameResult(blackPoints, whitePoints) == -1) {
            blackWins = "Black wins: " + g.bScore + ":" + g.wScore;
            System.out.println(blackWins);
        } else if (g.gameResult(blackPoints, whitePoints) == 0) {
            System.out.println(draw);
        } else if (g.gameResult(blackPoints, whitePoints) == 1) {
            whiteWins = "White wins: " + g.wScore + ":" + g.bScore;
            System.out.println(whiteWins);
        }
    }

    public static void main(String[] args) {
        Game b = new Game();
        start(b);
    }
}
