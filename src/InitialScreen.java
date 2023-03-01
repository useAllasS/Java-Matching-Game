import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.*;
import java.util.List;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class InitialScreen {
    // initialize class variables/fields.
    private String username ;
    private JPanel rulesPanel = new JPanel();
    private JPanel diffPanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel oppPanel = new JPanel();
    private JPanel rowColPanel = new JPanel();
    private JButton buttonPanel = new JButton();
    private JComboBox<String> rowsNew = new JComboBox<String>();
    private JComboBox<String> colsNew = new JComboBox<String>();
    private List<String> playersName = new ArrayList<String>();
    String usern;
    private JFrame frame = new JFrame("Memory Matching Game");
    public Settings gameParams = new Settings();
       public static void main(String[] args) {
        //InitialScreen game = new InitialScreen();
        //game.initLaunchScreen();
    }
    // this method displays initial window to receive game parameters.
    // this parameters updates Settings class.
    public void initLaunchScreen() {
        login user= new login();
        username=user.getUsername();
    // Add the frame, panel for the initial window.
    JPanel panelAll = new JPanel();
    // Define as Box Layout
    panelAll.setBorder(new EmptyBorder(5, 5, 5, 5));
    //panelAll.setLayout(new BoxLayout(panelAll,BoxLayout.Y_AXIS));
    panelAll.setLayout(new GridLayout(6, 5, 5, 5));
    // Add Rule Button
    addRuleButton(panelAll);
    // Add Difficulty Level Button
    addDiffLevelButton(panelAll);
    // Add Time Info Level Button
    addTimeButton(panelAll);
    // Add Name/Opponent Button
    addOpponentButton(panelAll);
    // Add Size information of the game.
    addRowColumnButton(panelAll);
    // initialize final layer buttons
    buttonPanel.setLayout(new GridLayout(1, 5, 5, 5));
    // add final layer buttons successively.
    addCardThemeButton(buttonPanel);
    addThemeChangeButton(buttonPanel);
    addLeaderBoardButton(buttonPanel);
    addStartButton(buttonPanel);
    addExitButton(buttonPanel);
    // add final layer into whole panel.
    panelAll.add(buttonPanel);
    // create a new JLabel with the GIF as the icon
    ImageIcon gifIcon = new ImageIcon(getClass().getResource("icon/oprah.gif"));
    JLabel gifLabel = new JLabel(gifIcon);
    // set the label to be opaque so it covers the entire frame
    gifLabel.setOpaque(true);
    // make the frame's content pane use a BorderLayout
    frame.getContentPane().setLayout(new BorderLayout());
    // add the gifLabel to the content pane as the background
    frame.getContentPane().add(gifLabel, BorderLayout.CENTER);
    // add the panelAll to the content pane, it will be stacked on top of the gifLabel
    frame.getContentPane().add(panelAll, BorderLayout.PAGE_END);
    // set the default close operation for the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // make the frame visible
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}


    
    
    
    
    
    
    
    
    
    
    // This is a method to add Rule Info button.
    private void addRuleButton(JPanel panel) {
        // Add rule panel
        JButton b1 = new JButton("Rules About Memory Game");
        rulesPanel.add(b1);
        b1.addActionListener((ActionEvent e) -> {
            // Basic info about game.
           JOptionPane.showMessageDialog(null, """
                                               Welcome to the Matching Game! This game can be played as single or double.
                                               To win, you need to match all pairs on the game board by clicking on them.
                                               It's easy to play: just click on the cards to flip them over and reveal what's on the other side.
                                               Find all the matching pairs to finish the game.
                                               Feeling up for a challenge? Try increasing the difficulty level for an extra twist.""");
        });
        panel.add(rulesPanel);
    }
    
    
    
    
    
    
    
    
    
    
    
    // This is a method to add Difficulty Level Button.
    private void addDiffLevelButton(JPanel panel) {

        // create and initialize panel with 1 rows 4 columns format.
        diffPanel.setLayout(new GridLayout(1, 4, 5, 5));

        // add buttons for panel.
        JRadioButton button1 = new JRadioButton("Easy");
        JRadioButton button2 = new JRadioButton("Medium");
        JRadioButton button3 = new JRadioButton("Difficult");

        // create 3 buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);

        JLabel radioLabel = new JLabel("Difficulty Level: ");
        radioLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        button1.setHorizontalAlignment(SwingConstants.CENTER);
        button2.setHorizontalAlignment(SwingConstants.CENTER);
        button3.setHorizontalAlignment(SwingConstants.CENTER);
        radioLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        diffPanel.add(radioLabel);
        diffPanel.add(button1);
        diffPanel.add(button2);
        diffPanel.add(button3);

        // add final panel into the given panel.
        panel.add(diffPanel);


        // In addition to that we need to add action listeners for this button
        // This button information will determine difficulty level of the game.

        // Add actions to the whole button..!
        button1.addActionListener(this::actionPerformedDiffLevelButton);
        button2.addActionListener(this::actionPerformedDiffLevelButton);
        button3.addActionListener(this::actionPerformedDiffLevelButton);
    }
    
    
    
    
    
    
    
    
    
    
    
    // This is a method to add time information button.
    private void addTimeButton(JPanel panel) {
        // create panel template.
        timePanel.setLayout(new GridLayout(1, 5, 5, 5));
        JRadioButton b11 = new JRadioButton("0 sec");
        JRadioButton b22 = new JRadioButton("10 sec");
        JRadioButton b33 = new JRadioButton("60 sec");
        JRadioButton b44 = new JRadioButton("90 sec");
        JRadioButton b55 = new JRadioButton("120 sec");

        // Add Time information
        ButtonGroup groupTime = new ButtonGroup();
        groupTime.add(b11);
        groupTime.add(b22);
        groupTime.add(b33);
        groupTime.add(b44);
        groupTime.add(b55);

        // Add ActionListeners for each button.
        b11.addActionListener(this::actionPerformedTimeButton);
        b22.addActionListener(this::actionPerformedTimeButton);
        b33.addActionListener(this::actionPerformedTimeButton);
        b44.addActionListener(this::actionPerformedTimeButton);
        b55.addActionListener(this::actionPerformedTimeButton);

        // Create and Add Time Label Information.
        JLabel timeLabel = new JLabel("Time Setting: ");

        b11.setHorizontalAlignment(SwingConstants.CENTER);
        b22.setHorizontalAlignment(SwingConstants.CENTER);
        b33.setHorizontalAlignment(SwingConstants.CENTER);
        b44.setHorizontalAlignment(SwingConstants.CENTER);
        b55.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Merge all info inthe the final panel.
        timePanel.add(timeLabel);
        timePanel.add(b11);
        timePanel.add(b22);
        timePanel.add(b33);
        timePanel.add(b44);
        timePanel.add(b55);
        panel.add(timePanel);
    }
    
    
    
    
    
    
    
    
    
    // This is a method to add player number count.
    private void addOpponentButton(JPanel panel) {
    // Add opponent Type
    oppPanel.setLayout(new GridLayout(1, 3, 5, 5));
        
    JRadioButton button21 = new JRadioButton(("Double"));
    JLabel radioLabel1 = new JLabel("Opponent Type: ");
    radioLabel1.setHorizontalAlignment(SwingConstants.CENTER);

    ButtonGroup groupOpp = new ButtonGroup();
    groupOpp.add(button21);

    // add buttons into action listener.
    button21.addActionListener(this::actionPerformedOpponentButton);

    button21.setHorizontalAlignment(SwingConstants.CENTER);
    oppPanel.add(radioLabel1);
    oppPanel.add(button21);
    
    panel.add(oppPanel);
} 

    // This is method to add size(col and row) of the game.
    private void addRowColumnButton(JPanel panel) {

        // Add row-col info.
        rowColPanel.setLayout(new GridLayout(1, 4, 5, 5));
        //create a new label
        JLabel rowLabel = new JLabel("# of rows ");
        JLabel colLabel = new JLabel("# of cols ");


        //String array to store weekdays
        String[] rows = {"1", "2", "3","4", "5", "6"};
        String[] cols = {"2", "4", "6", "8"};

        // Add rows/col into the array.
        for (String number : rows) {
            rowsNew.addItem(number);
        }

        for (String number : cols) {
            colsNew.addItem(number);
        }

        // initial status is : NOT Selected.
        rowsNew.setSelectedItem(null);
        colsNew.setSelectedItem(null);

        // add actions for two buttons.
        rowsNew.addActionListener(this::actionPerformedRowColButton);
        colsNew.addActionListener(this::actionPerformedRowColButton);

        // modify locations of buttons.
        rowLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        colLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        // merge all buttons into the panel.
        rowColPanel.add(rowLabel);
        rowColPanel.add(rowsNew);
        rowColPanel.add(colLabel);
        rowColPanel.add(colsNew);
        panel.add(rowColPanel);
    }
    
    
    
    
    
    
    
    // This is a method to add CardThem Button in the final layer.
    private void addCardThemeButton(JButton button) {
        // initialize button and add action listener/panel.
        JButton bf0 = new JButton("Card Theme");
        bf0.addActionListener(this::actionPerformedCardThemeButton);
        button.add(bf0);
    }
    
    
    
    
    
    
    // Change Background Color of the game.
    private void addThemeChangeButton(JButton button) {
        // add Theme Change button and action listener.
        JButton bf1 = new JButton("Change Background");
        bf1.addActionListener(this::actionPerformedBackColorTypeButton);
        button.add(bf1);
    }
    // add high score board in the initial window.
    private void addLeaderBoardButton(JButton button) {
    // Add High Scores Button and action listener.
    JButton bf2 = new JButton("High Scores");
    bf2.addActionListener(this::actionPerformedLeaderBoardButton);
    button.add(bf2);
}

    
    
    
    
    
    // add start button.
    private void addStartButton(JButton button) {
        // add start button and its action.
        JButton bf3 = new JButton("Start");
        bf3.addActionListener(this::actionPerformedStartButton);
        button.add(bf3);
    }





// this button exits the window.
    private void addExitButton(JButton button) {
        // add exit button and its action.
        JButton bf4 = new JButton("Exit");
        bf4.addActionListener(this::actionPerformedExitButton);
        button.add(bf4);
    }








// display the highest score by reading a file.
   private void scoreDisplay() {
    String[] columnNames = {"Usernames", "Total Points", "Date"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    try {
        // Connect to the database and retrieve the user names, scores, and dates.
        try (Connection connection = Database.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT username, score, game_date FROM accounts ORDER BY score DESC";
            ResultSet resultSet = statement.executeQuery(query);
            // Add the data to the table model.
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                float score = resultSet.getInt("score");
                String date = resultSet.getString("game_date");
                Object[] rowData = {username, score, date};
                tableModel.addRow(rowData);
            }
            // Close the database connection.
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Display an error message to the user.
        JOptionPane.showMessageDialog(null, "Failed to retrieve scores from database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Create a JTable and apply the custom TableCellRenderer.
    JTable table = new JTable(tableModel);
    table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    JScrollPane scrollPane = new JScrollPane(table);
    JOptionPane.showMessageDialog(null, scrollPane, "Leaderboard", JOptionPane.PLAIN_MESSAGE);
}

   
   
   
   
    // add actionListener for Exit Button.
    private void actionPerformedExitButton(ActionEvent e) {
        //Write code here to close the program.
        System.out.println("Program is closed ");
        System.exit(0);
    }




// add actionListener for Start Button.
    private void actionPerformedStartButton(ActionEvent e) {
        // Write code here to start the program.
        // After parameters are collected, let's initialize game.
        frame.setVisible(false);
        frame.dispose();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // determine size of the game : Single or Multi Player.
        if (gameParams.getPlayersName().size() == 1) {
            PlayGameWithSingle startGame = new PlayGameWithSingle(gameParams);
            login user= new login();
            startGame.username=user.getUsername();
            // Someone can exit board.
            startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set background color.
            startGame.setBackground(Color.gray);
            // Other Board remaining initializations.
            startGame.setPreferredSize(new Dimension(600, 600)); //need to use this instead of setSize
            startGame.pack();
            startGame.setLocationRelativeTo(null); 
            startGame.setVisible(true);
        } 
    }// add actionListener for Start Button.
   private void actionPerformedLeaderBoardButton(ActionEvent e) {
    try {
        scoreDisplay();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}// add actionListener for Background Color Button.
    private void actionPerformedBackColorTypeButton(ActionEvent e) {
        // show possible color options.
        String[] options = new String[]{"White", "Gray", "Yellow", "Blue", "Green"};
        int option = JOptionPane.showOptionDialog(null, "Choose Background", "Option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options);

        if (option != JOptionPane.CLOSED_OPTION) {
            System.out.println(options[option]);

            try {
                Color backgroundColor = (Color) Color.class.getField(options[option].toLowerCase()).get(null);

                gameParams.setBackColor(backgroundColor);

            } catch (IllegalAccessException | NoSuchFieldException ex) {
            }
       } else {
            System.out.println("No option selected");
        }
    }
    // add actionListener for Background Color Button.
    private void actionPerformedCardThemeButton(ActionEvent e) {
        //declarations:
        String[] options = new String[]{"classes", "keywords", "methdos"};
        int option = JOptionPane.showOptionDialog(null, "Choose Background", "Option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options);
        if (option != JOptionPane.CLOSED_OPTION) {
            System.out.println(options[option]);
            // receive selected theme and save it.
            switch (options[option]) {
                case "classes" -> {
                    System.out.println("Selected Theme : classes");
                    gameParams.setCardTheme("classes");
                }
                case "methods " -> {
                    System.out.println("Selected Theme :  methdos");
                    gameParams.setCardTheme("methods");
                }
                case "keywords" -> {
                    System.out.println("Selected Theme : keywords");
                    gameParams.setCardTheme("keywords");
                }
            }
        } else {
            System.out.println("No option selected");
        }
    }// add actionListener for number of row and column buttons.
    private void actionPerformedRowColButton(ActionEvent e) {
        // add actions based on row/column information
        if (e.getSource() == rowsNew) {
            String s = (String) rowsNew.getSelectedItem();
            assert s != null;
            int s2 = Integer.parseInt(s);
            System.out.println("Row button is selected as : " + s);
            gameParams.setrowId(s2);
        } else if (e.getSource() == colsNew) {

            String s3 = (String) colsNew.getSelectedItem();
            System.out.println("Column button is selected as : " + s3);
            String s33 = (String) colsNew.getSelectedItem();
            assert s33 != null;
            int s4 = Integer.parseInt(s33);
            gameParams.setcolId(s4);
        }
    }
    // add actionListener for opponent type information.
    private void actionPerformedOpponentButton(ActionEvent e) {
        // add playing type buttons as Single/Double.
        login user=new login();
        AbstractButton aButton = (AbstractButton) e.getSource();
        System.out.println("Selected: " + aButton.getText());
       if (aButton.getText().equals("Single")) {
            String Player1 = user.getUsername();
            if (Player1 == null) {
                System.out.println("The user canceled");
            } else {
                //set game Params.
                playersName.add(Player1);
                gameParams.setPlayersName(playersName);
            }
        } else 
         if (aButton.getText().equals("Double")) {
            String Player1 =  username;
            String Player2 = JOptionPane.showInputDialog("Please enter name for Player 2 : ");
            if (Player1 == null) {
                System.out.println("The user canceled.");
            } else {
                System.out.println("Entered name : " + Player2);
            }
            playersName.add(Player1);
            playersName.add(Player2);
            gameParams.setPlayersName(playersName);
        }}
    // add actionListener for opponent type information.
    private void actionPerformedTimeButton(ActionEvent e) {
        // Receive time information and update gameParams object.
        AbstractButton aButton = (AbstractButton) e.getSource();
        System.out.println("Selected: " + aButton.getText());
        String timeText = aButton.getText().replaceAll("[A-Za-z\\s+]", "");
        int intTimeText = Integer.parseInt(timeText);
        gameParams.setTimeInfo(intTimeText);
    }
    private void actionPerformedDiffLevelButton(ActionEvent e) {
        // Receives Difficulty Level information and Updates gameParams object.
        AbstractButton aButton = (AbstractButton) e.getSource();
        System.out.println("Selected: " + aButton.getText());
        switch (aButton.getText()) {
            case "Easy" -> gameParams.setDiffLevel(500);
            case "Medium" -> gameParams.setDiffLevel(1000);
            case "Difficult" -> gameParams.setDiffLevel(2000);
        }}
}