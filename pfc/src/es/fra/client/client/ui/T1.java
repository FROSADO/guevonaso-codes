package es.fra.client.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.SelectionCell;
import java.util.ArrayList;

public class T1 extends Composite {

	public T1() {
		
		CellTable<String> cellTable = new CellTable<String>();
		initWidget(cellTable);
		
		Column<String, String> colActual = new Column<String, String>(new EditTextCell()) {
			@Override
			public String getValue(String object) {
				return (String) null;
			}
		};
		cellTable.addColumn(colActual, "Estado Actual");
		
		Column<String, String> column = new Column<String, String>(new EditTextCell()) {
			@Override
			public String getValue(String object) {
				return (String) null;
			}
		};
		cellTable.addColumn(column, "Entrada");
		
		Column<String, String> column_1 = new Column<String, String>(new SelectionCell(new ArrayList<String>())) {
			@Override
			public String getValue(String object) {
				return (String) null;
			}
		};
		cellTable.addColumn(column_1, "Estado Final");
		
	}

}
