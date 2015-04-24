package main.filter;

import main.integration.Integrator;

public class F_Piezo extends Filter {
	private Integrator i_piezo;

	public F_Piezo(Integrator i_piezo) {
		this.i_piezo = i_piezo;
	}

	@Override
	public void input(double d) {
		i_piezo.input(getMax(d, 500));
	}

	@Override
	public double output() {
		return i_piezo.output();
	}

}
