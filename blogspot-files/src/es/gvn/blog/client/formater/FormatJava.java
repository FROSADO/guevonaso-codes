package es.gvn.blog.client.formater;

import com.google.gwt.core.client.JavaScriptObject;

import es.gvn.blog.client.CodeFormat;

public class FormatJava extends CodeFormat {

	// Java keywords :
	private static final String[] JAVA_KEYWORDS = new String[] { "abstract",
			"break", "case", "catch", "class", "continue", "do", "enum",
			"const", "transient", "else", "extends", "for", "finally",
			"foreach", "if", "import", "instanceof", "implements", "int",
			"interface", "implement", "new", "null", "package", "private",
			"protected", "public", "return", "static", "super", "switch",
			"this", "throw", "throws", "try", "while", "function", "zzzzzzzz" };
	private static final String[] OTHER_JAVA_KEYWORDS = new String[] { "char",
			"byte", "boolean", "float", "double", "long", "int", "null",
			"void", "new", "zzzzzzzz" };

	private static final String JAVA_COMMENT = "##--COMMENT--##";
	private static final String[] JAVA_GRAY_KEYWORDS = new String[] {
			"@Override", "@Deprecated", "@SuppressWarnings" };
	static String keywordsReg;
	static {
		keywordsReg = "(";

		for (int n = 0; n < JAVA_KEYWORDS.length; n++)
			keywordsReg += "\\b" + JAVA_KEYWORDS[n] + "\\b|";

		keywordsReg = keywordsReg + "string)";
	}
	static String moreKeyWords;
	static {
		moreKeyWords = "(";

		for (int n = 0; n < OTHER_JAVA_KEYWORDS.length; n++)
			moreKeyWords += "\\b" + OTHER_JAVA_KEYWORDS[n] + "\\b|";

		moreKeyWords = moreKeyWords + "string)";
	}
	// --- Other keywords
	static String otherKeywords;
	static {
		otherKeywords = "(";
		for (int i = 0; i < JAVA_GRAY_KEYWORDS.length; i++) {
			otherKeywords += JAVA_GRAY_KEYWORDS[i] + "|";
		}
		otherKeywords = otherKeywords + "string)";
	}

	@Override
	protected String format(String content) {

		String result = content;
		// Coloreo las comillas en purpura
		result = regExpReplace(content, regQuotes(), TEMP_OPEN_SPAN_PURPLE
				+ "$1" + TEMP_CLOSE_SPAN);
		// Los comentarios en verde
		result = regExpReplace(result, regComment(), TEMP_OPEN_SPAN_GREEN
				+ JAVA_COMMENT + "$1" + TEMP_CLOSE_SPAN + "<br/>");
		// Las palabras clave en azul
		result = regExpReplace(result, regKeyWords(keywordsReg),
				TEMP_OPEN_SPAN_BLUE + "$1" + TEMP_CLOSE_SPAN);
		result = regExpReplace(result, regKeyWords(moreKeyWords),
				TEMP_OPEN_SPAN_RED + "$1" + TEMP_CLOSE_SPAN);
		result = regExpReplace(result, regGrayWords(otherKeywords),
				TEMP_OPEN_SPAN_GRAY + "$1" + TEMP_CLOSE_SPAN);
		// Reemplazo todos los espacios por &nbsp;
		result = result.replace(" ", "&nbsp;");
		result = result.replace(JAVA_COMMENT, "//");
		result = replaceSpan(result);
		return result;
	}

	private native JavaScriptObject regKeyWords(String keywordsReg) /*-{
		return new RegExp(keywordsReg,"g");
	}-*/;

	private native JavaScriptObject regGrayWords(String otherKeywords) /*-{
		return new RegExp(otherKeywords,"g");
	}-*/;

	private native JavaScriptObject regComment() /*-{
		return new RegExp("//(.*)(\r|\n|\r\n)","g");
	}-*/;

	private native String regExpReplace(String text, JavaScriptObject regExp,
			String replacement) /*-{
		return text.replace(regExp,replacement);
	}-*/;

	private native JavaScriptObject regQuotes() /*-{
		return new RegExp ("(\"(?!\").+?\")","g");
	}-*/;

}
