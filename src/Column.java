import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Column {

    private static int WIDTH = 60;
    private int GAP = 50;
    private int MIN_FROM_EDGE = 50;
    private int FLOOR_HEIGHT = 100;
    private int X;

    private Color color;

    private Rectangle top;
    private Rectangle bottom;

    /**
     *
     * @param X - the X coordinate of the column to be added
     * @param HEIGHT
     */
    public Column(int X, int HEIGHT) {
        this.X = X;
        this.color = Color.GREEN.darker();

        Random rand = new Random();
        int randomHeight = rand.nextInt((HEIGHT-FLOOR_HEIGHT)-(2*MIN_FROM_EDGE));

        this.top = new Rectangle(X, 0, this.WIDTH, MIN_FROM_EDGE+randomHeight-GAP);
        this.bottom = new Rectangle(X, MIN_FROM_EDGE+randomHeight, this.WIDTH, (HEIGHT-randomHeight)-MIN_FROM_EDGE);
    }

    public static int getWidth() {
        return WIDTH;
    }
    public int getX() {
        return X;
    }

    public void paintColumn(Graphics g){
        g.setColor(color);

        g.fillRect(top.x, top.y, top.width, top.height);

        g.fillRect(bottom.x, bottom.y, bottom.width, bottom.height);
    }
}