package main.datahandler;

import java.util.HashMap;
import java.util.List;

import main.CalcDynamicLevels;
import main.filter.F_CO2;
import main.filter.F_PPL;
import main.filter.Filter;
import main.integration.I_CO2;
import main.integration.I_Light;
import main.integration.I_PIR;
import main.integration.I_PPL;
import main.integration.I_Sound;

public class InputOutput {
	private HashMap<String, Filter> integrator;
	private CalcDynamicLevels cdl;

	public InputOutput() {
		integrator = new HashMap<String, Filter>();
		I_PIR pir = new I_PIR();
		F_PPL ppl = new F_PPL(new I_PPL(), pir);
		integrator.put("movement", new Filter(pir));
		integrator.put("sound", new Filter(new I_Sound()));
		integrator.put("light", new Filter(new I_Light(), 200));
		integrator.put("carbon dioxide", new F_CO2(new I_CO2()));
		integrator.put("people", ppl);
		cdl = new CalcDynamicLevels(integrator, ppl);
	}

	public void input(List<String> v) {
		for (int i = 0; i < LineChart.NAMES.length; i++)
			if (integrator.containsKey(LineChart.NAMES[i]))
				integrator.get(LineChart.NAMES[i]).input(
						Double.parseDouble(v.get(i)));
		cdl.measure(v);
	}

	public double getOutput() {
		double sum = 0;
		for (int i = 0; i < LineChart.NAMES.length - 2; i++)
			sum += integrator.get(LineChart.NAMES[i]).output();

		sum += integrator.get("people").output() > 0 ? 0 : -1;
		System.out.println("Total: " + sum);
		return sum >= 1 ? 1 : 0;
	}
}
