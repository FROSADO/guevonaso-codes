package es.fra.pfc.qm;

public enum TermValue {

	Zero("0"), One("1"), DontCare("X");
	private final String format;

	TermValue(final String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return format;
	}

	public static TermValue getValue(int c) {
		if (c == '0') {
			return Zero;
		} else if (c == '1') {
			return One;
		} else {
			return DontCare;
		}
	}

}
