package main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = -802009526020433864L;
	private JPanel contentPane;
	private ArrayList<JLabel> labels;

	/**
	 * Create the frame.
	 */
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		labels = new ArrayList<JLabel>();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel _light = new JLabel("Light");
		_light.setBounds(46, 11, 100, 14);
		panel.add(_light);

		JLabel _co2 = new JLabel("CO2");
		_co2.setBounds(46, 36, 100, 14);
		panel.add(_co2);

		JLabel _pir = new JLabel("PIR");
		_pir.setBounds(46, 61, 100, 14);
		panel.add(_pir);

		JLabel _piezo = new JLabel("Piezo");
		_piezo.setBounds(46, 86, 100, 14);
		panel.add(_piezo);

		JLabel _sound = new JLabel("Sound");
		_sound.setBounds(46, 111, 100, 14);
		panel.add(_sound);

		JLabel light = new JLabel("New label");
		light.setBounds(117, 11, 100, 14);
		panel.add(light);

		JLabel co2 = new JLabel("New label");
		co2.setBounds(117, 36, 100, 14);
		panel.add(co2);

		JLabel pir = new JLabel("New label");
		pir.setBounds(117, 61, 100, 14);
		panel.add(pir);

		JLabel piezo = new JLabel("New label");
		piezo.setBounds(117, 86, 100, 14);
		panel.add(piezo);

		JLabel sound = new JLabel("New label");
		sound.setBounds(117, 111, 100, 14);
		panel.add(sound);

		labels.add(light);
		labels.add(co2);
		labels.add(pir);
		labels.add(piezo);
		labels.add(sound);
	}

	public void updateValues(String[] values) {
		for (int i = 0; i < values.length; i++)
			labels.get(i).setText(values[i]);
		repaint();
	}
}
