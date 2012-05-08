package es.fra.pfc.statemachine.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.fra.pfc.statemachine.presenter.ConfigDiagramPresenter;
import es.fra.pfc.statemachine.presenter.ConfigDiagramPresenter.DiagramValues;
import net.miginfocom.swing.MigLayout;

public class ConfigDiagramView extends JPanel implements
		ConfigDiagramPresenter.Display {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892338456408472694L;
	private JTextField entradasText;
	private JTextField salidasText;
	private JComboBox diagramaCombo;
	private static final String[] DIAGRAMS = new String[] { "Moore", "Mealy" };

	/**
	 * Create the panel.
	 */
	public ConfigDiagramView() {
		setLayout(new MigLayout("", "[95.00px][87px][34.00px][29.00px][75.00][50.00px][75.00px][50.00px][1px][86px]", "[][20px]"));

		JLabel lblTipoDeDiagrama = new JLabel("Tipo de Diagrama:");
		add(lblTipoDeDiagrama, "cell 0 0,alignx left,aligny center");

		diagramaCombo = new JComboBox();

		diagramaCombo.setModel(new DefaultComboBoxModel(DIAGRAMS));
		diagramaCombo.setSelectedIndex(0);
		add(diagramaCombo, "cell 1 0 2 1,alignx left,aligny center");

		JLabel lblNEntradas = new JLabel("Nº Entradas :");
		add(lblNEntradas, "cell 4 0,alignx left,aligny center");

		entradasText = new JTextField();
		entradasText.setText("1");
		add(entradasText, "cell 5 0,alignx left,aligny center");
		entradasText.setColumns(2);

		JLabel lblNSalidas = new JLabel("Nº Salidas :");
		add(lblNSalidas, "cell 6 0,alignx left,aligny center");

		salidasText = new JTextField();
		salidasText.setText("1");
		add(salidasText, "cell 7 0,alignx left,aligny center");
		salidasText.setColumns(2);

	}

	// -------------------------------------------------------
	// ---------- View ---------------------------------------
	// --------------------------------------------------------

	/**
	 * Return current diagram selected on combo. By Default return Moore.
	 */
	public DiagramValues getCurrentDiagram() {
		Object item = diagramaCombo.getSelectedItem();
		if (DIAGRAMS[0].equals(item)) {
			return DiagramValues.Moore;
		} else if (DIAGRAMS[1].equals(item)) {
			return DiagramValues.Mealy;
		}
		return DiagramValues.Moore;
	}

	public Integer getInputsNumber() {
		String input = this.entradasText.getText();
		try {
			return Integer.valueOf(input);
		} catch (NumberFormatException e) {
			entradasText.setText("1");
			return 1;
		}
	}

	public Integer getOutputNumber() {
		String output = this.salidasText.getText();
		try {
			return Integer.valueOf(output);
		} catch (NumberFormatException e) {
			salidasText.setText("1");
			return 1;
		}
	}

	public void setDiagram(DiagramValues value) {
		switch (value) {
		case Mealy:
			diagramaCombo.getModel().setSelectedItem("Mealy");
			break;
		case Moore:
			diagramaCombo.getModel().setSelectedItem("Moore");
		default:
			break;
		}

	}

	public void setInputNumber(Integer inputs) {
		salidasText.setText(inputs.toString());

	}

	public void setOuputNumber(Integer outputs) {
		entradasText.setText(outputs.toString());

	}

	public JComboBox getDiagramCombo() {
		return diagramaCombo;
	}

	public JTextField getInputNumberTextField() {
		return entradasText;
	}

	public JTextField getOutputNumberTextField() {
		return salidasText;
	}

}
