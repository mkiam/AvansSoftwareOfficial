package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Welcome extends JPanel{
private BufferedImage image;
public Welcome() {
try {
	image = ImageIO.read(Welcome.class.getResource("/modeles/wel.jpg"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
protected void paintComponent(Graphics g) {
super.paintComponent(g);
g.drawImage(image, 0, 0, null); 
}
}
