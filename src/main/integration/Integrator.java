package main.integration;

public abstract class Integrator {
	protected static final int INPUT_INTERVAL = 240000;
	protected static long LAST_CHANGE = 0;
	protected static final int OUTPUT_INTERVAL = 120000;
	protected BinaryDataholder data = new BinaryDataholder();

	public void input(double value) {
		if (value == 1) {
			this.data.setValue(1);
			LAST_CHANGE = data.setTime(System.currentTimeMillis());
		}
	}

	public abstract double output();

	protected int x() {
		int x = (int) (data.getTimeDifference() / INPUT_INTERVAL);
		return x;
	}

	protected long getOutputInterval() {
		return (System.currentTimeMillis() - LAST_CHANGE) / OUTPUT_INTERVAL;
	}

}
