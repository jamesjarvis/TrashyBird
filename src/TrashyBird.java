import java.awt.event.*;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class TrashyBird implements ActionListener, KeyListener, MouseListener{

    static TrashyBird trashyBird;

    private static final int WIDTH = 600, HEIGHT = 600;

    private int ticks;
    private static int speed;
    private static double GRAVITY;

    private static boolean gameOver, started;

    private Renderer renderer;

    private Background background;

    private static Bird bird;

    private static Menu menu;

    private static ArrayList<Column> columns;

    private static int score;


    private TrashyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        ticks = 0;
        speed = 4;
        GRAVITY = 2;
        renderer = new Renderer();
        background = new Background(WIDTH, HEIGHT);
        bird = new Bird(WIDTH, HEIGHT);
        menu = new Menu();
        columns = new ArrayList<>();
        started = false;
        gameOver = false;
        score = 0;

        jframe.add(renderer);
        jframe.setTitle("TrashyBird");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);

        jframe.setVisible(true);

        timer.start();
    }

    private static void addColumn(boolean start){
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

    static boolean isGameOver() {
        return gameOver;
    }

    static boolean isStarted() {
        return started;
    }

    static int getScore() {
        return score;
    }

    private Boolean checkCollision(Column column) {
        return column != null && (bird.getY_location() <= column.getGAP_START() || bird.getY_location() + bird.getSIZE() >= column.getGAP_START() + column.getGAP_SIZE());
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
        column.setCheck();
    }

    static void collision(){
        bird.dead();
        gameOver=true;
        started = false;
    }

    private static void restart(){
        bird = new Bird(WIDTH, HEIGHT);
        columns = new ArrayList<>();
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
    void repaint(Graphics g){
        background.paintBackground(g);
        bird.paintBird(g);
        menu.paintMenu(g);

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
                    if (columns.get(0).getX() + Column.getWIDTH() <= 0) {
                        addColumn(false);
                    }
                }
                renderer.repaint();
                if (checkCollision(findColumn(bird.getX_location() - Column.getWIDTH(), bird.getX_location() + bird.getSIZE()))) {
                    collision();
                    try {
                        renderer.repaint();
                        Thread.sleep(0);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
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
        if(started){
            bird.jump();
        }else{
            restart();
        }
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
