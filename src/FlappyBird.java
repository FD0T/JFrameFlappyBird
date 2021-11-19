import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyBird {

    int vy = 0, vy_const = 75, ay_const = 30, score = 0;
    Timer timer;
    JButton jump;

    public static void main(String[] args) {
        new FlappyBird();
    }

    public FlappyBird(){flappyBirdLoop();}

    public void flappyBirdLoop(){
        JFrame flappyBirdWindow = new JFrame("JFrame Flappy Bird ");
        flappyBirdWindow.setSize(480, 848);
        flappyBirdWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flappyBirdWindow.setLayout(null);
        flappyBirdWindow.setVisible(true);

        JLabel scoreOut = new JLabel();
        scoreOut.setFont(new Font("Comic Sans", Font.PLAIN, 64));
        scoreOut.setText(String.valueOf(score));
        scoreOut.setBounds(230,100,200,200);
        flappyBirdWindow.add(scoreOut);

        JPanel bird = new JPanel();
        bird.setBounds(100,400,10,10);
        bird.setBackground(Color.black);

        flappyBirdWindow.add(bird);

        jump = new JButton("Jump");
        jump.setBounds(190, 750, 100,50);
        jump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vy = 0;
                vy -= vy_const;
                bird.setBounds(50,bird.getY()+vy, 10, 10);
            }
        });
        flappyBirdWindow.add(jump);

        int rand = -400 + (int)(Math.random()*400);
        JPanel tubeU = new JPanel();
        tubeU.setBackground(Color.green);
        tubeU.setBounds(480, rand,50,500);
        flappyBirdWindow.add(tubeU);

        JPanel tubeD = new JPanel();
        tubeD.setBackground(Color.green);
        tubeD.setBounds(480, tubeU.getY() + 500 + 140,50,500);
        flappyBirdWindow.add(tubeD);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vy = ay_const;
                bird.setBounds(50,bird.getY()+vy, 10, 10);
                if(bird.getY() >= 838 || bird.getY() <= 0){
                    deathScreen();
                }
                if((bird.getY() <= tubeU.getY() + tubeU.getHeight() || bird.getY() + bird.getHeight() >= tubeD.getY()) &&
                bird.getX() + bird.getWidth() >= tubeU.getX() && bird.getX() <= tubeU.getX() + tubeU.getWidth()){
                    deathScreen();
                }

                if(tubeU.getX() <= -50 && tubeD.getX() <= -50){
                    int rand = -400 + (int)(Math.random()*400);
                    score++;
                    scoreOut.setText(String.valueOf(score));
                    tubeU.setBounds(480,rand,50,500);
                    tubeD.setBounds(480, tubeU.getY() + 500 + 140, 50, 500);
                }
                else{
                    tubeU.setBounds(tubeU.getX()-20, tubeU.getY(), 50,500);
                    tubeD.setBounds(tubeD.getX()-20, tubeD.getY(), 50,500);
                }
            }
        });
        timer.start();
    }

    public void deathScreen()
    {
        timer.stop();
        jump.setEnabled(false);
    }
}
