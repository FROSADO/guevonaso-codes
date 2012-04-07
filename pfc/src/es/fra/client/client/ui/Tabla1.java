package es.fra.client.client.ui;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.Header;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import java.util.Collections;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import java.util.List;
import java.util.ArrayList;

public class Tabla1 extends LayoutContainer {

	public Tabla1() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig clmncnfgNewColumn = new ColumnConfig("id", "New Column", 150);
		configs.add(clmncnfgNewColumn);
		
		ColumnConfig clmncnfgNewColumn_1 = new ColumnConfig("id", "New Column", 150);
		configs.add(clmncnfgNewColumn_1);
		
		ColumnConfig clmncnfgNewColumn_2 = new ColumnConfig("id", "New Column", 150);
		configs.add(clmncnfgNewColumn_2);
		
		EditorGrid editorGrid = new EditorGrid(new ListStore(), new ColumnModel(configs));
		add(editorGrid);
		editorGrid.setBorders(true);
	}

}
