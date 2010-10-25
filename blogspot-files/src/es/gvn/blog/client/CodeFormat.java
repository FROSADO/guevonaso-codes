package es.gvn.blog.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public abstract class CodeFormat {

	public static final String OPEN_SPAN_PURPLE = "<span class='formatted' style='color:purple'>";
	public static final String CLOSE_SPAN = "</span>";
	public static final String OPEN_SPAN_GREEN = "<span class='formatted' style='color:green'>";
	public static final String OPEN_SPAN_BLUE = "<span class='formatted' style='color:blue'>";
	public static final String OPEN_SPAN_GRAY = "<span class='formatted' style='color:gray'>";
	public static final String OPEN_SPAN_RED = "<span class='formatted' style='color:red'>";

	public static final String TEMP_OPEN_SPAN_PURPLE = "###~~~OP_SP_PRP~~~###";
	public static final String TEMP_CLOSE_SPAN = "###~~~CLOSE_SPAN~~~###";
	public static final String TEMP_OPEN_SPAN_GREEN = "###~~~OP_SP_GREEN~~~###";
	public static final String TEMP_OPEN_SPAN_BLUE = "###~~~OP_SP_BLUE~~~###";
	public static final String TEMP_OPEN_SPAN_GRAY = "###~~~OP_SP_GRAY~~~###";
	public static final String TEMP_OPEN_SPAN_RED = "###~~~OP_SP_RED~~~###";

	public void formatPre(Element pre) {
		String inner = pre.getInnerHTML();
		String result = format(inner);
		Element newPre = DOM.createElement("pre");
		Element newDIV = DOM.createElement("div");
		newPre.appendChild(newDIV);
		newDIV.setInnerHTML(result);
		newPre.setClassName(pre.getClassName());

		pre.getParentNode().replaceChild(newPre, pre);
	}

	protected abstract String format(String content);

	protected String replaceSpan(String result) {
		return result.replace(TEMP_OPEN_SPAN_PURPLE, OPEN_SPAN_PURPLE)
				.replace(TEMP_OPEN_SPAN_GREEN, OPEN_SPAN_GREEN)
				.replace(TEMP_OPEN_SPAN_BLUE, OPEN_SPAN_BLUE)
				.replace(TEMP_OPEN_SPAN_GRAY, OPEN_SPAN_GRAY)
				.replace(TEMP_OPEN_SPAN_RED, OPEN_SPAN_RED)
				.replace(TEMP_CLOSE_SPAN, CLOSE_SPAN);

	}
}
