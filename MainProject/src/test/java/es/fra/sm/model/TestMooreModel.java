package es.fra.sm.model;

import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestMooreModel {

	@Test
	public void saveSample1ToFile() throws IOException, ClassNotFoundException {
		// Model must be serializable
		final MooreState a = new MooreState("A");
		a.setOutput(new MooreOutput(new TermValue[] { Zero }));

		final MooreTransition taa = new MooreTransition();
		taa.setInputValue(new TermValue[] { Zero });
		taa.setNextState("A");
		final MooreTransition tab = new MooreTransition();
		taa.setInputValue(new TermValue[] { Zero });
		taa.setNextState("B");
		a.addTransition(taa);
		a.addTransition(tab);

		final MooreState b = new MooreState("B");
		b.setOutput(new MooreOutput(new TermValue[] { One }));

		final MooreTransition tbb = new MooreTransition();
		tbb.setInputValue(new TermValue[] { Zero });
		tbb.setNextState("B");
		final MooreTransition tba = new MooreTransition();
		tba.setInputValue(new TermValue[] { One });
		tba.setNextState("A");

		b.addTransition(tbb);
		b.addTransition(tba);

		final ArrayList<MooreState> states = new ArrayList<>();
		states.add(a);
		states.add(b);

		final FileOutputStream fout = new FileOutputStream("sample1.dat");
		final ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(states);
		oos.close();
		final List<MooreState> result = this.readObjectsFromFile("sample1.dat");
		Assert.assertEquals(result, states);

	}

	@SuppressWarnings("unchecked")
	public List<MooreState> readObjectsFromFile(String filename) throws IOException, ClassNotFoundException {
		final FileInputStream fin = new FileInputStream(filename);
		final ObjectInputStream ois = new ObjectInputStream(fin);
		final List<MooreState> result = (List<MooreState>) ois.readObject();
		ois.close();
		return result;
	}

}
