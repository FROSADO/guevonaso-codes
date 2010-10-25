package es.gvn.blog.client;

import es.gvn.blog.client.formater.FormatCMD;
import es.gvn.blog.client.formater.FormatJava;
import es.gvn.blog.client.formater.FormatJavascript;
import es.gvn.blog.client.formater.FormatXML;

public class FormatBuilder {

	public static CodeFormat getFormat(String className) {
		if (className != null) {
			if ("java".equals(className)) {
				return new FormatJava();
			} else if ("javascript".equals(className)) {
				return new FormatJavascript();
			} else if ("cmd".equals(className)) {
				return new FormatCMD();
			} else if ("xml".equals(className)) {
				return new FormatXML();
			}
		}
		return new FormatJava();
	}

}
