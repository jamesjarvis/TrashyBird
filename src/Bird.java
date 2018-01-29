import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Bird{

    private Rectangle birdRect;
    private int SIZE;
    private Color color;
    private boolean alive;

    private int y_location;
    private int x_location;

    private double velocity;
    private double MAX_velocity = 15;

    public Bird(int WIDTH, int HEIGHT){
        this.y_location = HEIGHT/2 - SIZE;
        this.x_location = WIDTH/2 - SIZE;
        this.alive = true;

        this.SIZE = 20;
        this.birdRect = new Rectangle(x_location, y_location, SIZE, SIZE);
        this.color = Color.RED;
        this.velocity=0;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSIZE() {
        return SIZE;
    }

    public double getVelocity() {
        return velocity;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getY_location() {
        return y_location;
    }

    public int getX_location() {
        return x_location;
    }

    public void dead(){
        alive = false;
    }

    public void incrementPosition(){
        velocity+=Math.abs(TrashyBird.getGRAVITY());
        velocity = Math.min(velocity, MAX_velocity);
        y_location+=(int)Math.round(velocity);

        if(this.y_location+this.SIZE>TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()){
            y_location = TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-this.SIZE;
            velocity = 0;
            dead();
        }else if(y_location<0){
            y_location=0;
            velocity=0;
        }

        //System.out.println("V: "+velocity+", A:"+acceleration);
        birdRect.setLocation(x_location, y_location);
    }

    public void jump(){
        velocity = -15;
    }

    public void paintBird(Graphics g){
        g.setColor(color);
        g.fillRect(birdRect.x, birdRect.y, birdRect.width, birdRect.height);
    }
}
