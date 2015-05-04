package main.filter;

import java.util.LinkedList;

import main.integration.Integrator;

public class F_CO2 extends Filter {
	LinkedList<Double> values;

	public F_CO2(Integrator integrator) {
		super(integrator);
		values = new LinkedList<Double>();
	}

	public F_CO2(Integrator integrator, double th) {
		super(integrator, th);
		values = new LinkedList<Double>();
	}

	public void input(double d) {
		values.addLast(d);
		if (values.size() > 4)
			values.removeFirst();
		integrator.input(getMax(d, th));
	}

	public double output() {
		for (int i = 0; i < values.size() - 1; i++)
			if ((int) (values.get(i) / 10) > (int) (values.get(i + 1) / 10))
				return integrator.output();
		return -1 * integrator.output();
	}

}
