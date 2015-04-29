package main.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import main.datahandler.InputOutput;

import org.junit.Before;
import org.junit.Test;

public class TestInput {
	InputOutput io;

	@Before
	public void initialize() {
		io = new InputOutput();
	}

	@Test
	public void test() {
		String[] s = { "0", "0", "0", "0", "0" };
		Random r = new Random();
		int l, so, m, c, p;
		for (int i = 0; i < 100; i++)
			try {
				l = 0;
				so = r.nextInt() % 35 + 425;
				m = 0;
				c = r.nextInt() % 50 + 400;
				p = r.nextInt() % 50 + 400;
				String[] x = { so + "", m + "", l + "", p + "", c + "" };
				Thread.sleep(1000); // 1000 milliseconds is one second.
				io.input(Arrays.asList(x));
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		io.input(Arrays.asList(s));
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
