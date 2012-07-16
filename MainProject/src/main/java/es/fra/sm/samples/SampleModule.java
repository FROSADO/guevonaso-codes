package es.fra.sm.samples;

import com.google.inject.AbstractModule;

public class SampleModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Person.class);
	}

}
