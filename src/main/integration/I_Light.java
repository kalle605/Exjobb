package main.integration;

public class I_Light extends Integrator {
	@Override
	public double output() {

		System.out.println(data.getValue() + " light");
		return data.getValue() == 1 ? Math.pow(0.3, getOutputInterval())
				* data.getValue() / (x() + 1) : data.getValue();
	}

	@Override
	public void input(int v) {
		if (v == 1 && data.getValue() != 1)
			LAST_CHANGE = data.setTime(System.currentTimeMillis());
		this.data.setValue(v == 1 ? 1 : -1);
	}
}
