package main.filter;

import main.integration.Integrator;

public class Filter {
	public Filter(Integrator integrator, double th) {
		this.integrator = integrator;
		this.th = th;
	}

	private double th = 1;
	private Integrator integrator;

	public Filter(Integrator integrator) {
		this.integrator = integrator;
	}

	public void input(double d) {
		integrator.input(getMax(d, th));
	}

	public double output() {
		return integrator.output();
	}

	protected int getMax(double d, double max) {
		return d >= max ? 1 : 0;
	}

	public void setTreshHold(double th) {
		this.th = th;
	}
}
