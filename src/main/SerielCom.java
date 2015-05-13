package main;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import main.datahandler.AddData;
import main.datahandler.LineChart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class SerielCom implements SerialPortEventListener {
	private SerialPort serialPort;
	private XYSeriesCollection dataset;
	private XYSeriesCollection dataset2;
	private AddData data;
	static double arr[];

	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;

	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier("COM4");
		} catch (NoSuchPortException e1) {
			e1.printStackTrace();
		}

		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);
			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			// open the streams
			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			dataset = createXYSeriesCollection();
			dataset2 = createXYSeriesCollection1();
			data = new AddData(dataset, dataset2);

			LineChart chart = new LineChart("Mätningsprogram", "Measurements",
					dataset, dataset2);
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);

		} catch (Exception e) {
			System.err.println("Fel i SerialCom");
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			
				try {
					data.addData(input.readLine());
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}

	public static void main(String[] args) throws Exception {

		SerielCom main = new SerielCom();
		main.initialize();
		Thread t = new Thread() {
			public void run() {
				// the following line will keep this app alive for 1000 seconds,
				// waiting for events to occur and responding to them (printing
				// incoming messages to console).
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
				}
			}
		};
		t.start();
		System.out.println("Started");
	}

	private XYSeriesCollection createXYSeriesCollection1() {
		XYSeriesCollection temp = new XYSeriesCollection();
		temp.addSeries(new XYSeries("Detected Presence"));
		return temp;
	}

	private XYSeriesCollection createXYSeriesCollection() {
		XYSeriesCollection temp = new XYSeriesCollection();
		for (int i = 0; i < LineChart.NAMES.length - 1; i++)
			temp.addSeries(new XYSeries(LineChart.NAMES[i]));
		return temp;
	}
}