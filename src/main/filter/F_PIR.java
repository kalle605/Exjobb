package main.filter;

import main.integration.Integrator;

public class F_PIR extends Filter {
	private Integrator i_pir;

	public F_PIR(Integrator i_pir) {
		this.i_pir = i_pir;
	}

	@Override
	public void input(double d) {
		i_pir.input(d);

	}

	@Override
	public double output() {
		return i_pir.output();
	}

}
