import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JFrame;
import javax.swing.Timer;


public class TrashyBird implements ActionListener{

    static TrashyBird trashyBird;

    private final int WIDTH = 400, HEIGHT = 400;

    public boolean gameOver, started;

    Renderer renderer;

    Background background;

    Bird bird;

    Random rand;





    public TrashyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new Renderer();
        background = new Background(WIDTH, HEIGHT);
        bird = new Bird(WIDTH, HEIGHT);
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("TrashyBird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setVisible(true);

    }

    //Used to repaint the whole scene
    public void repaint(Graphics g){
        System.out.println("hello");
        background.paintBackground(g);
        bird.paintBird(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        trashyBird = new TrashyBird();
    }

}
