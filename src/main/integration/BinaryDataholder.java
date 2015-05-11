package main.integration;

public class BinaryDataholder {
	private double value;
	private long time;

	public BinaryDataholder() {
	}

	public long getTimeDifference() {
		return System.currentTimeMillis() - getTime();
	}

	public long getTime() {
		return time;
	}

	public long setTime(long time) {
		this.time = time;
		return time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value2) {
		this.value = value2;
	}
}