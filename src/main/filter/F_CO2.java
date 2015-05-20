package main.filter;

import main.integration.Integrator;

public class F_CO2 extends Filter {
	public F_CO2(Integrator integrator) {
		super(integrator);
	}

	private boolean choose = false;
	private double lastValue = 0;
	private double currentWeight = 0;

	public void input(double d) {
		if (lastValue == 0) {
			lastValue = d;
			integrator.input(getMax(d, th));
			currentWeight = getMax(d, th);
		} else if (lastValue - d < -25) {
			choose = true;
			increasing(d);
			lastValue = d;
		} else if (d - lastValue < -25) {
			choose = false;
			decreasing(d);
			lastValue = d;
		} else if (choose) {
			increasing(d);
		} else if (!choose)
			decreasing(d);
		else
			integrator.input(currentWeight);
	}

	private void decreasing(double d) {
		integrator.input(getMax(d, th) == 1 ? 0.0 : -1.5);
		currentWeight = getMax(d, th) == 1 ? 0.0 : -1.5;
	}

	private void increasing(double d) {
		integrator.input(getMax(d, th) == 1 ? 1.5 : 0.5);
		currentWeight = getMax(d, th) == 1 ? 1.5 : 0.5;
	}

	protected int getMax(double d, double max) {
		return d >= max ? 1 : -1;
	}
}
