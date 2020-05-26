package gamebreakout;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

     private boolean play = false;
     private int score =0;
     private Timer timer;
     private int totalBricks;
     private int delay = 8;
     private int playerX = 310;
     private int ballPosX = 120;
     private int ballPosY = 350;
     private int ballXDir = -1;
     private int ballYDir = -2;
     
     
     private MapGenerator map ;
     public Gameplay(){
         map = new MapGenerator(3,7);
         addKeyListener(this);
         setFocusable(true);
         setFocusTraversalKeysEnabled(false);
         timer = new Timer(delay, this);
         timer.start();
     }
     
     public void paint(Graphics g){
         //background
         g.setColor(Color.black);
         g.fillRect(1, 1, 692, 592);
         
         
         // draw map
         
         map.draw((Graphics2D)g);
         //borders
         g.setColor(Color.yellow);
         g.fillRect(0, 0, 3, 592);
         g.fillRect(0, 0, 692, 3);
         g.fillRect(691, 0, 3, 592);
         
         //scores
         g.setColor(Color.white);
         g.setFont(new Font("serif" , Font.BOLD, 25));
         g.drawString(""+score, 590, 30);
         
         //paddles
         if(score <105){
         g.setColor(Color.green);
         g.fillRect(playerX, 550, 100, 8);}
         else if(score>105 & score<350){
         g.setColor(Color.green);
         g.fillRect(playerX, 550, 50, 8);
         }
         else if(score >350){
             g.setColor(Color.green);
            g.fillRect(playerX, 550, 40, 8);
             g.setColor(Color.white);
             g.fillOval(ballPosX, ballPosY, 10, 10);
         }
         
         //ball
         g.setColor(Color.yellow);
         g.fillOval(ballPosX, ballPosY, 20, 20);
         
         
         if(score == 105){
             play = false;
             ballXDir = 0;
             ballYDir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("You won my friend, you are so good at this!!! ", 160, 300);
             g.setColor(Color.green);
         g.fillRect(playerX, 550, 50, 8);
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("Press 2 to stage 2!!!  ", 230, 350);
         }
         if(score == 350){
             play = false;
             ballXDir = 0;
             ballYDir = 0;
             g.setColor(Color.WHITE);
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("You won my friend, you are so good at this!!! ", 160, 300);
             
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("Press 3 to stage 3!!!  ", 230, 350);
         }
         if(ballPosY > 570){
             play = false;
             ballXDir = 0;
             ballYDir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("Game over , Scores : " + score, 190, 300);
             
             g.setFont(new Font("serif", Font.BOLD, 25));
             g.drawString("Press 1 to restart!!!  ", 230, 350);
         }
         g.dispose();
     }
     
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(ke.getKeyCode()== KeyEvent.VK_LEFT){
            if(playerX <10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        if(ke.getKeyCode() == KeyEvent.VK_2){
            if(!play){
                play = true;
                ballPosX = 120;
                delay = 8;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score += score;
                totalBricks = 28;
                map = new MapGenerator(4,7);
                repaint();
            }
        }
        if(ke.getKeyCode() == KeyEvent.VK_1){
            if(!play){
                play = true;
                ballPosX = 120;
                delay = 8;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }
        if(ke.getKeyCode() == KeyEvent.VK_3){
            if(!play){
                play = true;
                ballPosX = 120;
                delay = 8;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 35;
                map = new MapGenerator(5,8);
                repaint();
            }
        }
    }
    
    public void moveRight(){
        play = true;
        playerX +=20;
    }
    public void moveLeft(){
    
        play = true;
        playerX -=20;
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    @Override
    public void actionPerformed(ActionEvent ae) {
    timer.start();
    
    if(play){
        if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
            ballYDir = -ballYDir;
        }
        
        A: for(int i=0; i<map.map.length;i++){
            for(int j=0; j<map.map[0].length;j++){
                if(map.map[i][j] > 0){
                    int brickX = j*map.brickWidth +80;
                    int brickY = i*map.brickHeight + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    
                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                    Rectangle brickRect = rect;
                    
                    if(ballRect.intersects(brickRect)){
                        map.setBrickValue(0, i, j);
                        totalBricks--;
                        score +=5;
                        if(ballPosX+19<= brickRect.x|| ballPosX +1 >= brickRect.x +brickRect.width  ){
                            ballXDir = -ballXDir;
                        }
                        else{
                            ballYDir = -ballYDir;
                        }
                        break A;
                    }
                }
            }
        }
        
        ballPosX += ballXDir;
        ballPosY += ballYDir;
        if(ballPosX <0){
            ballXDir = -ballXDir;
        }
        if(ballPosY<0){
            ballYDir = -ballYDir;
        }
        if(ballPosX > 670){
            ballXDir = -ballXDir;
        }
      }
    repaint();
    }

    
    
    
}
