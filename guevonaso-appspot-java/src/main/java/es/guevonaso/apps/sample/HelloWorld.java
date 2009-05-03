package es.guevonaso.apps.sample;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
@Validation()
public class HelloWorld extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7432161826761786290L;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	// -------------------------
	private Date now;

	public Date getNow() {
		return now;
	}

	@TypeConversion(converter = "es.guevonaso.apps.sample.DateConverter")
    @RequiredFieldValidator(message = "Please enter the date",shortCircuit = true)
	public void setNow(Date now) {
		this.now = now;
	}

	// ------------------------
	private String name;

	public String getName() {
		return name;
	}

	@RequiredStringValidator(message = "Please enter a name", trim = true)
	public void setName(String name) {
		this.name = name;
	}

}
