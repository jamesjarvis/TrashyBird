import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Bird{

    private Rectangle birdRect;
    private int SIZE;
    private Color color;

    public Bird(int WIDTH, int HEIGHT){
        this.SIZE = 20;
        this.birdRect = new Rectangle(WIDTH/2 - SIZE/2, HEIGHT/2 - SIZE/2, SIZE, SIZE);
        this.color = Color.RED;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void paintBird(Graphics g){
        g.setColor(color);
        g.fillRect(birdRect.x, birdRect.y, birdRect.width, birdRect.height);
    }
}
