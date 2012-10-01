package es.fra.sm.samples;

import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceFXMLLoader {
	private final Injector	injector;

	@Inject
	public GuiceFXMLLoader(Injector injector) {
		this.injector = injector;
	}

	// Load some FXML file, using the supplied Controller, and return the
	// instance of the initialized controller...?
	public Object load(String url, Class<?> controller) {
		final Object instance = this.injector.getInstance(controller);
		final FXMLLoader loader = new FXMLLoader();
		loader.getNamespace().put("controller", instance);
		InputStream in = null;

		try {
			try {
				final URL u = this.getClass().getResource(url);
				// URL u = new URL(url);
				in = u.openStream();
			} catch (final Exception e) {
				in = controller.getResourceAsStream(url);
			}
			return loader.load(in);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception ee) {
				}
			}
		}
		return null;
	}
}
