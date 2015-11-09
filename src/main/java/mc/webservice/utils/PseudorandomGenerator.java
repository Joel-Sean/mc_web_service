package mc.webservice.utils;

import java.util.Random;

public class PseudorandomGenerator implements RandomGenerator {
	
	private Random rand = new Random();

	@Override
	public int nextInt() {
		return rand.nextInt();
	}

	@Override
	public int nextInt(int n) {
		return rand.nextInt(n);
	}

}
