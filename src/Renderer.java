import javax.swing.JPanel;
import java.awt.Graphics;

public class Renderer extends JPanel {


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        TrashyBird.trashyBird.repaint(g);
    }
}
