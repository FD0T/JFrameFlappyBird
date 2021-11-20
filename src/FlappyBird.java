import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyBird extends JFrame {

    int vy = 0, vy_const = 75, ay_const = 30, score = 0;
    Timer timer;

    public static void main(String[] args) {
        new FlappyBird();
    }

    public FlappyBird(){flappyBirdLoop();}

    public void flappyBirdLoop(){
        JFrame flappyBirdWindow = new JFrame("JFrame Flappy Bird ");
        flappyBirdWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flappyBirdWindow.setSize(480, 848);
        flappyBirdWindow.setLayout(null);
        flappyBirdWindow.setVisible(true);


        JPanel startMenu = new JPanel();
        startMenu.setBackground(Color.blue);
        startMenu.setBounds(0,0,480,848);
        startMenu.setLayout(new GridLayout(1,1));
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    timer.start();
                    startButton.setVisible(false);
                    startMenu.setVisible(false);
            }
        });
        startMenu.add(startButton);
        flappyBirdWindow.add(startMenu);


        JPanel deathMenu = new JPanel();
        deathMenu.setBackground(Color.blue);
        deathMenu.setBounds(0,0,480,848);
        deathMenu.setLayout(new GridLayout(2,1));
        deathMenu.setVisible(false);
        JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
                deathMenu.setVisible(false);
            }
        });
        JLabel scoreMenu = new JLabel();
        scoreMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        scoreMenu.setText("Your score is " + score);
        deathMenu.add(scoreMenu);
        deathMenu.add(restart);
        flappyBirdWindow.add(deathMenu);

        JLabel scoreOut = new JLabel();
        scoreOut.setFont(new Font("Comic Sans", Font.PLAIN, 64));
        scoreOut.setText(String.valueOf(score));
        scoreOut.setBounds(230,100,200,200);
        flappyBirdWindow.add(scoreOut);

        JPanel bird = new JPanel();
        bird.setBounds(100,150,10,10);
        bird.setBackground(Color.black);

        flappyBirdWindow.add(bird);

        JButton jump = new JButton("Jump");
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
                jump.setEnabled(true);
                vy = ay_const;
                bird.setBounds(50,bird.getY()+vy, 10, 10);
                if(bird.getY() >= 838 || bird.getY() <= 0){
                    timer.stop();
                    scoreMenu.setText("Your score is " + score);
                    deathMenu.setVisible(true);
                    score = 0;
                    scoreOut.setText(String.valueOf(score));
                    bird.setBounds(100,150,10,10);
                    tubeU.setBounds(480, rand,50,500);
                    tubeD.setBounds(480, tubeU.getY() + 500 + 140,50,500);
                }
                if((bird.getY() <= tubeU.getY() + tubeU.getHeight() || bird.getY() + bird.getHeight() >= tubeD.getY()) &&
                bird.getX() + bird.getWidth() >= tubeU.getX() && bird.getX() <= tubeU.getX() + tubeU.getWidth()){
                    timer.stop();
                    scoreMenu.setText("Your score is " + score);
                    deathMenu.setVisible(true);
                    score = 0;
                    scoreOut.setText(String.valueOf(score));
                    bird.setBounds(100,150,10,10);
                    tubeU.setBounds(480, rand,50,500);
                    tubeD.setBounds(480, tubeU.getY() + 500 + 140,50,500);
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
    }
}
