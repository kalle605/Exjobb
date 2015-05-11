package main.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class SuperTestIR {

	@Test
	public void test() {
		boolean SENSOR1 = true, SENSOR2 = false, SENSOR1_LOW, SENSOR2_LOW, SENSOR1_LOW_FIRST, SENSOR2_LOW_FIRST, LED2;

		SENSOR1_LOW = SENSOR2_LOW = SENSOR1_LOW_FIRST = SENSOR2_LOW_FIRST = LED2 = false;
		if (!SENSOR1 && !SENSOR1_LOW) {
			SENSOR1_LOW = true;
			if (!SENSOR2_LOW) {
				SENSOR1_LOW_FIRST = true;
			}
		}
		if (!SENSOR2 && !SENSOR2_LOW) {
			SENSOR2_LOW = true;
			if (!SENSOR1_LOW) {
				SENSOR2_LOW_FIRST = true;
			}
		}

		if (SENSOR2_LOW && SENSOR1_LOW) {
			System.out.println("hej");
			if (SENSOR1_LOW_FIRST) {
				// LED2 = true;
				// // __delay_ms(200);
				// LED2 = false;
				// // __delay_ms(200);
				// LED2 = true;
				// // __delay_ms(200);
				// LED2 = false;
				//
				// OUT1 = false;
				// // __delay_ms(10);
				// OUT1 = true;
			}
			if (SENSOR2_LOW_FIRST) {
				// LED1 = true;
				// // __delay_ms(200);
				// LED1 = false;
				// // __delay_ms(200);
				// LED1 = true;
				// // __delay_ms(200);
				// LED1 = false;
				//
				// OUT1 = false;
				// // __delay_ms(20);
				// OUT1 = true;
			}
		}
		assertEquals(false, SENSOR1_LOW_FIRST);
		assertEquals(true, SENSOR2_LOW_FIRST);

	}

}
