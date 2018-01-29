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

    private int ticks;
    private static int speed;
    private static double GRAVITY;

    public boolean gameOver, started;

    private Renderer renderer;

    private Background background;

    private Bird bird;

    private ArrayList<Column> columns;


    public TrashyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        ticks = 0;
        speed = 4;
        GRAVITY = 2;
        renderer = new Renderer();
        background = new Background(WIDTH, HEIGHT);
        bird = new Bird(WIDTH, HEIGHT);
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
        addColumn(false);
        addColumn(false);
        addColumn(false);

        timer.start();
    }

    public void addColumn(boolean start){
        int SPACE = 200;
        int columnWidth = Column.getWidth();

        if(start){
            columns.add(new Column(WIDTH+columnWidth+(columns.size()*SPACE), HEIGHT));
        }else{
            columns.add(new Column(columnWidth+(columns.get(columns.size()-1).getX()+SPACE), HEIGHT));
        }
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getSpeed() {
        return speed;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static double getGRAVITY() {
        return GRAVITY;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isStarted() {
        return started;
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
            for(Column column: columns){
                column.incrementPosition();
            }
            if(columns.get(0).getX()<=0){
                addColumn(false);
            }
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
