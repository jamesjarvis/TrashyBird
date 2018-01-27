import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Bird{

    private Rectangle birdRect;
    private int SIZE;
    private Color color;

    private int y_location;
    private int x_location;

    private double velocity;
    private double MAX_velocity = 10;
    private double acceleration;
    private double MAX_acceleration = 0.9;

    public Bird(int WIDTH, int HEIGHT){
        this.y_location = HEIGHT/2 - SIZE;
        this.x_location = WIDTH/2 - SIZE;

        this.SIZE = 20;
        this.birdRect = new Rectangle(x_location, y_location, SIZE, SIZE);
        this.color = Color.RED;
        this.velocity=0;
        this.acceleration=0;
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

    public void incrementPosition(){
        if(y_location>=0&&(y_location+SIZE)<(TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT())) {
            y_location+=velocity;
            if(velocity<MAX_velocity&&velocity>-MAX_velocity){
                velocity+=acceleration;
            }else if(velocity<-MAX_velocity+5){
                velocity = -MAX_velocity+4;
            }else if(velocity>MAX_velocity){
                velocity = MAX_velocity-1;
            }
            if(acceleration<MAX_acceleration){
                acceleration+=0.5; //This is where the JUMP magic happens
            }
        }else if(y_location+SIZE>=(TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT())){
            y_location = TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-SIZE;
            velocity = 0;
            acceleration = 0;
        }else if(y_location<0){
            y_location = 0;
            velocity = 0;
            acceleration = 0;
        }

        //System.out.println("V: "+velocity+", A:"+acceleration);
        birdRect.setLocation(x_location, y_location);
    }

    public void jump(){
        if(acceleration>-3*MAX_acceleration){
            acceleration = -3*MAX_acceleration;
            velocity-=1;
        }else{

        }
    }

    public void paintBird(Graphics g){
        g.setColor(color);
        g.fillRect(birdRect.x, birdRect.y, birdRect.width, birdRect.height);
    }
}
