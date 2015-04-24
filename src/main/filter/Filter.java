package main.filter;

public abstract class Filter {

	public abstract void input(double d);

	public abstract double output();

	protected int getMax(double d, int max) {
		return d >= max ? 1 : 0;
	}
}
