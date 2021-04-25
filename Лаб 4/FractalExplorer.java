import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FractalExplorer{
	// Window size
	private int winSize;
	// used to disp;ay updaating
	private JImageDisplay display;
	// for future features
	private FractalGenerator generator;
	// for complex range
	private Rectangle2D.Double range;

	private JLabel coordsLabel;

	public FractalExplorer(int winSize){
		this.winSize = winSize;
		this.range = new Rectangle2D.Double();
		this.generator = new Mandelbrot();
		this.generator.getInitialRange(this.range);
		this.display = new JImageDisplay(winSize, winSize);
	}

	public void createAndShowGUI(){
		// JFrame init
		MouseAdapter mListener = new MListener();

		display.setLayout(new BorderLayout());
		display.addMouseListener(mListener);
		JFrame frame = new JFrame("Pathfinder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		coordsLabel = new JLabel("Coordinates: ");

		frame.add(coordsLabel, BorderLayout.PAGE_START);

		frame.add(display, BorderLayout.CENTER);
		
		ActionListener aListener = new AListener();

		JButton droppingButton = new JButton("Drop images");
		droppingButton.addActionListener(aListener);
		
		frame.add(droppingButton, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	private void drawFractal(){
		double xCoord, yCoord;
		float hue;
		int iters, rgbColor;

		for (int x = 0; x < winSize; x++){
			for (int y = 0; y < winSize; y++){
				xCoord = generator.getCoord(range.x, range.x + range.width, winSize, x);
				yCoord = generator.getCoord(range.y, range.y + range.height, winSize, y);

				iters = generator.numIterations(xCoord, yCoord);

				hue = 0.7f + (float)iters / 200f;
				rgbColor = iters == -1 ? 0 : Color.HSBtoRGB(hue, 1f, 1f);

				display.drawPixel(x, y, rgbColor);
			}
		}

		display.repaint();

	}

	public static void main(String[] args) {
		FractalExplorer exp = new FractalExplorer(800);
		exp.createAndShowGUI();
		exp.drawFractal();
	} 

	public class AListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			display.clearImage();
			generator.getInitialRange(range);
			drawFractal();
		}
	}

	public class MListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			double x = e.getPoint().getX();
			double y = e.getPoint().getY();

			coordsLabel.setText("Coordinates:  x -> " + x + "   y - >" + y);

			x = generator.getCoord(range.x, range.x + range.width, winSize, (int)x);
			y = generator.getCoord(range.y, range.y + range.height, winSize, (int)y);

			generator.recenterAndZoomRange(range, x, y, 0.5);
			drawFractal();
			display.repaint();
		}
	}
}