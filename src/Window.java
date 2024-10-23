import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame implements KeyListener{
    private JFrame window = new JFrame();
    private JPanel panel = new JPanel();
    private boolean buttonPressed = false;
    private boolean buttonAlreadyPressed = false;
    private int secondsPassed;
    private int number;

    public void CreateWindow(){

        window = new JFrame();

        window.setAlwaysOnTop(true);
        window.setTitle("Pokemon GaOle");
        window.setLayout(null);
        window.setSize(400,400);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
    }

    public void addTimer(){
        secondsPassed++;
    }

    public void SpamButton1(){
        boolean condition = true;
        number = 0;
        secondsPassed = 0;
        
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                addTimer();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(tasknew, 0, 1000);
        CreateWindow();
        panel = new JPanel();

        panel.setBackground(Color.black);
        panel.setBounds(0, 55, 400, 250);
        panel.setLayout(null);        
        window.add(panel);
        window.addKeyListener(this);

        JLabel label = new JLabel();
        label.setText("Spam the spacebar!");
        label.setForeground(Color.white);
        label.setBounds(140, 0, 400, 30);

        JLabel label1 = new JLabel(String.valueOf(number), JLabel.CENTER);
        label1.setFont(new Font("Verdana", Font.BOLD, 70));
        label1.setForeground(Color.white);
        label1.setBounds(95, 70, 200, 100);
        
        panel.add(label);
        panel.add(label1);
        
        do{
            if(buttonPressed && secondsPassed <= 10){
                label1.setText(String.valueOf(number = number+5));
                buttonPressed = false;
            }
            else if(secondsPassed > 10){
                condition = false;
            }
            panel.revalidate();
            panel.repaint(); 
        }while(condition);
        window.dispose();
    }

    public int getScore(){
        return number;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !buttonAlreadyPressed){
            buttonPressed = true;
            buttonAlreadyPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            buttonAlreadyPressed = false;
        }
    }  
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
