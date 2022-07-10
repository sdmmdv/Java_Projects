package fourgame;

import java.awt.Color;

public class Board {

    private Field[][] board;
    private final int boardSize;
    private Player actualPlayer;
    private int scorePlayer1;
    private int scorePlayer2;

    /**
     * @param boardSize as given n, a new Field of nXn dimensions
     * is created and instantiated
     */

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Field();
            }
        }
    }

    /**
     * Getters and Setters
     */

    public Field getValue(int x, int y) {
        return board[x][y];
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public void setActualPlayer(Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    /**
     * applies the colors onto the field based on corresponding player
     * @param indI indicates the row wise coordinate of a board
     * @param indJ indicates the column wise coordinate of a board
     */

    public void applyColor(int indI, int indJ) {
        if(actualPlayer == Player.Player1){
             board[indI][indJ].setColor(Color.red);
        }
        else
            board[indI][indJ].setColor(Color.blue);
    }

    /**
     * switches the turn of players as per round
     * @return actualPlayer : type of Player
     */

    public Player switchActualPlayer() {
        if (actualPlayer == Player.Player1) {
            actualPlayer = Player.Player2;
        } else {
            actualPlayer = Player.Player1;
        }
        return actualPlayer;
    }

    /**
     * Indicates the pre move turn using actualPlayer
     * to illustrate before the move on the label
     * @return P : type of Player
     */

    public Player preMoveTurn(){
        return actualPlayer == Player.Player1 ? Player.Player2 : Player.Player1;
    }

    /**
     * Depending on the actual moving player modifies(increments)
     * the score of corresponding player
     */
    public void applyScore() {
        if(actualPlayer == Player.Player1) {
            scorePlayer1++;}
        else {
            scorePlayer2++;}
    }


    /**
     * behaves as a flag to terminate the game
     * @return bool : implies true iff all the points have been scored.
     */
    public boolean isOver() {
        return scorePlayer1 + scorePlayer2 == boardSize*boardSize;
    }

    /**
     * Returns the name of Winner based on players score comparison.
     * @return String : name of the type Player
     */
    public String findWinner() {
        return scorePlayer1 > scorePlayer2 ? "Player1" : "Player2";
    }


}
