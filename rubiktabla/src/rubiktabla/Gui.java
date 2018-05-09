package rubiktabla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author Burian Sándor
 */
class Gui  extends JFrame{
    private int     érték;
    private JLabel  welcomeMsg;
    private JLabel  nrOfSteps;
    private JPanel  gamePanel;
    
    private Game game;

    public Gui()
    {
        gamePanel = new JPanel();
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        
        addWindowListener(onExit);
        setJMenuBar(menusor());
        //twiceADouble.setEnabled(false);
        setLayout(new BorderLayout());
        JPanel  gombok = new JPanel(new FlowLayout());
        érték = 0;
        welcomeMsg = new JLabel("Állíts be méretet!", SwingConstants.CENTER);
        //gombok.add(new JButton(kattintásakció));
        gombok.add(new JButton(exitAction));
        add("Center", welcomeMsg);
        add("South", gombok);
        java.net.URL    url = Rubiktabla.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        setTitle("Rubik Tábla");
        setMinimumSize(new Dimension(500, 500));
        pack();
        setVisible(true);
    }
    
        /**
         * Add menubar, with the new game options.
         * 
         * @return 
         */
	private JMenuBar menusor()
	{
		JMenuBar    menu = new JMenuBar();                  //menü init
		JMenu       newGameMenu = new JMenu("Új játék");    //lenyíló menü1
                
		JMenuItem   twiceADoubleButton = new JMenuItem(twiceADouble);
		twiceADoubleButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Event.CTRL_MASK));
		twiceADoubleButton.setMnemonic('t');
		
                JMenuItem   fourTimesFourButton = new JMenuItem(fourTimesFour);
		fourTimesFourButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Event.CTRL_MASK));
		fourTimesFourButton.setMnemonic('f');
		
                JMenuItem   sixTimesSixButton = new JMenuItem(sixTimesSix);
		sixTimesSixButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Event.CTRL_MASK));
		sixTimesSixButton.setMnemonic('s');
                
                
		/*JMenuItem   kilép = new JMenuItem(kilépésakció);
		kilép.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK));
		kilép.setMnemonic('K');*/
		newGameMenu.setMnemonic('T');
                
		newGameMenu.add(twiceADoubleButton);
		newGameMenu.add(fourTimesFourButton);
		newGameMenu.add(sixTimesSixButton);
                
		//newGameMenu.addSeparator();
                
		//newGameMenu.add(kilép);
		menu.add(newGameMenu);
                /***/
                
		JMenu       aboutMenu = new JMenu("Névjegy");           //lenyíló menü2
                
                JMenuItem   aboutButton = new JMenuItem(about);         //lenyíló gomb
		aboutButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Event.CTRL_MASK));
		aboutButton.setMnemonic('A');
                
                aboutMenu.add(aboutButton);
                menu.add(aboutMenu);
                /***/
                
                nrOfSteps = new JLabel("Lépések száma: 0");             //menü felirat
                menu.add("East",nrOfSteps);
                
		return menu;
	}

    /**
     * Exit from the program
     */    
    private void exit()
    {
        System.exit(0);
    }

    private WindowAdapter   onExit = new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            exit();
        }
    };

    private AbstractAction  exitAction = new AbstractAction("Kilépés")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            exit();
        }
    };

    private AbstractAction  kattintásakció = new AbstractAction("Kattintás")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            érték++;
            nrOfSteps.setText("Lépések száma: " + érték);
            //twiceADouble.setEnabled(true);
        }
    };

	private AbstractAction twiceADouble = new AbstractAction("2X2")
	{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            érték = 0;
            nrOfSteps.setText("Lépések száma: 0");
            //setEnabled(false);
            game = new Game(2);
            drawTable(game, 2);
                        
            sixTimesSix.setEnabled(false);
            fourTimesFour.setEnabled(false);
            twiceADouble.setEnabled(false);
        }
    };
        /**
         * Set in menu 4X4 
         */
	private AbstractAction fourTimesFour = new AbstractAction("4X4")
	{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            érték = 0;
            nrOfSteps.setText("Lépések száma: 0");
            //setEnabled(false);
            game = new Game(4);
            drawTable(game, 4);
                        
            sixTimesSix.setEnabled(false);
            fourTimesFour.setEnabled(false);
            twiceADouble.setEnabled(false);
        }
    };
        /**
         * Set in menu 6X6 
         */
	private AbstractAction sixTimesSix = new AbstractAction("6X6")
	{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            érték = 0;
            nrOfSteps.setText("Lépések száma: 0");
            //setEnabled(false);
            game = new Game(6);
            drawTable(game, 6);
            
            sixTimesSix.setEnabled(false);
            fourTimesFour.setEnabled(false);
            twiceADouble.setEnabled(false);
        }
    };
        /**
         * Set about dialog box, with description
         */
	private AbstractAction about = new AbstractAction("Névjegy")
	{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Név: Burian Sándor\nNeptun: AWXYHE\n\nTárgy: Programozási technológia 1\n2018 ELTE");
        }
    };
        
    /**
     * Draw table to the panel, hide the welcome text.
     * Set game table size.
     * 
     * @param game a Game value
     * @param size of the game, an int
     */
    private void drawTable(Game game, int size){
        welcomeMsg.setVisible(false);
        //getContentPane().remove(gamePanel);
        JPanel gamePanel;
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size+2, size+2));
        
        gamePanel = showGame(gamePanel, game);
        
        
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        

        // TEST
        //game.moveByRowLeft(2);
        //game.moveByRowRight(2);
        //game.moveByColumnUp(1);
        //game.moveByColumnDown(1);
    }    

    /**
     * Modifies the values of given gamePanel, by adding the buttons to the specified places.
     * 
     * @param gamePanel Jpanel, the panel of what want to set buttons
     * @param game Game value, to get the database behind the panel
     * @return gamePanel, a JPanel value
     */
    private JPanel showGame(JPanel gamePanel, Game game) {
        Vector tmpRemainIdx = new Vector();        
        
        //for(int idx=0; idx<game.getGameTable().size(); ++idx){
        int idx=0;
        while(idx<game.getGameTable().size()){
            if(idx == game.getSize()){                                     //first row -> the game buttons
                addNavButton(gamePanel, "left", idx-1, game);
                for(int tIdx=0; tIdx<game.getSize(); ++tIdx )    
                    addButton(gamePanel, game.gameTableIdxToColor(tIdx));
                addNavButton(gamePanel, "right", idx-1, game);
            }
            
            if(idx == (game.getSize()*(game.getSize()-1))){                 //last row -> the game buttons
                addNavButton(gamePanel, "left", idx, game);
                for(int tIdx=game.getGameTable().size()-game.getSize(); tIdx<game.getGameTable().size(); ++tIdx )    
                    addButton(gamePanel, game.gameTableIdxToColor(tIdx));
                addNavButton(gamePanel, "right", idx, game);
            }
            //if(addIfIsRemainingElement(tmpRemainIdx,idx)){
            /*if(((idx<=(game.getSize()*2)) && (idx>game.getSize())) || (idx>game.getGameTable().size())){
                addIfIsRemainingElement(tmpRemainIdx,idx);
            }else */if(game.isEdge(idx).equals("up")){                          //first row -> nav buttons
                addNavButton(gamePanel, "up", idx, game);
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
            }else if(game.isEdge(idx).equals("down")){                        //last row -> nav buttons
                addNavButton(gamePanel, "down", idx, game);
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
            }else if(game.isEdge(idx).equals("left")){
                addNavButton(gamePanel, "left", idx, game);
                addButton(gamePanel, game.gameTableIdxToColor(idx));
            }else if(game.isEdge(idx).equals("right")){
                addButton(gamePanel, game.gameTableIdxToColor(idx));
                addNavButton(gamePanel, "right", idx, game);
            }else if(game.isEdge(idx).equals("leftup")){
                addNavButton(gamePanel, "leftup", idx, game);
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
            }else if(game.isEdge(idx).equals("rightup")){
                addNavButton(gamePanel, "rightup", idx, game);
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
            }else if(game.isEdge(idx).equals("leftdown")){
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
                addNavButton(gamePanel, "leftdown", idx, game);
            }else if(game.isEdge(idx).equals("rightdown")){
                //addButton(gamePanel, game.gameTableIdxToColor(idx));
                addNavButton(gamePanel, "rightdown", idx, game);
            }else{
                addButton(gamePanel, game.gameTableIdxToColor(idx));
                //addButton(gamePanel, Integer.toString((int) game.getGameTable().get(idx) ), game);
            }
            ++idx;
        }
        return gamePanel;
    }

    /**
     * Add a button to gamePanel, with a specific color.
     * 
     * @param gamePanel JPanel
     * @param gameTableIdxToColor String
     */
    private void addButton(JPanel gamePanel, String gameTableIdxToColor) {
        JButton button = new JButton();
        switch (gameTableIdxToColor){
            case "red":
                    button.setBackground(Color.red);
                    break;
            case "green":
                    button.setBackground(Color.green);
                    break;
            case "yellow":
                    button.setBackground(Color.yellow);
                    break;
            case "orange":
                    button.setBackground(Color.orange);
                    break;
            case "blue":
                    button.setBackground(Color.blue);
                    break;
            case "white":
                    button.setBackground(Color.white);
                    break;
        }
        gamePanel.add(button);
    }
    
    /**
     * Add a navigation button to panel. Navigation button looks like this: ▲▼◄►
     * Each navigation button have an action listener, ehat will change the game table as the button value if the button is clicked.
     * 
     * @param gamePanel a JPanel value. The button will be added to this panel.
     * @param dir a String value. This could be: up, down, left, right, leftup, rightup, leftdown, rightdown
     * @param idx index of this button in gameTable
     * @see gameTable
     * @param game the Game what will make the movements of the nav button
     */
    private void addNavButton(JPanel gamePanel, String dir, int idx, Game game) {
        //JButton button = null;
        JButton button = new JButton();
        switch (dir){
            case "up":
                    button = new JButton("▲");
                    //button.setBackground(Color.black);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);
                            game.moveByColumnUp(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "down":
                    //button.setBackground(Color.green);
                    button = new JButton("▼");
                    //button.setBackground(Color.black);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);
                            game.moveByColumnDown(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "left":
                    //button.setBackground(Color.yellow);
                    button = new JButton("◄");
                    //button.setBackground(Color.black);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);
                            game.moveByRowLeft(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "right":
                    //button.setBackground(Color.orange);
                    button = new JButton("►");
                    //button.setBackground(Color.black);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);
                            game.moveByRowRight(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "leftup":
                    //button.setBackground(Color.blue);
                    //button = new JButton();
                    button.setBackground(Color.gray);
                    gamePanel.add(button);
                    button = new JButton("▲");
                    
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);                            
                            game.moveByColumnUp(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "rightup":
                    //button.setBackground(Color.white);
                    button = new JButton("▲");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);                            
                            game.moveByColumnUp(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    gamePanel.add(button);
                    
                    button = new JButton();
                    button.setBackground(Color.gray);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    break;
            case "leftdown":
                    //button.setBackground(Color.white);
                    button = new JButton();
                    button.setBackground(Color.gray);
                    gamePanel.add(button);
                    button = new JButton("▼");
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);                            
                            game.moveByColumnDown(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    break;
            case "rightdown":
                    //button.setBackground(Color.white);
                    button = new JButton("▼");
                    gamePanel.add(button);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.err.println(dir+" clicked, with " + idx);                            
                            game.moveByColumnDown(idx);
                            //stopIfIsCompleted();
                            //refreshGUI();
                        }
                    });
                    
                    button = new JButton();
                    button.setBackground(Color.gray);
                    System.out.println("LOG\t addNavButton @"+idx +" @ "+ dir);
                    break;
        }
        gamePanel.add(button);
    }
}
