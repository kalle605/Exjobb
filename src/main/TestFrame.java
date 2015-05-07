package main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.datahandler.LineChart;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = -802009526020433864L;
	private JPanel contentPane;
	private ArrayList<JLabel> labels;
	private int nbrofppl = 0;

	/**
	 * Create the frame.
	 * 
	 * @param values
	 */
	public TestFrame(ArrayList<Double> values) {
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

		int inc = 11;
		for (int i = 0; i < LineChart.NAMES.length - 1; i++) {
			JLabel temp = new JLabel(LineChart.NAMES[i]);
			temp.setBounds(40, inc, 100, 15);
			panel.add(temp);
			inc += 25;
		}

		inc = 11;
		for (double d : values) {
			JLabel temp = new JLabel(d + "");
			temp.setBounds(130, inc, 100, 15);
			panel.add(temp);
			labels.add(temp);
			inc += 25;
		}
		JLabel temp = new JLabel(0 + "");
		temp.setBounds(130, inc, 100, 15);
		panel.add(temp);
		labels.add(temp);

	}

	public synchronized void updateValues(ArrayList<Double> values) {
		System.out.println(values);
		for (int i = 0; i < values.size(); i++)
			labels.get(i).setText(values.get(i) + "");

		repaint();
	}

	public void updatePeople(int i) {
		nbrofppl += i;
		labels.get(4).setText(nbrofppl + "");
	}
}
