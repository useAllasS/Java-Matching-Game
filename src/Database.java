

import java.sql.*;
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/miniprojet?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "090909";
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }
    }
    public static String retrieveWordFromDatabase(String cardTheme){
            String message = "" ;
            String sql="select word from "+cardTheme+";";
             try (Connection conn = Database.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                message = rs.getString("word");
            }
        } catch (SQLException e) {}
        return message;
};
    public static String retrieveWordFromDatabase(int no,String cardTheme){
            String message = "" ;
            String sql="select word from "+cardTheme+" where id='"+no+"';";
             try (Connection conn = Database.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                message = rs.getString("word");
            }
        } catch (SQLException e) {}
        return message;
};
    public static String retrieveDefinitionFromDatabase(String word,String cardTheme){
           String message = "" ;
           String sql="select definitions from "+cardTheme+" where word='"+word+"';";
            try (Connection conn = Database.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                message = rs.getString("definitions");
            }
        } catch (SQLException e) {}  
        return message;
};
}
