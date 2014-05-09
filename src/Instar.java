import java.awt.Color;
import java.awt.image.ImageProducer;
import java.io.File;

import lan.stdlib.Picture;


public class Instar {
	public final int CYCLEMAX = 351;
	private final int N = 16;
	private final int pointNum;
	private double epsilon = 0.01;
	private Sink sink;
	private Connect[] connect;	
	private double[] weight;
	
	public Instar(double[] input) {
		// TODO Auto-generated constructor stub
		pointNum = input.length;
		sink = new Sink(N, 1.0, epsilon);
		connect = new Connect[pointNum];
		weight = new double[pointNum];
		for (int i = 0; i < pointNum; i++) 
			connect[i] = new Connect(input[i], sink);
	}
	
	public Sink sink() {
		return sink;
	}
	
	public double[] weight() {
		for (int i = 0; i < pointNum; i++) {
			weight[i] = connect[i].weight();
		}
		return weight;
	}
	
	private double collect(int x[], Synapse w[]) {
		double res = 0.0;
		for(int i = 0; i < x.length; i++) {
			res += x[i] * w[i].state();
		}
		return res;
	}
	
	private void update(int x[], Synapse w[]) {
		int y[] = sink.y();
		for(int i = 0; i < w.length; i++) {
			if (y[i] == 1)
				w[i].update(x[i]);
		}
	}
	
	public void cycle() {
		sink.refresh();
		//clear out
		sink.out(0.0);
		//calculate input
		for (int i = 0; i < pointNum; i++) {			
			ShiftRegister sr = connect[i].shiftReg();
			Synapse w[] = connect[i].synapse();
			double out = 0.0;
			for (int j = 0; j < N; j++) {
				out += collect(sr.x(), w);
				sr.shift();
			}
			sink.out(out);
			update(sr.x(), w);
		}
	}
	
	private static Picture toGrayScale(Picture pic) {
		int w = pic.width();
		int h = pic.height();
		Picture p = new Picture(w, h);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color cl = pic.get(x, y);
				int r = cl.getRed();
				int b = cl.getBlue();
				int g = cl.getGreen();
				int grey = (int)(0.3*r+0.59*g+0.11*b);
				p.set(x, y, new Color(grey, grey, grey));
			}
		}		
		return p;
	}
	
	private static Picture imageTrans(double img[], int width) {
		int w = width;
		int h = img.length/width;
		Picture pic = new Picture(w, h);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int grey = (int) (img[x*h + y] * 255);
				pic.set(x, y, new Color(grey, grey, grey));
				//img[x*w + y] = (double)(pic.get(x, y).getBlue()/255) * 2.0 - 1.0;
			}
		}
		
		return pic;
	}
	
	private static double[] imageTrans(Picture pic) {
		int w = pic.width();
		int h = pic.height();
		double img[] = new double[w*h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				img[x*h + y] = (double)(pic.get(x, y).getBlue())/255;
			}
		}
		
		return img;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(args[0]);
		Picture pic = new Picture(file);
		Picture newpic = toGrayScale(pic);
		double img[] = imageTrans(newpic);
		Instar is = new Instar(img);
		ImageDisplay id = new ImageDisplay(imageTrans(is.weight(), newpic.width()));
		id.setVisible(true);
		for (int i = 0; i < is.CYCLEMAX; i++) {
			is.cycle();
			if (i % 10 == 0) {
				newpic = imageTrans(is.weight(), newpic.width());
				id.refresh(newpic);
				id.refresh(String.format("cycle: %d out: %.3f", i, is.sink().out()));
			
			}
				
		}
	}

}
