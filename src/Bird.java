import java.awt.Rectangle;

public class Bird extends Rectangle {

    private int SIZE;

    public Bird(int WIDTH, int HEIGHT, int SIZE){
        super(WIDTH/2 - SIZE/2, HEIGHT/2 - SIZE/2, SIZE, SIZE);
        this.SIZE = SIZE;
    }

    public int getSIZE() {
        return SIZE;
    }
}
