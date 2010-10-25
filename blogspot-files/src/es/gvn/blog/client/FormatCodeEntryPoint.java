package es.gvn.blog.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;

public class FormatCodeEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		NodeList<Element> toFormat = Document.get().getElementsByTagName("pre");
		int count = toFormat.getLength();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				Element pre = toFormat.getItem(i);
				String className = pre.getClassName();
				CodeFormat f = FormatBuilder.getFormat(className);
				f.formatPre(pre);

			}
		}
	}

}
