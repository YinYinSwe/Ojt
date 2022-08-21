package spring.security.demo.common;

import java.security.SecureRandom;

/**
 * <h2> TokenGenerator Class</h2>
 * <p>
 * Process for Displaying TokenGenerator
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public final class TokenGenerator {
	/**
	 * <h2> chars</h2>
	 * <p>
	 * chars
	 * </p>
	 */
    private final String chars = "ABCDEFGJKLMNPRSTUVWXYZ";

	/**
	 * <h2> numbers</h2>
	 * <p>
	 * numbers
	 * </p>
	 */
	private final String numbers = "0123456789";

	/**
	 * <h2> random</h2>
	 * <p>
	 * random
	 * </p>
	 */
	private final SecureRandom random = new SecureRandom();

	/**
	 * <h2> rdm</h2>
	 * <p>
	 * rdm
	 * </p>
	 */
	private final char[] rdm;

	/**
	 * <h2> Constructor for TokenGenerator </h2>
	 * <p>
	 * Constructor for TokenGenerator
	 * </p>
	 * @param length
	 */
	public TokenGenerator(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		rdm = new char[length];
	}

	/**
	 * <h2> getToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getToken() {
		String symbols = chars + numbers + chars.toLowerCase();
		for (int idx = 0; idx < rdm.length; ++idx) {
			rdm[idx] = symbols.charAt(random.nextInt(symbols.length()));
		}
		return new String(rdm);
	}

}