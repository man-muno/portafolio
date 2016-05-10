package fml.tum.dummys;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

import fml.tum.utils.DatagramSender;

public class DummyDSA {
	
	private int index = 0;
	private boolean positive = false;

	public static void main(String[] args) throws Exception {
		new DummyDSA().start();
	}

	public void start() throws InterruptedException {
		while (true) {
			ByteBuffer buffer = ByteBuffer.allocate(30);
			buffer.order(ByteOrder.BIG_ENDIAN);
			buffer.clear();
			float degree = 0;// getRandomFloat(0, 45);
			int height = getNextValue();
			byte test = (byte) 1;
			for (int i = 0; i < 24; i++)
				buffer.put(test);
			buffer.putShort((short) degree);
			buffer.putShort((short) height);
			buffer.put(test);
			buffer.put(test);
			buffer.flip();

			new Thread(new DatagramSender("localhost",54352, buffer)).start();

			// Pause for 4 seconds
			Thread.sleep(500);
		}
	}

	private int getNextValue() {
		if (index ==  3500 || index ==  0)
			positive = !positive; 
		index = positive ? index + 50: index - 50;
		return index;
	}

	public static float getRandomFloat(int min, int max) {
		Random rand = new Random();
		float random = max * rand.nextFloat() + min;
		return random;
	}

	public static int getRandomInt(int min, int max) {
		Random rand = new Random();
		int random = rand.nextInt(max) + min;
		return random;
	}
}
