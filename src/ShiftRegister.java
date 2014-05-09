import lan.stdlib.StdOut;

enum ShiftDirection {
	RIGHT, LEFT;
}

public class ShiftRegister {
	private int[] x;
	private double value;
	private int n;
	private int m;
	private final double XMIN = 0.0, XMAX = 1.0;
	
	public ShiftRegister(double xw, int N) {
		if (xw < XMIN || xw > XMAX)
			throw new java.lang.IllegalArgumentException();
		n = N;
		m = (int) (xw * N);
		x = new int[n];
		for (int i = 0; i < m; i++) {
			x[i] = 1;
		}
		value = (double) m/n;
	}
	
	public ShiftRegister(int[] x) {
		// TODO Auto-generated constructor stub
		this.x = x;
		n = x.length;
		m = 0;
		for (int i = 0; i < n; i++) {
			if (x[i] == 1)
				m++;
		}
		value = (double) m/n ;
	}
	
	public void shift() {
		shift(ShiftDirection.RIGHT, 1);
	}
	
	public void shift(ShiftDirection dir, int digits) {
		if (dir == ShiftDirection.RIGHT) {
			int[] t = x.clone();
			System.arraycopy(t, 0, x, digits, t.length - digits);
			System.arraycopy(t, t.length - digits, x, 0, digits);
		}
	}
	
	public int[] x() {
		return x;
	}
	
	public double value() {
		return value;
	}
	
	@Override
	public String toString() {
		String s = new String();
		
		s += String.format("Total digits: %3d\nMarked digits: %3d\n", n, m);
		s += "From low -> high bits:\n";
		for (int i = 0; i < n; i++) {
			s += String.format("%-3d",i+1);
		}
		s += "\n";
		for (int i = 0; i < n; i++) {
			s += String.format("%-3d",x[i]);
		}
		s += String.format("\nContinuous value:%4.3f", value);
		
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShiftRegister sr = new ShiftRegister(0.2, 10);
		//sr = new ShiftRegister(sr.x());
		StdOut.println(sr.toString());
	}
}
