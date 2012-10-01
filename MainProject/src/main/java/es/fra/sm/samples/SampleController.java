package es.fra.sm.samples;

import com.google.inject.Inject;

public class SampleController {
	/**
	 * This domain data model is supplied in the constructor
	 */
	private final Person	person;

	/**
	 * Notice this constructor is using JSR 330 Inject annotation, which makes
	 * it "Guice Friendly".
	 * 
	 * @param person
	 */
	@Inject
	public SampleController(Person person) {
		this.person = person;
	}

	/**
	 * Used within the FXML document. This is just some state which the
	 * controller wants to expose for the view to bind to or use.
	 * 
	 * @return
	 */
	public final String getPersonName() {
		return this.person.getFirstName();
	}

	/**
	 * Some method which I will call from the FXML file.
	 */
	public void print() {
		System.out.println("Well done, " + this.getPersonName() + "!");
	}
}