package main.integration;

public class I_CO2 extends Integrator {

	@Override
	public double output() {
		System.out.println(data.getValue() + " CO2");
		return data.getValue();
	}

	@Override
	public void input(double value) {
		this.data.setValue(value);
	}
}
