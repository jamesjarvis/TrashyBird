import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

class Bird{

    private Rectangle birdRect;
    private int SIZE;
    private Color color;
    private boolean alive;

    private int y_location;
    private int x_location;

    private double velocity;
    private double MAX_velocity = 15;

    Bird(int WIDTH, int HEIGHT){
        this.y_location = HEIGHT/2 - SIZE;
        this.x_location = WIDTH/2 - SIZE;
        this.alive = true;

        this.SIZE = 20;
        this.birdRect = new Rectangle(x_location, y_location, SIZE, SIZE);
        this.color = Color.RED;
        this.velocity=0;
    }

    void setColor(Color color) {
        this.color = color;
    }

    int getSIZE() {
        return SIZE;
    }

    int getY_location() {
        return y_location;
    }

    int getX_location() {
        return x_location;
    }

    void dead(){
        alive = false;
    }

    void incrementPosition(){
        velocity+=Math.abs(TrashyBird.getGRAVITY());
        velocity = Math.min(velocity, MAX_velocity);
        y_location+=(int)Math.round(velocity);

        if(this.y_location+this.SIZE>TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()){
            y_location = TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-this.SIZE;
            velocity = 0;
            TrashyBird.collision();
        }else if(y_location<0){
            y_location=0;
            velocity=0;
        }

        //System.out.println("V: "+velocity+", A:"+acceleration);
        birdRect.setLocation(x_location, y_location);
    }

    void jump(){
        velocity = -MAX_velocity;
    }

    void paintBird(Graphics g){
        g.setColor(color);
        g.fillRect(birdRect.x, birdRect.y, birdRect.width, birdRect.height);
    }
}
