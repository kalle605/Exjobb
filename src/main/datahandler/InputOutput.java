package main.datahandler;

import java.util.HashMap;
import java.util.Map.Entry;

import main.filter.F_CO2;
import main.filter.F_Light;
import main.filter.F_PIR;
import main.filter.F_Piezo;
import main.filter.F_Sound;
import main.filter.Filter;
import main.integration.I_CO2;
import main.integration.I_Light;
import main.integration.I_PIR;
import main.integration.I_Piezo;
import main.integration.I_Sound;

public class InputOutput {
	private HashMap<String, Filter> integrator;

	public InputOutput() {
		integrator = new HashMap<String, Filter>();
		integrator.put("movement", new F_PIR(new I_PIR()));
		integrator.put("sound", new F_Sound(new I_Sound()));
		integrator.put("piezo", new F_Piezo(new I_Piezo()));
		integrator.put("light", new F_Light(new I_Light()));
		integrator.put("carbon dioxide", new F_CO2(new I_CO2()));
	}

	public void input(String[] v) {
		for (int i = 0; i < v.length; i++)
			if (integrator.containsKey(LineChart.NAMES[i]))
				integrator.get(LineChart.NAMES[i]).input(
						Double.parseDouble(v[i]));

	}

	public double getOutput() {
		double sum = 0;
		for (Entry<String, Filter> e : integrator.entrySet())
			sum += e.getValue().output();
		System.out.println("Total: " + sum);
		return sum >= 1 ? 1 : 0;
	}
}
