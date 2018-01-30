import java.awt.*;

class Background {

    private Rectangle sky;
    private Color skyColor;

    private Rectangle floor;
    private Color floorColor;

    private Rectangle floorTopper;
    private Color floorTopperColor;

    private static int FLOOR_HEIGHT = 100;

    Background(int WIDTH, int HEIGHT) {
        this.sky = new Rectangle(0,0,WIDTH, HEIGHT);
        this.skyColor = Color.CYAN;

        this.floor = new Rectangle(0,HEIGHT-FLOOR_HEIGHT,WIDTH, FLOOR_HEIGHT);
        this.floorColor = Color.ORANGE;

        this.floorTopper = new Rectangle(0,HEIGHT-FLOOR_HEIGHT, WIDTH, 20);
        this.floorTopperColor = Color.GREEN;
    }

    static int getFLOOR_HEIGHT() {
        return FLOOR_HEIGHT;
    }

    void paintBackground(Graphics g){
        g.setColor(skyColor);
        g.fillRect(sky.x, sky.y, sky.width, sky.height);

        g.setColor(floorColor);
        g.fillRect(floor.x, floor.y, floor.width, floor.height);

        g.setColor(floorTopperColor);
        g.fillRect(floorTopper.x, floorTopper.y, floorTopper.width, floorTopper.height);
    }
}
