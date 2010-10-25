package es.gvn.blog.client.formater;

import com.google.gwt.core.client.JavaScriptObject;

import es.gvn.blog.client.CodeFormat;

public class FormatCMD extends CodeFormat {

	// Java keywords :
	private static final String[] CMD_KEYWORDS = new String[] { "rem", "set",
			"if", "else", "exist", "errorlevel", "for", "in", "do", "break",
			"call", "copy", "chcp", "cd", "chdir", "choice", "cls", "country",
			"ctty", "date", "del", "erase", "dir", "echo", "exit", "goto",
			"loadfix", "loadhigh", "mkdir", "md", "move", "path", "pause",
			"prompt", "rename", "ren", "rmdir", "rd", "shift", "time", "type",
			"ver", "verify", "vol", "com", "con", "lpt", "nul", "defined",
			"not", "errorlevel", "endlocal", "setlocal", "cmdextversion" };
	

	static String keywordsReg;
	static {
		keywordsReg = "(";

		for (int n = 0; n < CMD_KEYWORDS.length; n++)
			keywordsReg += "\\b" + CMD_KEYWORDS[n] + "\\b|";

		keywordsReg = keywordsReg + "string)";
	}
	

	@Override
	protected String format(String content) {

		String result = content;
		// Coloreo las comillas en purpura
		result = regExpReplace(content, regQuotes(), TEMP_OPEN_SPAN_PURPLE
				+ "$1" + TEMP_CLOSE_SPAN);
		// Las palabras clave en azul
		result = regExpReplace(result, regKeyWords(keywordsReg),
				TEMP_OPEN_SPAN_BLUE + "$1" + TEMP_CLOSE_SPAN);
		// Reemplazo todos los espacios por &nbsp;
		result = result.replace(" ", "&nbsp;");
		
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
