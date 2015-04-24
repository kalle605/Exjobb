package main.integration;

public class BinaryDataholder {
	private int value;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}