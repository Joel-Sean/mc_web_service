package mc.webservice.utils;

public interface RandomGenerator {

	/**
	 * Returns the next random integer
	 * @return
	 * @throws Exception 
	 */
	int nextInt() throws Exception;
	/**
	 * Returns the next random integer between 0 (inclusive) and the specified value (exclusive)
	 * @param n
	 * @return
	 * @throws Exception 
	 */
	int nextInt(int n) throws Exception;
	
}
