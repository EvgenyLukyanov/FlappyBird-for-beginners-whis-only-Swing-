
package flappybird;

/**
 *
 * @author evgenylukyanov
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird extends JPanel implements ActionListener {


    private JFrame frame;
    private Timer timer;
    private Image background;
    private int backgroundX;
    private static boolean inGame = true;
    private Image pipe;
    private Image pipe2;
    private Image pipe3;
    private Image bird;
    private int birdX = 200;
    private static int birdY=350;
    private int pipeX = 600;
    private int pipeX2 = 1000;
    private int pipeX3 = 1400;
    private int pipeY = ThreadLocalRandom.current().nextInt(300, 700);
    private int pipeY2 = ThreadLocalRandom.current().nextInt(300, 700);
    private int pipeY3 = ThreadLocalRandom.current().nextInt(300, 700);
    private final int pipeWidth = 150;
    private final int pipeHeight = 800;
    
    

    public FlappyBird() {
        frame = new JFrame("Flappy Bird");
        frame.setSize(800, 825);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(this);
        frame.addKeyListener(new FieldKeyListener());
        frame.setVisible(true);

        background = Toolkit.getDefaultToolkit().getImage("/Users/evgenylukyanov/NetBeansProjects/FlappyBird/Images/backgroundFB.png");
        backgroundX = 0;
        pipe = Toolkit.getDefaultToolkit().getImage("/Users/evgenylukyanov/NetBeansProjects/FlappyBird/Images/pipe.png");
        pipe2 = Toolkit.getDefaultToolkit().getImage("/Users/evgenylukyanov/NetBeansProjects/FlappyBird/Images/pipe.png");
        pipe3 = Toolkit.getDefaultToolkit().getImage("/Users/evgenylukyanov/NetBeansProjects/FlappyBird/Images/pipe.png");
        bird = Toolkit.getDefaultToolkit().getImage("/Users/evgenylukyanov/NetBeansProjects/FlappyBird/Images/bird.png");
        
        
        timer = new Timer(6, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
if(inGame){
        g.drawImage(background, backgroundX, 0, null);
        g.drawImage(background, backgroundX + 790, 0, null);
        
        g.drawImage(pipe,pipeX, pipeY, null);
        g.drawImage(pipe, pipeX, pipeY-250-pipeHeight, null);
        
        g.drawImage(pipe2, pipeX2, pipeY2, null);
        g.drawImage(pipe2, pipeX2, pipeY2-250-pipeHeight, null);
        
        g.drawImage(pipe3,pipeX3, pipeY3, null);
        g.drawImage(pipe3, pipeX3, pipeY3-250-pipeHeight, null);
        
        g.drawImage(bird, birdX, birdY, null);}
else {
            String str = "GAME OVER";
            g.setColor(Color.RED);
            Font f = new Font("Arial", Font.BOLD, 36);
            g.setFont(f);
            g.drawString(str, 300, 400);
            
        }

    }

    public static void main(String[] args) {
       new FlappyBird();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        backgroundX--;
        pipeX--;
        pipeX2--;
        pipeX3--;
        birdY++;
        
        if(((birdY+75>pipeY||birdY+75<pipeY-250)&&birdX+75>pipeX&&birdX+75<pipeX+pipeWidth)||birdY+75>800||birdY+75<0){inGame=false;}
        if((birdY+75>pipeY2||birdY+75<pipeY2-250)&&birdX+75>pipeX2&&birdX+75<pipeX2+pipeWidth){inGame=false;}
        if((birdY+75>pipeY3||birdY+75<pipeY3-250)&&birdX+75>pipeX3&&birdX+75<pipeX3+pipeWidth){inGame=false;}
        
        if(pipeX==0-pipeWidth){
            pipeY = ThreadLocalRandom.current().nextInt(300, 700);
            pipeX=1000;
        }  
        if(pipeX2==0-pipeWidth){
            pipeY2 = ThreadLocalRandom.current().nextInt(300, 700);
            pipeX2=1000;
        } 
        if(pipeX3==0-pipeWidth){
            pipeY3 = ThreadLocalRandom.current().nextInt(300, 700);
            pipeX3=1000;
        } 


        if (backgroundX == -790) {
            backgroundX = 0;
        }

        repaint();
    }
    
    class FieldKeyListener extends KeyAdapter{

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_ENTER){
           birdY-=75;
           repaint();
        }
        if(key==KeyEvent.VK_SPACE){
           birdY+=75;
           repaint();
        }
    }
    
}
}



