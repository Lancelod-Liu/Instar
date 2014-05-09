
public class Synapse {
	private int state;
	
	public Synapse() {
		// TODO Auto-generated constructor stub
		state = 0;
	}
	
	public double state() {
		return state;
	}
	
	//modify the weight
	public void update(int s) {
		if (s != 0 && s != 1)
			throw new java.lang.IllegalArgumentException();
		state = s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
