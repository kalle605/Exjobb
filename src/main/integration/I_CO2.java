package main.integration;

public class I_CO2 extends Integrator {

	@Override
	public double output() {
		System.out.println(Math.pow(0.5, getOutputInterval()) * data.getValue()
				/ (x() + 1) + " CO2");
		return data.getValue() == 1 ? (Math.pow(0.5, getOutputInterval())
				* data.getValue() / (x() + 1)) : data.getValue();
	}

	public void input(double value) {
		if (value != data.getValue())
			LAST_CHANGE = data.setTime(System.currentTimeMillis());
		this.data.setValue(value == 1 ? 1 : -1);
	}
}
