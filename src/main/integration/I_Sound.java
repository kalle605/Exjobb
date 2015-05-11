package main.integration;

public class I_Sound extends Integrator {
	@Override
	public double output() {
		System.out.println((data.getValue() == 1 ? 0.9
				* Math.pow(0.5, getOutputInterval()) * data.getValue()
				/ (x() + 1) : data.getValue())
				+ " sound");
		return data.getValue() == 1 ? 0.9 * Math.pow(0.5, getOutputInterval())
				* data.getValue() / (x() + 1) : data.getValue();
	}

}
