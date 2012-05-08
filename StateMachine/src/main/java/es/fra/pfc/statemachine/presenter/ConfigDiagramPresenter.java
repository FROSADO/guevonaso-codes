package es.fra.pfc.statemachine.presenter;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface ConfigDiagramPresenter {

	public enum DiagramValues {
		Moore, Mealy

	}

	public interface Display {
		DiagramValues getCurrentDiagram();

		Integer getInputsNumber();

		Integer getOutputNumber();

		void setDiagram(DiagramValues value);

		void setInputNumber(Integer inputs);

		void setOuputNumber(Integer outputs);

		JComboBox getDiagramCombo();

		JTextField getInputNumberTextField();

		JTextField getOutputNumberTextField();

	}
}
