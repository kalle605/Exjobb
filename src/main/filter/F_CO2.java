package main.filter;

import main.integration.Integrator;

public class F_CO2 extends Filter {
	public F_CO2(Integrator integrator) {
		super(integrator);
	}

	private double lastValue = 0;

	public void input(double d) {
		if (lastValue - d < -25) {
			lastValue = d;
			integrator.input(getMax(d, th) == 1 ? 1.5 : 0.5);
		} else if (d - lastValue < -25) {
			lastValue = d;
			integrator.input(getMax(d, th) == 1 ? -0.5 : -1.5);
		} else
			integrator.input(getMax(d, th));
	}

	protected int getMax(double d, double max) {
		return d >= max ? 1 : -1;
	}

}
