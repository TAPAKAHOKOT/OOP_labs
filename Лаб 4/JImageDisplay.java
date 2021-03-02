import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class JImageDisplay extends JComponent{
	private BufferedImage bimage;

	public static void main(String[] args){

	}

	public JImageDisplay(int w, int h, int iType){
		bimage = new BufferedImage(w, h, iType);
		super.setPreferredSize(new Dimension(w, h));
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bimage, 0, 0, bimage.getWidth(), bimage.getHeight(), null);
	}

	// Делаем все пиксели черными
	public void clearImage(){
		for (int i = 0; i < bimage.getWidth(); i++) {
			for (int j = 0; j < bimage.getHeight(); j++) {
				bimage.setRGB(i, j, new Color(0, 0, 0, 255).getRGB());
			}
		}
	}

	// Раскрашиваме определенный пиксель
	public void drawPixel(int x, int y, int rgbColor){
		// bimage.setRGB(x, y, new Color(0, 0, 0, 255).getRGB());
	}

	// Вычисления фрактала Мандельброта...
}
