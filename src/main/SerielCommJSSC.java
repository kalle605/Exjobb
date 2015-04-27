package main;

import java.io.IOException;

import jssc.SerialPortList;

public class SerielCommJSSC {
	public static void main(String[] args) {
		// getting serial ports list into the array
		String[] portNames = SerialPortList.getPortNames();

		if (portNames.length == 0) {
			System.out
					.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
			System.out.println("Press Enter to exit...");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		for (int i = 0; i < portNames.length; i++) {
			System.out.println(portNames[i]);
		}

	}
}
