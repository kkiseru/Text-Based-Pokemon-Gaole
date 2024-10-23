import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
class Window1 extends JFrame implements KeyListener {
    private boolean buttonPressed = false;
    private boolean buttonAlreadyPressed = false;
    public static int enemyWidth;
    public static int allyWidth;

    public void SpamButton2(){
        boolean condition = true;
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                enemyWidth = enemyWidth+5;
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(tasknew, 0, 500);

        JPanel mainPanel = new JPanel();  
        setAlwaysOnTop(true);      
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        getContentPane().add(mainPanel);
        mainPanel.setBackground(Color.black);
        setSize(400, 400);
        setVisible(true);
        addKeyListener(this);

        LinePanel linePanel = new LinePanel();
        JLabel label = new JLabel();
        label.setText("Spam the spacebar! First to the end gets to attack!");
        label.setForeground(Color.white);
        label.setBounds(50, 0, 400, 30);
        
        mainPanel.add(label, BorderLayout.NORTH);        
        mainPanel.add(linePanel, BorderLayout.CENTER);
        do{
            if(buttonPressed){
                allyWidth = allyWidth+5;
                buttonPressed = false;
            }
            else if(allyWidth > 300 || enemyWidth > 300){
                condition = false;
                timer.cancel();
                if(allyWidth > 300){
                    label.setText("Player attacks.");
                }
                else if(enemyWidth > 300){
                    label.setText("Enemy attacks.");
                }
            }
            mainPanel.revalidate();
            mainPanel.repaint(); 
        }while(condition);
        try {
            Thread.sleep(3000);
            dispose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

class LinePanel extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Get the panel dimensions
        setBackground(Color.BLACK);
        // Draw lines in the middle
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(5)); // Set the line thickness
        g2d.drawLine(100, 150, Window1.enemyWidth, 150);

        g2d.setColor(new Color(0,143,255));
        g2d.setStroke(new BasicStroke(5)); // Set a different line thickness
        g2d.drawLine(100, 180, Window1.allyWidth, 180);
    }    
}