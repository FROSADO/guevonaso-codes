package es.gvn.blog.client.formater;

import com.google.gwt.core.client.JavaScriptObject;

import es.gvn.blog.client.CodeFormat;

public class FormatXML extends CodeFormat {

	@Override
	protected String format(String content) {
		// Replace all < and >
		String result = regExpReplace(content, regXMLTag(), "$1"
				+ TEMP_OPEN_SPAN_BLUE + "&lt;$2&gt;" + TEMP_CLOSE_SPAN + "$3");
		result = regExpReplace(result, regQuotes(), TEMP_OPEN_SPAN_PURPLE
				+ "$1" + TEMP_CLOSE_SPAN);
		return replaceSpan(result);
	}

	private native String regExpReplace(String text, JavaScriptObject regExp,
			String replacement) /*-{
		return text.replace(regExp,replacement);
	}-*/;

	private native JavaScriptObject regXMLTag() /*-{
		return new RegExp ("(.*)&lt;(.*)&gt;(.*)","g");
	}-*/;

	private native JavaScriptObject regQuotes() /*-{
		return new RegExp ("(\"(?!\").+?\")","g");
	}-*/;
}
