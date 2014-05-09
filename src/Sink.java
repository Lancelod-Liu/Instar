import lan.stdlib.StdOut;


public class Sink {
	private double value, ywave;
	private double epsilon;
	private int n;
	private int[] y;
	private final double YMIN = 0.0, YMAX = 1.0;
	private double out;
	
	public Sink(int n, double y, double eps) {
		// TODO Auto-generated constructor stub
		if (y < YMIN || y > YMAX || eps < 0.0 || eps > 1.0)
			throw new java.lang.IllegalArgumentException();
		this.n = n;
		this.y = new int[n];
		value = y;
		epsilon = eps;
		out = 0.0;
		if (Math.random() < value) 
			ywave = 1.0;
		else
			ywave = 0.0;
		for (int i = 0; i < n; i++) {
			if (Math.random() < epsilon)
				this.y[i] = (int) ywave;
			else
				this.y[i] = 0;
		}
	}
	
	public int[] y() {
		return y;
	}
	
	public void refresh() {
		if (Math.random() < value) 
			ywave = 1.0;
		else
			ywave = 0.0;
		for (int i = 0; i < n; i++) {
			if (Math.random() < epsilon)
				this.y[i] = (int) ywave;
			else
				this.y[i] = 0;
		}
	}
	
	public double out() {
		return out;
	}
	
	public double out(double d) {
		if (d == 0.0) out = d;
		else
			out += d;
		return out;
	}
	
	@Override
	public String toString() {
		String s = new String();
		
		s += String.format("Total digits: %3d\n", n);
		s += "From low -> high bits:\n";
		for (int i = 0; i < n; i++) {
			s += String.format("%-3d",i+1);
		}
		s += "\n";
		for (int i = 0; i < n; i++) {
			s += String.format("%-3d",y[i]);
		}
		s += String.format("\nvalue:%4.3f\nepsilon:%4.3f", value, epsilon);
		
		return s;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sink s = new Sink(16, 1, 0.2);
		StdOut.println(s.toString());
	}

}
