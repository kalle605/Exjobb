package main.filter;

import main.integration.Integrator;

public class Filter {
	private double th = 1;
	private Integrator integrator;

	public Filter(Integrator integrator) {
		this.integrator = integrator;
	}

	public void input(double d) {
		System.out.println(th);
		integrator.input(getMax(d, th));
	}

	public double output() {
		return integrator.output();
	}

	protected int getMax(double d, double max) {
		return d > max ? 1 : 0;
	}

	public void setTreshHold(double th) {
		this.th = th;
	}
}
