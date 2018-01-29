import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Column {

    private static int WIDTH = 60;
    private int GAP = 100;
    private int MIN_FROM_EDGE = 30;
    private int X;

    private Color COLOR;

    private Rectangle top;
    private Rectangle bottom;

    /**
     *
     * @param X - the X coordinate of the column to be added
     */
    public Column(int X) {
        this.X = X;
        this.COLOR = Color.GREEN.darker();

        Random rand = new Random();
        int randomHeight = MIN_FROM_EDGE+rand.nextInt((TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-(2*MIN_FROM_EDGE)-GAP));

        this.top = new Rectangle(X, 0, this.WIDTH, randomHeight);
        this.bottom = new Rectangle(X, randomHeight+GAP, this.WIDTH, TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-(randomHeight+GAP));
    }

    public static int getWidth() {
        return WIDTH;
    }
    public int getX() {
        return X;
    }

    public void incrementPosition(){
        X -= TrashyBird.getSpeed();
        top.setLocation(X, top.y);
        bottom.setLocation(X, bottom.y);
    }

    public void paintColumn(Graphics g){
        g.setColor(COLOR);

        g.fillRect(top.x, top.y, top.width, top.height);

        g.fillRect(bottom.x, bottom.y, bottom.width, bottom.height);
    }
}