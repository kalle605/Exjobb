package main.integration;

public class I_Piezo extends Integrator {

	@Override
	public double output() {
		System.out.println(Math.pow(0.5, getOutputInterval()) * data.getValue()
				/ (x() + 1) + " piezo");
		return data.getValue() == 1 ? Math.pow(0.5, getOutputInterval())
				* data.getValue() / (x() + 1) : data.getValue();

	}

}
