package main.integration;

public class I_PPL extends Integrator {

	@Override
	public double output() {
		if (x() > 5 && data.getValue() > 0)
			data.setValue(data.getValue() - 1);
		return data.getValue();
	}

	@Override
	public void input(double value) {
		data.setValue((data.getValue() + value > 0) ? data.getValue() + value
				: 0);
		if (data.getValue() > 0)
			LAST_CHANGE = data.setTime(System.currentTimeMillis());
	}
}
