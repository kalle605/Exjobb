package main.filter;

import main.integration.Integrator;

public class F_Light extends Filter {
	private Integrator i_light;

	public F_Light(Integrator i_light) {
		this.i_light = i_light;
	}

	@Override
	public void input(double d) {
		i_light.input(getMax(d, 80));
	}

	@Override
	public double output() {
		return i_light.output();
	}

}
