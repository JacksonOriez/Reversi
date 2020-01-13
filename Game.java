
/**
 * CS18000 - Fall 2018
 *
 * Project 2 - Reversi
 *
 * Implementation of the game mechanics in Reversi
 *
 * @author Imtiaz Karim, karim7@purdue.edu
 * @author Marina Haliem, mwadea@purdue.edu
 * @author Evan Wang, wang3407@purdue.edu
 * @author Jackson Oriez, oriezj@purdue.edu
 * @version 1017/2018
 */

public class Game {

    private final char[][] board;
    public int wScore;
    public int bScore;
    private final char[] boardX = {'1', '2', '3', '4', '5', '6', '7', '8'};

    public Game() {
        board = new char[][]{
            {'_', '_', '_', '_', '_', '_', '_', '_', },
            {'_', '_', '_', '_', '_', '_', '_', '_', },
            {'_', '_', '_', '_', '_', '_', '_', '_', },
            {'_', '_', '_', 'W', 'B', '_', '_', '_', },
            {'_', '_', '_', 'B', 'W', '_', '_', '_', },
            {'_', '_', '_', '_', '_', '_', '_', '_', },
            {'_', '_', '_', '_', '_', '_', '_', '_', },
            {'_', '_', '_', '_', '_', '_', '_', '_', },
        };
    }

    public void displayBoard(Game b) {
        /**
         * @param b The game board to be displayed
         */
        System.out.println("  " + boardX[0] + " " + boardX[1] + " " + boardX[2] + " " + boardX[3] + " " + boardX[4] +
                " " + boardX[5] + " " + boardX[6] + " " + boardX[7]);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    System.out.print(boardX[i] + " " + b.board[i][j] + " ");
                } else if (j == b.board.length - 1) {
                    System.out.print(b.board[i][j] + "\n");
                } else {
                    System.out.print(b.board[i][j] + " ");
                }
            }
        }
    }

    //There are three cases black win = -1, white win = 1, draw = 0 

    public int gameResult(Point[] whitePlaceableLocations, Point[] blackPlaceableLocations) {
        /**
         * @param whitePlaceableLocations Array of possible moves for white
         * @param blackPlaceableLocations Array of possible moves for white
         * @return The integer corresponding to the game result: -1 for black win, 0 for draw, 1 for white win
         */

        boolean whiteDone = false;
        boolean blackDone = false;

        for (int i = 0; i < whitePlaceableLocations.length; i++) {
            if (whitePlaceableLocations[i].x != -1 && whitePlaceableLocations[i].y != -1) {
                whiteDone = true;
                i = whitePlaceableLocations.length;
            } else {
                whiteDone = false;
            }
        }

        for (int i = 0; i < blackPlaceableLocations.length; i++) {
            if (blackPlaceableLocations[i].x != -1 && blackPlaceableLocations[i].y != -1) {
                blackDone = true;
                i = whitePlaceableLocations.length;
            } else {
                blackDone = false;
            }
        }

        int result = 2;

        if (blackDone && whiteDone && wScore > bScore) {
            result = 1;
        } else if (blackDone && whiteDone && wScore < bScore) {
            result = -1;
        } else if (blackDone && whiteDone) {
            result = 0;
        }
        return result;
    }

    public Point[] getPlaceableLocations(char player, char opponent) {
        /**
         * @param player Current player
         * @param opponent player's opponent
         * @return placeablePositions array, with corresponding point for a valid location and (-1,-1) for an invalid
         * location
         */

        Point[] placeablePositions = new Point[64];
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;
        boolean upright = false;
        boolean upleft = false;
        boolean downright = false;
        boolean downleft = false;
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board[i][j] == player) {
                    placeablePositions[counter] = new Point(-1, -1);
                    counter++;
                } else if (board[i][j] == opponent) {
                    placeablePositions[counter] = new Point(-1, -1);
                    counter++;
                } else {
                    if (board[i][j] == '_' && j != 7) {
                        if (board[i][j + 1] != opponent) {
                            right = false;
                        } else if (2 + j < 8) {
                            for (int k = 2; k + j < 8; k++) {
                                if (board[i][j + k] == player) {
                                    right = true;
                                    break;
                                } else if (board[i][j + k] != opponent) {
                                    right = false;
                                    break;
                                } else if (board[i][j + k] == opponent && k + j == 7) {
                                    right = false;
                                    break;
                                }
                            }
                        } else {
                            right = false;
                        }
                    }

                    if (board[i][j] == '_' && j != 0) {
                        if (board[i][j - 1] != opponent) {
                            left = false;
                        } else if (j - 2 > 0) {
                            for (int k = 2; j - k > 0; k++) {
                                if (board[i][j - k] == player) {
                                    left = true;
                                    break;
                                } else if (board[i][j - k] != opponent) {
                                    left = false;
                                    break;
                                } else if (board[i][j - k] == opponent && j - k == 0) {
                                    left = false;
                                    break;
                                }
                            }
                        } else {
                            left = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 7) {
                        if (board[i + 1][j] != opponent) {
                            down = false;
                        } else if (2 + i < 8) {
                            for (int k = 2; k + i < 8; k++) {
                                if (board[i + k][j] == player) {
                                    down = true;
                                    break;
                                } else if (board[i + k][j] != opponent) {
                                    down = false;
                                    break;
                                } else if (board[i + k][j] == opponent && k + i == 7) {
                                    down = false;
                                    break;
                                }
                            }
                        } else {
                            down = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 0) {
                        if (board[i - 1][j] != opponent) {
                            up = false;
                        } else if (i - 2 > 0) {
                            for (int k = 2; i - k > 0; k++) {
                                if (board[i - k][j] == player) {
                                    up = true;
                                    break;
                                } else if (board[i - k][j] != opponent) {
                                    up = false;
                                    break;
                                } else if (board[i - k][j] == opponent && i - k == 0) {
                                    up = false;
                                    break;
                                }
                            }
                        } else {
                            up = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 7 && j != 7) {
                        if (board[i + 1][j + 1] != opponent) {
                            downright = false;
                        } else if (i + 2 < 8 && j + 2 < 8) {
                            for (int k = 2; i + k < 8 && j + k < 8; k++) {
                                if (board[i + k][j + k] == player) {
                                    downright = true;
                                    break;
                                } else if (board[i + k][j + k] != opponent) {
                                    downright = false;
                                    break;
                                } else if (board[i + k][j + k] == opponent && k + j == 7 && k + i == 7) {
                                    downright = false;
                                    break;
                                }
                            }
                        } else {
                            downright = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 0 && j != 0) {
                        if (board[i - 1][j - 1] != opponent) {
                            upleft = false;
                        } else if (i - 2 > 0 && j - 2 > 0) {
                            for (int k = 2; i - k > 0 && j - k > 0; k++) {
                                if (board[i - k][j - k] == player) {
                                    upleft = true;
                                    break;
                                } else if (board[i - k][j - k] != opponent) {
                                    upleft = false;
                                    break;
                                } else if (board[i - k][j - k] == opponent && j - k == 1 && i - k == 1) {
                                    upleft = false;
                                    break;
                                }
                            }
                        } else {
                            upleft = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 7 && j != 0) {
                        if (board[i + 1][j - 1] != opponent) {
                            downleft = false;
                        } else if (i + 2 < 8 && j - 2 > 0) {
                            for (int k = 2; i + k < 8 && j - k > 0; k++) {
                                if (board[i + k][j - k] == player) {
                                    downleft = true;
                                    break;
                                } else if (board[i + k][j - k] != opponent) {
                                    downleft = false;
                                    break;
                                } else if (board[i + k][j - k] == opponent && k + i == 7 && j - k == 1) {
                                    downleft = false;
                                    break;
                                }
                            }
                        } else {
                            downleft = false;
                        }
                    }

                    if (board[i][j] == '_' && i != 0 && j != 7) {
                        if (board[i - 1][j + 1] != opponent) {
                            upright = false;
                        } else if (j + 2 < 8 && i - 2 > 0) {
                            for (int k = 2; j + k < 8 && i - k > 0; k++) {
                                if (board[i - k][j + k] == player) {
                                    upright = true;
                                    break;
                                } else if (board[i - k][j + k] != opponent) {
                                    upright = false;
                                    break;
                                } else if (board[i - k][j + k] == opponent && k + j == 7 && i - k == 1) {
                                    upright = false;
                                    break;
                                }
                            }
                        } else {
                            upright = false;
                        }
                    }
                    if (up || down || left || right || upleft || upright || downleft || downright) {
                        placeablePositions[counter] = new Point(i, j);
                        counter++;
                    } else {
                        placeablePositions[counter] = new Point(-1, -1);
                        counter++;
                    }
                }
            }
        }

        return placeablePositions;
    }

    public void showPlaceableLocations(Point[] locations, char player, char opponent) {
        /**
         * @param locations Array containing placeable locations for the player
         * @param player Current player
         * @param opponent player's opponent
         */
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].x != -1 && locations[i].y != -1) {
                board[locations[i].x][locations[i].y] = '*';
            }
        }
    }

    public void placeMove(Point p, char player, char opponent) {
        /**
         * @param p Point corresponding to the location of the piece to be placed
         * @param player Current player
         * @param opponent player's opponent
         */
        board[p.x][p.y] = player;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < boardX.length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = '_';
                }
            }
        }

        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;
        boolean upright = false;
        boolean upleft = false;
        boolean downright = false;
        boolean downleft = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player && j != 7) {
                    if (board[i][j + 1] != opponent) {
                        right = false;
                    } else if (2 + j < 8) {
                        for (int k = 2; k + j < 8; k++) {
                            if (p.x == i && p.y == j + k) {
                                right = true;
                                break;
                            } else if (board[i][j + k] != opponent) {
                                right = false;
                                break;
                            } else if (board[i][j + k] == opponent && j + k == 7) {
                                right = false;
                                break;
                            }
                        }
                    } else {
                        right = false;
                    }
                }

                if (board[i][j] == player && j != 0) {
                    if (board[i][j - 1] != opponent) {
                        left = false;
                    } else if (j - 2 > 0) {
                        for (int k = 2; j - k > 0; k++) {
                            if (p.x == i && p.y == j - k) {
                                left = true;
                                break;
                            } else if (board[i][j - k] != opponent) {
                                left = false;
                                break;
                            } else if (board[i][j - k] == opponent && j - k == 1) {
                                left = false;
                                break;
                            }
                        }
                    } else {
                        left = false;
                    }
                }

                if (board[i][j] == player && i != 7) {
                    if (board[i + 1][j] != opponent) {
                        down = false;
                    } else if (2 + i < 8) {
                        for (int k = 2; k + i < 8; k++) {
                            if (p.x == i + k && p.y == j) {
                                down = true;
                                break;
                            } else if (board[i + k][j] != opponent) {
                                down = false;
                                break;
                            } else if (board[i + k][j] == opponent && i + k == 7) {
                                down = false;
                                break;
                            }
                        }
                    } else {
                        down = false;
                    }
                }

                if (board[i][j] == player && i != 0) {
                    if (board[i - 1][j] != opponent) {
                        up = false;
                    } else if (i - 2 > 0) {
                        for (int k = 2; i - k > 0; k++) {
                            if (p.x == i - k && p.y == j) {
                                up = true;
                                break;
                            } else if (board[i - k][j] != opponent) {
                                up = false;
                                break;
                            } else if (board[i - k][j] == opponent && i - k == 1) {
                                up = false;
                                break;
                            }
                        }
                    } else {
                        up = false;
                    }
                }

                if (board[i][j] == player && i != 7 && j != 7) {
                    if (board[i + 1][j + 1] != opponent) {
                        downright = false;
                    } else if (i + 2 < 8 && j + 2 < 8) {
                        for (int k = 2; i + k < 8 && j + k < 8; k++) {
                            if (p.x == i + k && p.y == j + k) {
                                downright = true;
                                break;
                            } else if (board[i + k][j + k] != opponent) {
                                downright = false;
                                break;
                            } else if (board[i + k][j + k] == opponent && k + j == 7 && k + i == 7) {
                                downright = false;
                                break;
                            }
                        }
                    } else {
                        downright = false;
                    }
                }

                if (board[i][j] == player && i != 0 && j != 0) {
                    if (board[i - 1][j - 1] != opponent) {
                        upleft = false;
                    } else if (i - 2 > 0 && j - 2 > 0) {
                        for (int k = 2; i - k > 0 && j - k > 0; k++) {
                            if (p.x == i - k && p.y == j - k) {
                                upleft = true;
                                break;
                            } else if (board[i - k][j - k] != opponent) {
                                upleft = false;
                                break;
                            } else if (board[i - k][j - k] == opponent && j - k == 1 && i - k == 1) {
                                upleft = false;
                                break;
                            }
                        }
                    } else {
                        upleft = false;
                    }
                }

                if (board[i][j] == player && i != 7 && j != 0) {
                    if (board[i + 1][j - 1] != opponent) {
                        downleft = false;
                    } else if (i + 2 < 8 && j - 2 > 0) {
                        for (int k = 2; i + k < 8 && j - k > 0; k++) {
                            if (p.x == i + k && p.y == j - k) {
                                downleft = true;
                                break;
                            } else if (board[i + k][j - k] != opponent) {
                                downleft = false;
                                break;
                            } else if (board[i + k][j - k] == opponent && k + i == 7 && j - k == 1) {
                                downleft = false;
                                break;
                            }
                        }
                    } else {
                        downleft = false;
                    }
                }

                if (board[i][j] == player && i != 0 && j != 7) {
                    if (board[i - 1][j + 1] != opponent) {
                        upright = false;
                    } else if (j + 2 < 8 && i - 2 > 0) {
                        for (int k = 2; j + k < 8 && i - k > 0; k++) {
                            if (p.x == i - k && p.y == j + k) {
                                upright = true;
                                break;
                            } else if (board[i - k][j + k] != opponent) {
                                upright = false;
                                break;
                            } else if (board[i - k][j + k] == opponent && k + j == 7 && i - k == 1) {
                                upright = false;
                                break;
                            }
                        }
                    } else {
                        upright = false;
                    }
                }

                if (up) {
                    for (int k = 1; i - k > 0; k++) {
                        if (board[i - k][j] == opponent) {
                            board[i - k][j] = board[p.x][p.y];
                            up = false;
                        } else {
                            break;
                        }
                    }
                }

                if (down) {
                    for (int k = 1; i + k < 8; k++) {
                        if (board[i + k][j] == opponent) {
                            board[i + k][j] = board[p.x][p.y];
                            down = false;
                        } else {
                            break;
                        }
                    }
                }

                if (right) {
                    for (int k = 1; j + k < 8; k++) {
                        if (board[i][j + k] == opponent) {
                            board[i][j + k] = board[p.x][p.y];
                            right = false;
                        } else {
                            break;
                        }
                    }
                }

                if (left) {
                    for (int k = 1; j - k > 0; k++) {
                        if (board[i][j - k] == opponent) {
                            board[i][j - k] = board[p.x][p.y];
                            left = false;
                        } else {
                            break;
                        }
                    }
                }

                if (upright) {
                    for (int k = 1; i - k > 0 && j + k < 8; k++) {
                        if (board[i - k][j + k] == opponent) {
                            board[i - k][j + k] = board[p.x][p.y];
                            upright = false;
                        } else {
                            break;
                        }
                    }
                }

                if (upleft) {
                    for (int k = 1; i - k > 0 && j - k > 0; k++) {
                        if (board[i - k][j - k] == opponent) {
                            board[i - k][j - k] = board[p.x][p.y];
                            upleft = false;
                        } else {
                            break;
                        }
                    }
                }

                if (downleft) {
                    for (int k = 1; j - k > 0 && i + k < 8; k++) {
                        if (board[i + k][j - k] == opponent) {
                            board[i + k][j - k] = board[p.x][p.y];
                            downleft = false;
                        } else {
                            break;
                        }
                    }
                }

                if (downright) {
                    for (int k = 1; i + k < 8 && j + k < 8; k++) {
                        if (board[i + k][j + k] == opponent) {
                            board[i + k][j + k] = board[p.x][p.y];
                            downright = false;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public void updateScores() {
        wScore = 0;
        bScore = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'W') {
                    wScore = wScore + 1;
                } else if (board[i][j] == 'B') {
                    bScore = bScore + 1;
                }
            }
        }
    }
}
