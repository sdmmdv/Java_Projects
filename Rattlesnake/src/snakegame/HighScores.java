/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScores {

    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;


    
    public HighScores(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        String dbURL = "jdbc:derby://localhost:1527/HighScores;";
        connection = DriverManager.getConnection(dbURL);
        String insertQuery = "INSERT INTO HIGHSCORES (TIMESTAMP, NAME, SCORE, MODE, LEVEL) VALUES (?, ?, ?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String ts = results.getString("TIMESTAMP");
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            String mode = results.getString("MODE");
            int level = results.getInt("LEVEL");
            highScores.add(new HighScore(ts, name, score, mode, level));
        }
        sortHighScores(highScores);
        return highScores;
    }


    public void putHighScore(String ts, String name, int score, String mode, int level) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();
        if (highScores.size() < maxScores) {
            insertScore(ts, name, score, mode, level);
        } else {
            int leastScore = highScores.get(highScores.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(ts, name, score, mode, level);
            }
        }
    }

    /**
     * Sort the high scores in descending order.
     * @param highScores 
     */
    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    private void insertScore(String ts,String name, int score, String mode, int level) throws SQLException {
        insertStatement.setString(1, ts);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, score);
        insertStatement.setString(4, mode);
        insertStatement.setInt(5, level);
        insertStatement.executeUpdate();
    }

    /**
     * Deletes all the highscores with score.
     *
     * @param score
     */
    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
}
