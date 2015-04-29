package main.datahandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import main.CalcDynamicLevels;
import main.filter.Filter;
import main.integration.I_CO2;
import main.integration.I_Light;
import main.integration.I_PIR;
import main.integration.I_Piezo;
import main.integration.I_Sound;

public class InputOutput {
	private HashMap<String, Filter> integrator;
	private CalcDynamicLevels cdl;

	public InputOutput() {
		integrator = new HashMap<String, Filter>();
		integrator.put("movement", new Filter(new I_PIR()));
		integrator.put("sound", new Filter(new I_Sound()));
		integrator.put("piezo", new Filter(new I_Piezo()));
		integrator.put("light", new Filter(new I_Light()));
		integrator.put("carbon dioxide", new Filter(new I_CO2()));
		integrator.put("piezo golv", new Filter(new I_Piezo()));
		cdl = new CalcDynamicLevels(integrator);
	}

	public void input(List<String> v) {
		System.out.println(v + "HÄR");
		cdl.measure(v);
		for (int i = 0; i < v.size(); i++)
			if (integrator.containsKey(LineChart.NAMES[i]))
				integrator.get(LineChart.NAMES[i]).input(
						Double.parseDouble(v.get(i)));

	}

	public double getOutput() {
		double sum = 0;
		for (Entry<String, Filter> e : integrator.entrySet())
			sum += e.getValue().output();
		System.out.println("Total: " + sum);
		return sum >= 1 ? 1 : 0;
	}
}
