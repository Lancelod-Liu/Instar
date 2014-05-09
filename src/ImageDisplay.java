import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lan.stdlib.Picture;


public class ImageDisplay extends JFrame {
	private JLabel jLabel;
	private JLabel jLabel_count;
	
	
	public ImageDisplay(Picture pic) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(pic.width()+100,pic.height()+100);
		this.getContentPane().setLayout(null);
		
		jLabel_count = new JLabel();
		jLabel_count.setBounds(20,5,20+pic.width(),30);
		jLabel_count.setText("Count");
		this.add(jLabel_count);
		
		jLabel = new JLabel();
		jLabel.setBounds(20, 30, 20+pic.width(),30+pic.height());
		BufferedImage image = new BufferedImage(pic.width(), pic.height(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < pic.width(); x++) {
			for (int y = 0; y < pic.height(); y++) {
				image.setRGB(x, y, pic.get(x, y).getRGB());
			}
		}		
		ImageIcon imgicon = new ImageIcon(image);
		jLabel.setIcon(imgicon);
		
		this.add(jLabel);
		this.setTitle("Instar programe");
	}
	
	public void refresh(Picture pic) {
		BufferedImage image = new BufferedImage(pic.width(), pic.height(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < pic.width(); x++) {
			for (int y = 0; y < pic.height(); y++) {
				image.setRGB(x, y, pic.get(x, y).getRGB());
			}
		}		
		ImageIcon imgicon = new ImageIcon(image);
		jLabel.setIcon(imgicon);
		this.add(jLabel);
	}
	
	public void refresh(String s) {
		jLabel_count.setText(s);
		this.add(jLabel_count);
		this.repaint();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ImageDisplay id = new ImageDisplay();
		//id.setVisible(true);
	}

}
