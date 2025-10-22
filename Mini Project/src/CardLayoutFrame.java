import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class CardLayoutFrame extends JFrame
{
    // All the buttons
    private JButton startButton;
    private JButton categoryButton;
    private JButton nextButton;
    private JButton usernameButton;
    private JButton playButton;
    private JButton againButton;
    private JButton[] optionButtons = new JButton[4];
    JButton btn1, btn2, btn3, btn4;
    List<String> questions1 = new ArrayList<>();
    List<String> answers1 = new ArrayList<>();
    List<String> questions2 = new ArrayList<>();
    List<String> answers2 = new ArrayList<>();
    List<String> questions3 = new ArrayList<>();
    List<String> answers3 = new ArrayList<>();
    List<String> questions4 = new ArrayList<>();
    List<String> answers4 = new ArrayList<>();

    // Button Handler
    private ButtonHandler handler = new ButtonHandler();

    // The Card Layout
    private CardLayout cardLayout;

    // All the Panels
    private JPanel cardPanel;
    private JPanel usernamePanel;

    // All the Labels
    private JLabel questionLabel;

    // Used for Username
    private JTextField inputField;
    private String username = "player";

    // Variables needed for the questions
    private int currentQuestionIndex;
    private List<List<String>> questionGroups;
    private ArrayList<Integer> correctAnswers;
    private List<String> questionTextString;

    // Points
    private int score = 0;

    // General Layout of the Program
    public CardLayoutFrame()
    {
        // 1. instantiate components (no local shadowing)
        btn1 = new JButton("Load Set 1");
        btn2 = new JButton("Load Set 2");
        btn3 = new JButton("Load Set 3");
        btn4 = new JButton("Load Set 4");

        // 2. add to container (example panel)
        JPanel p = new JPanel();
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);
        p.add(btn4);
        add(p);

        // 3. wire listeners after instantiation
        setupButtons();

        // frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        currentQuestionIndex = 0;


        // first page - welcome
        createWelcomePanel();

        // username page
        createUsernamePanel();

        // category page
        createQuestionPanel();

        // second page - quiz
        createGamePanel();

        add(cardPanel);

        // Adds all the button handlers
        startButton.addActionListener(handler);
        usernameButton.addActionListener(handler);
        playButton.addActionListener(handler);
        nextButton.addActionListener(handler);
    }
    private void setupButtons() {
        // Optional defensive check
        if (btn1 == null || btn2 == null || btn3 == null || btn4 == null) {
            throw new IllegalStateException("One or more buttons are not initialized before setupButtons()");
        }

        btn1.addActionListener(e -> loadQA("src/questions1.txt", "src/answers1.txt", questions1, answers1));
        btn2.addActionListener(e -> loadQA("src/questions2.txt", "src/answers2.txt", questions2, answers2));
        btn3.addActionListener(e -> loadQA("src/questions3.txt", "src/answers3.txt", questions3, answers3));
        btn4.addActionListener(e -> loadQA("src/questions4.txt", "src/answers4.txt", questions4, answers4));
    }

    private void loadQA(String questionsFile, String answersFile,
                        List<String> questionsList, List<String> answersList) {
        questionsList.clear();
        answersList.clear();
        try {
            questionsList.addAll(Files.readAllLines(Paths.get(questionsFile), StandardCharsets.UTF_8));
            answersList.addAll(Files.readAllLines(Paths.get(answersFile), StandardCharsets.UTF_8));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // First Unique Panel Welcomes User
    private void createWelcomePanel()
    {
        // Creates the Panel
        JPanel welcomePanel = new JPanel(new BorderLayout());

        ImageIcon welcomeIcon = new ImageIcon("src/openImage.jpg");
        welcomePanel.add(new JLabel(welcomeIcon), BorderLayout.CENTER);

        // Adds the title
        JLabel title = new JLabel("Welcome Screen", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(title, BorderLayout.NORTH);

        // Adds the button to the panel adds it to the panel stack
        startButton = new JButton("Start game!");
        welcomePanel.add(categoryButton, BorderLayout.SOUTH);
        cardPanel.add(welcomePanel, "W");
    }

    // Second Unique Panel Allow User to add their Name and start game
    private void createUsernamePanel()
    {
        usernamePanel = new JPanel(new BorderLayout());
        JPanel usernameButtonPanel = new JPanel(new FlowLayout());

        //create input field
        inputField = new JTextField("Enter a username");
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));

        //create username button
        usernameButton = new JButton("set username");

        //add the input field and the username button to the username button panel
        usernameButtonPanel.add(inputField);
        usernameButtonPanel.add(usernameButton);

        //add this nested username button panel to the main username panel
        usernamePanel.add(usernameButtonPanel, BorderLayout.NORTH);

        // create a nested play button panel
        JPanel playButtonPanel = new JPanel(new FlowLayout());
        playButton = new JButton("Play!");
        playButtonPanel.add(playButton);
        usernamePanel.add(playButtonPanel, BorderLayout.SOUTH);

        //add this nested play button panel to the main username panel
        cardPanel.add(usernamePanel, "U");
    }

    // A button panel that allows the user to select which question set they want to use
    private void createQuestionPanel()
    {
        JPanel questionPanel = new JPanel(new BorderLayout());
        JPanel nestedButtonPanel = new JPanel(new GridLayout(2,2,10,10));

        // Adding the nested button panel to the game panel
        questionPanel.add(nestedButtonPanel,BorderLayout.CENTER);
        questionPanel.add(startButton, BorderLayout.SOUTH);

        /* remember to add this username panel to the cardPanel object, and give it a name */
        cardPanel.add(questionPanel, "Q");
    }

    // Third Unique Panel The Game
    private void createGamePanel()
    {
        JPanel gamePanel = new JPanel(new BorderLayout());

        questionLabel = new JLabel("Place holder for questions",SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD,16));
        gamePanel.add(questionLabel,BorderLayout.NORTH);

        nextButton = new JButton("Go to results");
        gamePanel.add(nextButton, BorderLayout.SOUTH);

        // Initialize the 4 buttons
        JPanel nestedButtonPanel = new JPanel(new GridLayout(2,2,10,10));
        for(int i = 0; i < 4; i++)
        {
            optionButtons[i] = new JButton("Option " + (i + 1));
            optionButtons[i].addActionListener(new OptionButtonHandler(i));
            nestedButtonPanel.add(optionButtons[i]);
        }

        // Adding the nested button panel to the game panel
        gamePanel.add(nestedButtonPanel,BorderLayout.CENTER);

        /* remember to add this username panel to the cardPanel object, and give it a name */
        cardPanel.add(gamePanel, "G");
    }


    // Fourth Unique Panel Results/Score Screen
    private void createResultsPanel()
    {
        // Adds the Result title to the Panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JLabel resultsLabel = new JLabel("Results", SwingConstants.CENTER);
        resultsLabel.setFont(new Font("Arial", Font.BOLD,24));
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);

        // Adds the Username and their score
        JPanel resultsUserNamePanel = new JPanel(new BorderLayout());
        JLabel resultsUserNameLabel = new JLabel(username + ": " + score + "/10", SwingConstants.CENTER);
        resultsUserNameLabel.setFont(new Font("Arial", Font.BOLD,16));
        resultsPanel.add(resultsUserNameLabel, BorderLayout.CENTER);

        // Adds the Result Image
        ImageIcon resultsIcon = new ImageIcon("src/resultsImage.jpg");
        resultsPanel.add(new JLabel(resultsIcon), BorderLayout.NORTH);

        // Adds in the Play again button in a smaller style on the bottom
        JPanel againButtonPanel = new JPanel(new FlowLayout());
        againButton = new JButton("Again");
        againButtonPanel.add(againButton);
        resultsPanel.add(againButtonPanel, BorderLayout.SOUTH);

        againButton.addActionListener(handler);
        cardPanel.add(resultsPanel, "R");
    }



    // Handles the Questions for the game
    private void loadNextQuestion()
    {
        if (questionGroups == null || questionGroups.isEmpty() || currentQuestionIndex >= questionGroups.size())
        {
            createResultsPanel();
            cardLayout.show(cardPanel, "R"); // Show Results
            return;
        }
        // Retrieving the Question from the array of questions for changing the prompt
        questionLabel.setText(questionGroups.get(currentQuestionIndex).get(0));
        for (int i = 0; i < 4; i++) // Try replacing 4 with optionButtons.length
        {
            optionButtons[i].setText(questionGroups.get(currentQuestionIndex).get(i +1));
        }
    }

    // Handles All other Buttons
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == startButton)
            {
                cardLayout.show(cardPanel, "U"); // show username
            }
            else if (e.getSource() == usernameButton)
            {
                //save username
                username = inputField.getText();
                //show message to welcome the user
                JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
            }
            else if (e.getSource() == playButton)
            {
                // Loads the questions
                loadNextQuestion();
                // show quiz game
                cardLayout.show(cardPanel, "G");
            }
            else if (e.getSource() == nextButton)
            {
                createResultsPanel();
                cardLayout.show(cardPanel, "R"); // show results
            }
            else if(e.getSource() == againButton)
            {
                // Restart the game back to the welcome screen and set the score to 0
                score = 0;
                cardLayout.show(cardPanel, "W");
            }
            else if(e.getSource() == categoryButton)
            {
                // Restart the game back to the welcome screen and set the score to 0
                setupButtons();
                cardLayout.show(cardPanel, "Q");
            }
        }
    }

    // Handles All buttons for the Game
    private class OptionButtonHandler implements ActionListener {


        private int index;
        public OptionButtonHandler(int index) // Allows us to get the passed value into this handler
        {
            this.index = index;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //Checking if the answer is correct
            if(index == correctAnswers.get(currentQuestionIndex))
            {
                optionButtons[index].setBackground(Color.GREEN);
                // User gets a point
                score++;
                JOptionPane.showMessageDialog(null,"Correct! Current Score " + score + "/10");
                optionButtons[index].setBackground(null);
            }
            else
            {
                optionButtons[index].setBackground(Color.RED);
                // No point
                JOptionPane.showMessageDialog(null,"Incorrect! Current Score" + score + "/10");
                optionButtons[index].setBackground(null);
            }
            //Increment the question index
            currentQuestionIndex++;
            // Go to the next question
            loadNextQuestion();
        }
    }
}
