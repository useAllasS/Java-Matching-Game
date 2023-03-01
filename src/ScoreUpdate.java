import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
public class ScoreUpdate {
    //String fileAddress = "src/highscore.txt";
    private final int remTime;
    private final int row;
    private final int col;
    private final int selectedTime;  
    
    public String playerName;
    public ScoreUpdate(int remTime, int row, int col, int selectedTime,String username) {
        this.playerName=username;
        this.remTime = remTime;
        this.row = row;
        this.col = col;
        this.selectedTime = selectedTime;
    }
    public void checkAndUpdate() {

        double finalScore = scoreCalculation(remTime, row, col, selectedTime);
        // check and update score
        boolean result = scoreControl(finalScore);
        if (result) {
            System.out.println("Score will UPDATED..!! CONGRATULATIONS...");
        }
    }
    // this method calculates score of the game.
    private double scoreCalculation(int remTime, int row, int col, int selectedTime) {
        int size = col * row;
        double finalScore;
        System.out.println("Remained time is " + remTime);
        System.out.println("Size is " + size);
        System.out.println("Total time is " + selectedTime);
        // Score Calculation
        int usedTime = selectedTime - remTime;
        System.out.println(usedTime);
        if (selectedTime == 0) {
            finalScore = 0;
        } else {

            finalScore = size * ((double) size / usedTime);
            System.out.println("--" + finalScore);
        }
        return finalScore;
    }

    // this method confirms new score is to be inserted leaderboard table.
   private boolean scoreControl(double score) {
    System.out.println("Final score is " + score);
    // get current date
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    LocalDateTime now = LocalDateTime.now();
    String game_date = dtf.format(now);
    System.out.println(game_date);
    // create SQL statement
    String sql = "INSERT INTO accounts (username, score, game_date) VALUES (?, ?, ?)";
    try (Connection conn = Database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        // set parameters
        pstmt.setString(1, playerName);
        pstmt.setDouble(2, score);
        pstmt.setString(3, game_date);
        // execute SQL statement
        int rowsUpdated = pstmt.executeUpdate();
        // check if score was successfully inserted
        return rowsUpdated > 0;
    } catch (SQLException e) {
        return false;
    }
}
}