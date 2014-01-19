package gui;

import inputOutput.SaveAndLoadHighScores;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import util.DifficultyEnum.Difficulty;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame{

	// Instance of the view for use elsewhere
	public static View currentInstance; 


	// Set up components
	private JLabel welcomeLab;
	private JPanel rightPan;
	private JPanel bottPan;
	private JPanel topPan;
	private HangmanPanel hangmanPan;
	private JButton howToPlay;
	private JLabel guessWord;
	private JTextField guessWordField;
	private JList<String> listOfLetters;
	private JRadioButton easyRadButt;
	private JRadioButton medRadButt;
	private JRadioButton hardRadButt;
	private JLabel difficultyLab;
	private JLabel scores;
	private JTextArea scoreDisplay;
	private JLabel letterHeadScroll;
	private JButton startGame;
	private JMenuBar menuBar;
	private JMenuItem save;
	private JMenuItem load;
	private JMenu file;
	private JMenu feedback;
	private JMenuItem leaveFeedBack;
	private JMenuItem quit;



	public View() {

		// Set up the components
		setupComponents();

		// Frame stuff
		setMinimumSize(new Dimension(500, 680));
		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);			// Set the frame to visible				
		setSize(700, 700);			// Set the size of the frame
		setLocationRelativeTo(null);// Loads the window in the center		
	}






	//////////////////////////////////////////////////////////////////////////
	/////// Component manipulation //////////////////////////////////////////
	
	/**
	 * Set the contents of the Jlist
	 */
	public void setJlist(String[] alpha) {
		
		// Make a new list model
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		// Set the Size for the Jlist
		dlm.setSize(alpha.length);
		
		// Loop through and assign the alpha array to the list
		for (int i=0; i<alpha.length; i++) {
			dlm.set(i, alpha[i]);
		}
		
		// Set the model
		listOfLetters.setModel(dlm);
	}
	
	

	/**
	 * Get the difficulty selected
	 */
	public Difficulty getSelectedDifficulty() {
		if (easyRadButt.isSelected()) {
			return Difficulty.EASY;
		}
		else if (medRadButt.isSelected()) {
			return Difficulty.MEDIUM;
		}
		else {
			return Difficulty.HARD;
		}		
		
	}
	
		
	/**
	 * Set the status the start game button 
	 */
	public void setStatusStartButton(boolean bool) {
		startGame.setEnabled(bool);
	}
	
	
	/**
	 * Set the status of the difficulty radio group
	 */
	public void setDifficultyRadStatus(boolean bool) {
		easyRadButt	.setEnabled(bool);
		medRadButt	.setEnabled(bool);
		hardRadButt	.setEnabled(bool);
	}
	
	
	/**
	 * Set the status of the list
	 */
	public void setLetterPickingStatus(boolean bool) {
		listOfLetters.setEnabled(bool);
	}
	
	
	/**
	 * Refresh the hangman panel
	 */
	public void refreshHangManPanel() {
		hangmanPan.repaint();
	}
	
	
	
	/**
	 * Get the selected index value from the JList
	 */
	public int getLetterSelection() {		
		return listOfLetters.getSelectedIndex();
	}
	
	
	/**
	 * Get the Value of the selection
	 */
	public String getListValueSelected() {
		return listOfLetters.getSelectedValue();
	}
	
	
	/**
	 *  Set the hangman panel drawing
	 */
	public void hangManPanWhatToDraw(int whatToDraw) {
		hangmanPan.whatToDraw = whatToDraw;
	}
	
	/**
	 * Set the text in the scores field
	 */
	public void setScoreText(String scores) {
		scoreDisplay.setText(scores);
	}
	
	/**
	 * Get the word guessed
	 */
	public String getGuessedWord() {
		return guessWordField.getText();
	}
	
	public void setGuessWordField(String msg) {
		guessWordField.setText(msg);
	}
	
	/**
	 * Set the status of the guess word field
	 */
	public void setGuessWordStatus(boolean bool) {
		guessWordField.setEnabled(bool);
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	////////// Action listeners //////////////////////////////////////////////
	
	
	/**
	 * Set up the start game Action listener
	 */
	public void startGameAction(ActionListener e) {
		startGame.addActionListener(e);
	}
	
	
	
	
	/**
	 * Set the actionListener for the Jlist
	 */
	public void guessLetterAction(MouseListener ml) {
		listOfLetters.addMouseListener(ml);
	}
	
	
	/**
	 * Set up the guess the word action
	 */
	public void guessWordAction(KeyListener kl) {
		guessWordField.addKeyListener(kl);
	}
	
	
	/**
	 * Set up save action
	 */
	public void saveActionListener(ActionListener e) {
		save.addActionListener(e);
	}
	
	
	/**
	 * Set load action listener
	 */
	public void loadActionListener(ActionListener e) {
		load.addActionListener(e);
	}
	
	
	/**
	 * Feedback action listener
	 */
	public void feedBackAction(ActionListener e) {
		leaveFeedBack.addActionListener(e);
	}
	
	
	/**
	 * Quit action listener
	 */
	public void quitAction(ActionListener e) {
		quit.addActionListener(e);
	}
	
	
	
	
	
	
	
	
	
	
	




	////////////////////////////////////////////////////////////////////////
	/////// Private ///////////////////////////////////////////////////////





	/**
	 * Set up the components
	 */
	private void setupComponents() {
		// Instantiate components
		startGame	= new JButton("Start Game");
		scores		= new JLabel("Scores: ");
		rightPan 	= new JPanel();
		bottPan	 	= new JPanel();
		topPan	 	= new JPanel();
		hangmanPan	= new HangmanPanel();
		welcomeLab	= new JLabel("Welcome to Hangman");
		howToPlay	= new JButton("How to play");
		guessWord 	= new JLabel("Guess the word: ");
		easyRadButt = new JRadioButton("Easy");
		medRadButt  = new JRadioButton("Medium");
		hardRadButt = new JRadioButton("Hard");
		guessWordField	 = new JTextField(15);  // 15 Columns across
		listOfLetters	 = new JList<String>();
		difficultyLab	 = new JLabel("Difficulty:");
		scoreDisplay	 = new JTextArea();
		letterHeadScroll = new JLabel("Select a Letter");
		menuBar = new JMenuBar();
		file = new JMenu("File");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		feedback 		= new JMenu("Feedback");
		leaveFeedBack	= new JMenuItem("Leave Feedback");
		quit			= new JMenuItem("Quit and Save");
		
		// Set the custom cell renderer
		listOfLetters.setCellRenderer(new CustomJlistCellRenderer());
		
		// Set easy as the default difficulty
		easyRadButt.setSelected(true);
		
		// Create borders
		bottPan		.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.WHITE));
		topPan		.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.WHITE));
		rightPan	.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.WHITE));
		hangmanPan	.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.WHITE));
		
		// Make the radio button group
		ButtonGroup difficultyGroup = new ButtonGroup();
		difficultyGroup.add(easyRadButt);
		difficultyGroup.add(medRadButt);
		difficultyGroup.add(hardRadButt);

		// Sort the panels layout manager
		topPan 	.setLayout(new MigLayout());
		rightPan.setLayout(new BoxLayout(rightPan, MAXIMIZED_HORIZ));
		bottPan	.setLayout(new MigLayout());

		// Add components to the top panel
		topPan.add(welcomeLab, "wrap");
		topPan.add(howToPlay);
		
		// Add Jlist to a scrollpane
		JScrollPane listOfLettersScroll = new JScrollPane(listOfLetters);
		listOfLettersScroll.setPreferredSize(new Dimension(110, 30));
		listOfLettersScroll.setColumnHeaderView(letterHeadScroll);
		listOfLetters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Change the list to be single selection
		
		// Add components to the left panel
		rightPan.add(listOfLettersScroll);
		
		// Set up the textArea
		JScrollPane scoreScroll = new JScrollPane(scoreDisplay);
		scoreScroll.setColumnHeaderView(scores);
		scoreScroll.setPreferredSize(new Dimension(200, 100));
		scoreDisplay.setEditable(false);

		// Add components to the bottom panel
		bottPan.add(guessWord);
		bottPan.add(guessWordField,   "cell 0 1, wrap");
		bottPan.add(difficultyLab);
		bottPan.add(easyRadButt,  "cell 0 2");
		bottPan.add(medRadButt,   "cell 0 2");
		bottPan.add(hardRadButt,  "cell 0 2,  wrap");
		bottPan.add(scoreScroll,  "wrap");
		bottPan.add(startGame);

		// Add components to the frame
		add(topPan, 	BorderLayout.PAGE_START);
		add(bottPan, 	BorderLayout.PAGE_END);
		add(hangmanPan, BorderLayout.CENTER);
		add(rightPan,	BorderLayout.LINE_END);
		
		// Set up the menu
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(feedback);
		feedback.add(leaveFeedBack);
		file.add(save);
		file.add(load);
		file.add(new JSeparator());
		file.add(quit);
		
		// Set mnemonics
		file.setMnemonic('f');
		save.setMnemonic('s');
		load.setMnemonic('l');
		feedback.setMnemonic('b');
		leaveFeedBack.setMnemonic('l');
		easyRadButt.setMnemonic('e');
		medRadButt.setMnemonic('m');
		hardRadButt.setMnemonic('h');
		
		// Disable the list of letters until the game has started
		listOfLetters.setEnabled(false);	
		guessWordField.setEnabled(false);
		
		// Set up very basic action listeners
		setUpBasicActions();
		
		// Set the icons
		setIconImage(new ImageIcon("images/hangmanIc.png").getImage());
	}

	
	private void setUpBasicActions() {
		
		// Display a helpful message
		howToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.showHelpMessage("How to Play", "A word is chosen at random. Pick a letter, if you are wrong the man will be drawn in the gallows.\n" +
										"If you have not guessed the word correctly by the time the image is fully drawn you lose the game.\n" +
										"20 points for easy\n40 points for medium\n60 points for hard");
			}
		});
		

	}















}
