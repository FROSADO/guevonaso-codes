package es.guevonaso.apps.sample;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

@Conversion()
public class IndexAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6252326733152764567L;
	private Date now = new Date(System.currentTimeMillis());

	@TypeConversion(converter = "es.guevonaso.apps.sample.DateConverter")
	public Date getDateNow() {
		return now;
	}

	public String execute() throws Exception {
		now = new Date(System.currentTimeMillis());
		return SUCCESS;
	}
}
