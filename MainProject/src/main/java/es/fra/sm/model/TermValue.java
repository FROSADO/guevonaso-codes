package es.fra.sm.model;

public enum TermValue {

	Zero("0"), One("1"), DontCare("X");
	private final String	format;

	TermValue(final String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return this.format;
	}

	/**
	 * <ul>
	 * <li>'0' == Zero</li>
	 * <li>'1' == One</li>
	 * <li>Other = DontCare</li>
	 * </ul>
	 * 
	 * @param c
	 * @return
	 */
	public static TermValue getValue(char c) {
		if (c == '0') {
			return Zero;
		} else if (c == '1') {
			return One;
		} else {
			return DontCare;
		}
	}

	/**
	 * <ul>
	 * <li>0 == Zero</li>
	 * <li>1 == One</li>
	 * <li>Other = DontCare</li>
	 * </ul>
	 * 
	 * @param c
	 * @return
	 */
	public static TermValue getValue(Integer c) {
		if (c == 0) {
			return Zero;
		} else if (c == 1) {
			return One;
		} else {
			return DontCare;
		}
	}
}
