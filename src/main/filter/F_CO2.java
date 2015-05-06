package main.filter;

import main.integration.Integrator;

public class F_CO2 extends Filter {
	public F_CO2(Integrator integrator) {
		super(integrator);
	}

	private double lastValue = 0;

	public void input(double d) {
		if (lastValue - d < -10) {
			lastValue = d;
			integrator.input(1);
		} else if (d - lastValue < -10) {
			lastValue = d;
			integrator.input(0);
		}
		integrator.input(getMax(d, th));
	}

	protected int getMax(double d, double max) {
		return d >= max ? 1 : -1;
	}

}
