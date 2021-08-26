import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{
    MainPanel main;
    GridPanel boardFrame;
    OptionsPanel optionsFrame;
    Options options;
    Board board;
    public GameFrame(){
        // default options
        options = new Options(1,false);
        main = new MainPanel();
        optionsFrame = new OptionsPanel();
        add(main);
        setSize(640,665);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main.startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(main);
                boardFrame = new GridPanel(new Board(options));
                add(boardFrame);
                repaint();
            }
        });
        main.optionButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(main);
                add(optionsFrame);
                repaint();
            }
        });

        BGMPlayer player = new BGMPlayer();
        player.play("Music/BGM.wav");
        boolean running = true;
        while (running){
            if (player.playCompleted){
                player.playCompleted = false;
                player.play("Music/BGM.wav");
            }
        }
    }
    public class MainPanel extends JPanel {
        JButton startButton;
        JButton optionButton;
        public MainPanel(){
            ImageImplement panel = new ImageImplement(new ImageIcon("assets/titleScreen.png").getImage());
            
            startButton = new JButton();
            optionButton = new JButton();
    
            startButton.setBounds(443,355,130,50);
            optionButton.setBounds(437,420,150,50);
            
            startButton.setOpaque(false);
            startButton.setContentAreaFilled(false);
            startButton.setBorderPainted(false);
    
            optionButton.setOpaque(false);
            optionButton.setContentAreaFilled(false);
            optionButton.setBorderPainted(false);
      
            add(startButton);
            add(optionButton);
    
            add(panel);
    
            setSize(640,665);
            setLayout(null);
            setVisible(true);
    
        }
    }
    public class GridPanel extends JPanel{
        private ImageImplement panel;
        GridPanel(Board board){
            panel = new ImageImplement(new ImageIcon("assets/board.png").getImage());
            add(panel);
            board.createLabels(panel);
            setSize(640,660);
            setLayout(null);
            setVisible(true);
        }
    }
    public class OptionsPanel extends JPanel{
        private ImageImplement panel;
        public OptionsPanel(){
       
            panel = new ImageImplement(new ImageIcon("assets/optionScreen.jpeg").getImage());
    
            JLabel diff_label = new JLabel("Difficulty : 1");
            JLabel high_label = new JLabel("Highlighting : OFF");
            JLabel back_label = new JLabel("Back");
    
            diff_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
            high_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
            back_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
    
            diff_label.setBounds(150,200,300,50);
            high_label.setBounds(150,250,300,50);
            back_label.setBounds(150,400,300,50);
    
            diff_label.addMouseListener(new MouseInputAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    options.setDifficulty((options.getDifficulty()+1)%4);
                    diff_label.setText("Difficulty : "+options.getDifficulty());
                }
            });
            high_label.addMouseListener(new MouseInputAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    options.setHighlighting(!options.getHighlighting());
                    String value = options.getHighlighting() ? "ON" : "OFF";
                    high_label.setText("Highlighting : "+value);
                }
            });
            back_label.addMouseListener(new MouseInputAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    
                }
            });
    
            add(diff_label);
            add(high_label);
            add(back_label);
    
            add(panel);
            setSize(640,660);
            setLayout(null);
            setVisible(true);
    
    
        }
        
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
