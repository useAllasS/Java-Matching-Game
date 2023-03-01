import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PlayGameWithSingle extends JFrame {
    // initalize class variables.
    
    String username;
    private int col;
    private int row;
    private int diffLevel;
    private List<String> playersName;
    private Cards choseMyCard;
    private Cards card1 = null;
    private Cards card2 = null;
    private Cards infoCard;
    private int[] gameScores;
    private int attackCardID;
    private int timeScore;
    private Color backColor;
    private int timeScoreSelected;
    private boolean timeScoreStatus;
    private Timer cardTimeControl;
    private Timer gameTimeControl;
    private List<Cards> Cards;
    private String cardTheme;
    private boolean alreadyExecuted = false;
    private File file_default = new File(PlayGameWithSingle.class.getResource("/logo3.png").getFile());
    private String DEFAULT_IMAGE = file_default.getPath();
    public PlayGameWithSingle(Settings gameParams) {
        // retrieve game information to initialize board and game settings.
        this.col = gameParams.getcolId();
        this.row = gameParams.getrowId();
        this.diffLevel = gameParams.getDiffLevel();
        this.timeScore = gameParams.getTimeInfo();
        this.playersName = gameParams.getPlayersName();
        this.gameScores = new int[gameParams.getPlayersName().size()];
        this.timeScoreStatus = gameParams.getTimeInfo() != 0;
        this.diffLevel = gameParams.getDiffLevel(); // 500:Diff - 1000:Medium - 2000:Easy
        this.timeScoreSelected = gameParams.getTimeInfo();
        this.cardTheme = gameParams.getCardTheme();
        this.backColor = gameParams.getBackColor();
        // initialize card Number and List of Card Objects.
        this.Cards = initCards(row, col, gameParams);
        // Create a BigRootPane with border layout.
        // We will put cards and info button inside.
        Container big = getRootPane();
        big.setLayout(new BorderLayout());
        //big.setBackground(Color.white);
        //Set up the board itself
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(row, col));
        pane.setBackground(backColor);
        // Create another card object for info button.
        // This will show score and time information.
        Cards infoButton = new Cards();
        infoButton.username=username;
        
        
        
        // Merge to structure into Root Pane.
        big.add(pane, BorderLayout.CENTER);
        big.add(infoButton, BorderLayout.SOUTH);
        // display game details (name, time) in the info button.
        this.infoCard = infoButton;
        infoCard.changeButtonParams(gameScores, timeScore, playersName);
        // add Card objects into the board. Set their back-side symbol.
        for (Cards comp : Cards) {
            pane.add(comp);
            comp.setIcon(new ImageIcon(DEFAULT_IMAGE));
        }
    }
    // This list initializes card list. Add action listeners to each card.
    // and return all card as list of objects.
    private List<Cards> initCards(int row, int col, Settings gameParams) {
        // create list of Card objects.
        List<Cards> listOfCards = new ArrayList<>();
        // create list of Card values.
        List<Integer> valuesOfCards = new ArrayList<>();
        // Calculate total number of buttons for the game.
        int pairs = (row * col) / 2;
        //String pairS = String.valueOf(pairs);
        //System.out.println("Pair is : " + pairS);
        // Create two card lists as many as pairs.
        for (int j = 1; j <= pairs; j++) {
            valuesOfCards.add(j);
            valuesOfCards.add(j);
        }
        // Mix card values randomly.
        Collections.shuffle(valuesOfCards);
        // This is the ID of the attack card in difficult game.
        attackCardID = valuesOfCards.get(0);
        System.out.println("Attacked Card : " + attackCardID);
        // add actions to each card. Then, return card objects.
        for (Integer valuesOfCard : valuesOfCards) {
            // Create Card object for each image.
            Cards mySelect = new Cards();
            mySelect.setCardNo(valuesOfCard);
            // Add action listener to change image of the card.
            mySelect.addActionListener((ActionEvent ae) -> {
                // assign mySelect as chosen card and then call flipCards method.
                choseMyCard = mySelect;
                // Flip cards will be called after any of cards is pressed.
                flipCardsSingle();
            });
            // Add all buttons into one list to process them in a board.
            listOfCards.add(mySelect);
        }
        //System.out.println(listOfCards.toString());
        return listOfCards;
    }
    // this is a method to flip cards back after both are opened.
    private void cardTimeCounter() {

        //set up the timer
        cardTimeControl = new Timer(750, (ActionEvent ae) -> {
            controlCards();
        });

        cardTimeControl.setRepeats(false);
        cardTimeControl.start();
    }
    // This method fills card1 and card2 objects according to actions in the board.
    // Each button has action listeners so that choseMyCard object is filled.
    private void flipCardsSingle() {
        // This condition helps us to understand first card is selected.
        if (card1 == null && card2 == null) {
            // start timer of the game once.
            if (!alreadyExecuted) {
                trackTime();
                alreadyExecuted = true;
            }
            System.out.println("Card 1 is selected");
            // After card is chosen, image need to be showed.
            card1 = choseMyCard;
            // In this step, image was taken according to card no.
            String no = String.valueOf(card1.getCardNo());
            // Then, card-button displays image.
            card1.setImageVisible(no, cardTheme);
        }
        // This case helps us to select second card.
        else if (card1 != null && card1 != choseMyCard && card2 == null) {
            // Assign selected card as my new card.
            card2 = choseMyCard;
            // Then, get card no of it.
            String no = String.valueOf(card2.getCardNo());
            // Finally, card object shows its image.
            card2.setImageVisible(no, cardTheme);
            // start time to come back.
            cardTimeCounter();
        }
    }
    // This method checks both selected cards. They can stay opened or they come back (closed).
   private void controlCards() {
    if (card1.getCardNo() == card2.getCardNo()) {

        int no = card1.getCardNo();
        String word = Database.retrieveWordFromDatabase(no, cardTheme);
        String definition = Database.retrieveDefinitionFromDatabase(word, cardTheme);
        JOptionPane optionPane = new JOptionPane("Word: " + word + " \t\nDefinition: " + definition, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Word and Definitions");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        JButton closeButton = new JButton("Close");
        JButton okButton = new JButton("OK");
        optionPane.setOptions(new Object[] { okButton, closeButton });

        // Define the timer as an instance variable of the class
        Timer timer = new Timer(5000, (ActionEvent e) -> {
            if (dialog.isVisible()) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        okButton.addActionListener((ActionEvent e) -> {
            dialog.dispose();
            timer.stop();
        });
        closeButton.addActionListener((ActionEvent e) -> {
            dialog.dispose();
            timer.stop();
        });
    


        //match condition
            card1.setEnabled(false); //disables the button
            card2.setEnabled(false);
        // existing code here
        // Retrieve and display message with timer
            card1.setCardMatchedInfo(true); //flags the button as having been matched
            card2.setCardMatchedInfo(true);
            gameScores[0] = gameScores[0] + 1;

            if (this.checkWinning()) {
                //JOptionPane.showMessageDialog(this, "You win!");
                //System.exit(0);
                 // Create a JPanel with the GIF as the background and the message label
    ImageIcon icon = new ImageIcon(getClass().getResource("icon/winner.gif"));
    JLabel gifLabel = new JLabel(icon);
    gifLabel.setLayout(new BorderLayout());

    JLabel messageLabel = new JLabel("<html>Congratulations. You are the Champion.<br>Do you want to play this game again ?</html>");
    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    messageLabel.setVerticalAlignment(SwingConstants.CENTER);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
    messageLabel.setForeground(Color.WHITE); // set the text color to white
    gifLabel.add(messageLabel, BorderLayout.CENTER);

    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    messageLabel.setVerticalAlignment(SwingConstants.CENTER);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
    gifLabel.add(messageLabel, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(gifLabel, BorderLayout.CENTER);

    // Show the custom JOptionPane
    int response = JOptionPane.showConfirmDialog(null, panel, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

               // int response = JOptionPane.showConfirmDialog(this, "Congratulations. You are the Champion. ",
               //         "Do you want to play this game again ? ", JOptionPane.YES_NO_CANCEL_OPTION);

                if (response == 0) {
                    dispose();
                    // start new game
                    InitialScreen newGame = new InitialScreen();
                    newGame.initLaunchScreen();
                } else if (response == 1) {
                    // exit from the game.
                    System.exit(0);
                }
            }
        } else {
            // start game effect : negative effect
            startGameEffect(false);
            // Then we need to change picture of our unselected cards.
            card1.setIcon(new ImageIcon(DEFAULT_IMAGE));
            card2.setIcon(new ImageIcon(DEFAULT_IMAGE));
        }
        card1 = null; //reset c1 and c2
        card2 = null;
    }
    // This method affects game according to difficulty level.
    // Easy : Time Counter increases 1 second.
    // Medium : No effect.
    // Difficult : Time Counter decreases 1 second and an attack Card inserted.
    // If an Attack Card is selected, all opened cards flipped back
    private void startGameEffect(boolean cardMatching) {
        if (cardMatching) {
            // increase time information by one.
            if (diffLevel == 500) {
                timeScore++;
            }
        } else {
            // decrease time information by one.
            // and there is a card pair. If one them comes, all opened cards become closed.
            if (diffLevel == 2000) {
                System.out.println("In the difficult level ");
                timeScore--;
                if (card1.getCardNo() == attackCardID | card2.getCardNo() == attackCardID) {
                    //System.out.println(  "Attacked Card : " + attackCardID);
                    for (Cards comp : Cards) {
                        comp.setIcon(new ImageIcon(DEFAULT_IMAGE));
                        comp.setEnabled(true);
                        comp.setCardMatchedInfo(false);
                    }
                    // make game score zero.
                    gameScores[0] = 0 ;
                }
            }
        }
    }
    private void startShuffleEffect(Settings gameParams) {
        Cards = initCards(row, col, gameParams);
        for (Cards comp : Cards) {
            comp.setIcon(new ImageIcon(DEFAULT_IMAGE));
            comp.setEnabled(true);
            comp.setCardMatchedInfo(false);
        }
        // make game score zero.
        gameScores[0] = 0 ;
    }
    // This method checks winning state.
    private boolean checkWinning() {
        for (Cards c : this.Cards) {
            if (!c.getCardMatchedInfo()) {
                return false;
            }
        }
        infoCard.changeButtonParams(gameScores, timeScore, playersName);
        // stop timer
        stopTime();
        // update scoreboard
        ScoreUpdate gameScoreUpdate = new ScoreUpdate(timeScore, row, col, timeScoreSelected,  username);
        gameScoreUpdate.checkAndUpdate();
        return true;
    }
    // This method counts time and change infoButton status (score-time info.)
    private void trackTime() {
        // Define timer with 1 second period.
        gameTimeControl = new Timer(1000, (ActionEvent ae) -> {
            //System.out.println("test timer has started");
            // Control timeCounter is true or false. If it is true, time will be decreased.
            // Otherwise, only infobutton will be updated.
            if (timeScoreStatus) {
                timeScore--;
                //infoCard.changeParameters(score, remTime, playerNameOne);
                infoCard.changeButtonParams(gameScores, timeScore, playersName);
                // check time information.
                if (timeScore <= 0) {
                    stopTime();
                    
                    // Create a JPanel with the GIF as the background and the message label
                    ImageIcon icon = new ImageIcon(getClass().getResource("icon/times.gif"));
                    JLabel gifLabel = new JLabel(icon);
                    gifLabel.setLayout(new BorderLayout());
                    
                    JLabel messageLabel = new JLabel("Time is UP.\nDo you want to play this game again?");
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    messageLabel.setVerticalAlignment(SwingConstants.CENTER);
                    messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    messageLabel.setForeground(Color.WHITE); // set the text color to white
                    gifLabel.add(messageLabel, BorderLayout.CENTER);
                    
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    messageLabel.setVerticalAlignment(SwingConstants.CENTER);
                    messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    gifLabel.add(messageLabel, BorderLayout.CENTER);
                    
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(gifLabel, BorderLayout.CENTER);
                    
                    // Show the custom JOptionPane
                    int response = JOptionPane.showConfirmDialog(null, panel, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if (response == 0) {
                        // start new game
                        dispose();
                        InitialScreen newGame = new InitialScreen();
                        newGame.initLaunchScreen();
                    } else if (response == 1) {
                        // exit from the game.
                        System.exit(0);
                    }
                }
            } else {
                //infoCard.changeParameters(score, remTime, playerNameOne);
                infoCard.changeButtonParams(gameScores, timeScore, playersName);
            }
        });
        gameTimeControl.start();
    }
  // stop timer.
    private void stopTime() {

        gameTimeControl.stop();
    }


}






