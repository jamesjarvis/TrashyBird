import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class Menu {

    private Color fontColor = Color.WHITE;
    private Font font = new Font("Arial", 1, 20);
    private String gameOver = "Game Over";
    private String score = "Score: ";
    private String start = "TrashyBird";
    private String startInstructions = "Click to start";

    Menu() {
    }

    void paintMenu(Graphics g){
        g.setColor(fontColor);
        g.setFont(font);

        if(!TrashyBird.isStarted()){
            g.drawString(start, 50, TrashyBird.getHEIGHT()/2);
            g.drawString(startInstructions, 75, TrashyBird.getHEIGHT()/2+50);
        }else if(TrashyBird.isGameOver()){
            g.drawString(gameOver, 50, TrashyBird.getHEIGHT()/2);
            g.drawString(startInstructions, 75, TrashyBird.getHEIGHT()/2+50);
        }else if(TrashyBird.isStarted()){
            g.drawString(score+TrashyBird.getScore(), 0, 20);
        }
    }
}
