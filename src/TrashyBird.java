import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JFrame;
import javax.swing.Timer;


public class TrashyBird implements ActionListener, KeyListener, MouseListener{

    public static TrashyBird trashyBird;

    private static final int WIDTH = 400, HEIGHT = 400;

    private int ticks, speed;

    public boolean gameOver, started;

    private Renderer renderer;

    private Background background;

    private Bird bird;

    private Random rand;

    private ArrayList<Column> columns;


    public TrashyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        ticks = 0;
        speed = 10;
        renderer = new Renderer();
        background = new Background(WIDTH, HEIGHT);
        bird = new Bird(WIDTH, HEIGHT);
        rand = new Random();
        columns = new ArrayList<Column>();

        jframe.add(renderer);
        jframe.setTitle("TrashyBird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(true);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setVisible(true);

        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start){
        int SPACE = 300;
        int columnWidth = Column.getWidth();

        if(start){
            columns.add(new Column(WIDTH+columnWidth+(columns.size()*SPACE), HEIGHT));
        }else{
            columns.add(new Column(WIDTH+columnWidth+(columns.get(columns.size()-1).getX()+(SPACE*2)), HEIGHT));
        }
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    //Used to repaint the whole scene
    public void repaint(Graphics g){
        background.paintBackground(g);
        bird.paintBird(g);

        for(Column column: columns){
            column.paintColumn(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;


        if(ticks%2==0){
            bird.incrementPosition();
        }
        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            bird.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        bird.jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args){
        trashyBird = new TrashyBird();
    }
}
