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

    public static boolean gameOver, started;

    private Renderer renderer;

    private Background background;

    private static Bird bird;

    private static ArrayList<Column> columns;

    private static int score;


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
        started = false;
        gameOver = false;
        score = 0;

        jframe.add(renderer);
        jframe.setTitle("TrashyBird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(true);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);

        jframe.setVisible(true);

        timer.start();
    }

    public static void addColumn(boolean start){
        int SPACE = 200;
        int columnWidth = Column.getWIDTH();

        if(start){
            columns.add(new Column(WIDTH+columnWidth+(columns.size()*SPACE)));
        }else{
            columns.add(new Column(columnWidth+(columns.get(columns.size()-1).getX()+SPACE)));
            columns.remove(0);
        }
    }

    static int getHEIGHT() {
        return HEIGHT;
    }

    static int getSpeed() {
        return speed;
    }

    static int getWIDTH() {
        return WIDTH;
    }

    static double getGRAVITY() {
        return GRAVITY;
    }

    boolean isGameOver() {
        return gameOver;
    }

    boolean isStarted() {
        return started;
    }

    public static int getScore() {
        return score;
    }

    public Boolean checkCollision(Column column){
        if(column == null){ return false;}
        if(bird.getY_location()<=column.getGAP_START()||bird.getY_location()+bird.getSIZE()>=column.getGAP_START()+column.getGAP_SIZE()){
            return true;
        }
        return false;
    }

    private Column findColumn(int minX, int maxX){
        for(Column column : columns){
            //checks if a column has been passed
            if(column.getX()<minX&&!column.isCheck()) {
                passedColumn(column);
                score++;
            }
            //checks if the column is in the range of the bird
            if(column.getX()>=minX&&column.getX()<=maxX&&!column.isCheck()){
                return column;
            }
        }
        return null;
    }

    private void passedColumn(Column column){
        column.setCheck(true);
    }

    public static void collision(){
        bird.dead();
        gameOver=true;
    }

    public static void restart(){
        bird = new Bird(WIDTH, HEIGHT);
        columns = new ArrayList<Column>();
        started = false;
        gameOver = false;
        score = 0;

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        started = true;
        gameOver = false;
        bird.jump();
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

        if(started) {
            if (!gameOver) {
                if (ticks % 2 == 0) {
                    bird.incrementPosition();
                    for (Column column : columns) {
                        column.incrementPosition();
                    }
                    if (columns.get(0).getX() + columns.get(0).getWIDTH() <= 0) {
                        addColumn(false);
                    }
                }
                renderer.repaint();
                if (checkCollision(findColumn(bird.getX_location() - Column.getWIDTH(), bird.getX_location() + bird.getSIZE()))) {
                    collision();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(started){
                bird.jump();
            }else{
                restart();
            }
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
