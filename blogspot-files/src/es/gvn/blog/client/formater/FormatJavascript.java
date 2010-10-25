package es.gvn.blog.client.formater;

import com.google.gwt.core.client.JavaScriptObject;

import es.gvn.blog.client.CodeFormat;

public class FormatJavascript extends CodeFormat {
	private static final String JAVA_COMMENT = "##--COMMENT--##";
	// Java keywords :
	private static final String[] JAVA_KEYWORDS = new String[] { "function",
			"prototype", "abstract", "break", "case", "catch", "char", "class",
			"continue", "do", "double", "else", "extends", "for", "foreach",
			"if", "import", "instanceof", "implements", "int", "interface",
			"implement", "new", "null", "package", "private", "protected",
			"public", "return", "static", "super", "switch", "this", "throw",
			"throws", "try", "while", "function", "zzzzzzzz" };

	static String keywordsReg;
	static {
		keywordsReg = "(";

		for (int n = 0; n < JAVA_KEYWORDS.length; n++)
			keywordsReg += "\\b" + JAVA_KEYWORDS[n] + "\\b|";

		keywordsReg = keywordsReg + "string)";
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
