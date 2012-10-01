package es.fra.sm.service;

import java.util.Collection;
import java.util.LinkedHashMap;

import es.fra.sm.model.TermValue;

/**
 * Class that code the list of states using a code. By default use Gray code:
 * <table>
 * <th>
 * <td>State</td>
 * <td>code</td></th>
 * <tr>
 * <td>S0</td>
 * <td>00</td>
 * </tr>
 * <tr>
 * <td>S1</td>
 * <td>01</td>
 * </tr>
 * <tr>
 * <td>S2</td>
 * <td>11</td>
 * </tr>
 * <tr>
 * <td>S3</td>
 * <td>11</td>
 * </tr>
 * </table>
 * 
 * 
 * @author User
 * 
 */
// TODO use different codification
public final class StateCoder {

	/**
	 * 
	 * Code states using a codification (By Default Gray code). The order it's
	 * important. A duplicated state will take the first occurrence value.
	 * Ignore uppercases. A "q0" it's equals that "Q0"
	 * 
	 * @param states
	 *            a list of states.
	 * @return
	 */
	public static TermValue[][] codeStates(Collection<String> states) {
		final LinkedHashMap<String, Integer> stateValues = new LinkedHashMap<String, Integer>();

		int x = 0;
		for (final String string : states) {

			if (!stateValues.containsKey(string.toUpperCase())) {
				stateValues.put(string.toUpperCase(), x++);
			}
		}

		final String bitX = Integer.toBinaryString(x - 1);
		final Collection<Integer> xvalues = stateValues.values();
		final int bitLength = bitX.length();
		final TermValue[][] result = new TermValue[xvalues.size()][bitLength];

		// TODO Solo calcula usando Gray.
		for (int i = 0; i < x; i++) {
			// Esta es la manera de calcular el valor gray usando java.
			String grayValue = Integer.toBinaryString((i >> 1) ^ i);
			final int z = bitLength - grayValue.length();
			for (int j = 0; j < z; j++) {
				grayValue = '0' + grayValue;
			}

			final char[] values = grayValue.toCharArray();
			for (int k = 0; k < values.length; k++) {
				final char d = values[k];
				result[i][k] = TermValue.getValue(d);
			}

		}

		return result;

	}

	private StateCoder() {

	}
}
