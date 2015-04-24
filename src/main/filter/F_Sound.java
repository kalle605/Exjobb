package main.filter;

import main.integration.Integrator;

public class F_Sound extends Filter {
	private Integrator i_sound;

	public F_Sound(Integrator i_sound) {
		this.i_sound = i_sound;
	}

	@Override
	public void input(double d) {
		i_sound.input(getMax(d, 410));
	}

	@Override
	public double output() {
		return i_sound.output();
	}

}
