import lan.stdlib.StdOut;


public class Connect {
	private final int N = 16;
	private Synapse[] w;
	private double weight;
	private Sink sink;
	private ShiftRegister sr;
	
	public Connect(double input, Sink sink) {
		// TODO Auto-generated constructor stub
		//初始化移位寄存器
		sr = new ShiftRegister(input, N);		
		//初始化突触
		w  = new Synapse[N];
		for (int i = 0; i < N; i++) 
			w[i] = new Synapse();
		//初始化sink
		this.sink = sink;
		weight = 0.0;
	}
	
	public Synapse[] synapse() {
		return w;
	}
	
	public Sink sink() {
		return sink;
	}
	
	public ShiftRegister shiftReg() {
		return sr;
	}
	
	public double weight() {
		int m = 0;
		for (int i = 0; i < N; i++) {
			if (w[i].state() == 1) m++;
		}
		return (double)m/N;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
