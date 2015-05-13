package main.filter;

import main.integration.I_PIR;
import main.integration.Integrator;

public class F_PPL extends Filter {
	private I_PIR pir;

	public F_PPL(Integrator integrator, I_PIR pir) {
		super(integrator);
	}

	@Override
	public void input(double d) {
		integrator.input(pir.output() > 0.9 ? d : 0);
	}
}
