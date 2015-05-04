package main.integration;

public class I_PIR extends Integrator {
	@Override
	public double output() {

		System.out.println(data.getValue() == 1 ? (Math.pow(0.5,
				getOutputInterval()) * data.getValue() / (x() + 1) + " pir")
				: data.getValue() + " pir");
		return data.getValue() == 1 ? (Math.pow(0.5, getOutputInterval())
				* data.getValue() / (x() + 1)) : data.getValue();
	}
}
