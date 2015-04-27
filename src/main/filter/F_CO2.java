package main.filter;

import main.integration.I_CO2;

public class F_CO2 extends Filter {
	private I_CO2 i_co2;

	public F_CO2(I_CO2 i_CO2) {
		this.i_co2 = i_CO2;
	}

	@Override
	public void input(double d) {
		i_co2.input(getMax(d, 500));
	}

	@Override
	public double output() {
		return i_co2.output();
	}

}
