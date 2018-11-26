/*
 * Taken partly from: https://github.com/chrishantha/sample-java-programs/releases/tag/v0.0.1
 * Copyright 2015 M. Isuru Tharanga Chrishantha Perera
 */
package de.syrocon.java11;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Flight {

	public static void main(String[] args) throws Exception {
		System.out.println("Wait before application start...");
		Thread.sleep(10000);

		System.out.println("Starting thread load...");
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("Exiting now...");
				System.exit(0);
			}
		}, 30000);

		for (int i = 1; i <= 100; i++) {
			Thread thread = new Thread(new MathWorker());
			thread.setName(String.format("Thread %d: %s", i, "Math"));
			thread.start();
		}
	}
}

class MathWorker implements Runnable {

	private Random random = new Random();

	@Override
	public void run() {
		BigDecimal bigDecimal = BigDecimal.ZERO;
		while (true) {
			double value = Math.atan(Math.sqrt(Math.pow(random.nextInt(10), random.nextDouble())));
			if (random.nextInt(10) > 5) {
				bigDecimal = bigDecimal.add(BigDecimal.valueOf(value));
			} else {
				bigDecimal = bigDecimal.subtract(BigDecimal.valueOf(value));
			}
		}
	}
}
