/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.util.List;

import javax.swing.table.TableModel;

import org.janus.gui.basis.TableColumnDescription;
import org.janus.gui.enums.GuiType;

import com.vaadin.data.Item;
import com.vaadin.ui.Table;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class TableViewConnector extends VaadinTableModelConnector {

    /**
     * Constructor declaration
     * 
     * 
     * @param node
     * @param name
     * @param action
     * 
     * @see
     */
    public TableViewConnector(Table table,
            List<TableColumnDescription> columnDescription) {
        super(GuiType.SHOWTABLE, table);
        createColumnsFromDescriptions(table, columnDescription);
    }

    public Table getTableView() {
        return (Table) getComponent();
    }

    protected void createColumnsFromDescriptions(Table table,
            List<TableColumnDescription> columnDescription) {
        // table.setAutoCreateColumnsFromModel(false);
        // TableColumnModel cm = table.getColumnModel();

        int index = 0;
        Object columnNames[] = new String[columnDescription.size()];
        for (TableColumnDescription desc : columnDescription) {
            columnNames[index] = desc.getName();
            getItems().addContainerProperty(desc.getName(), String.class, null);
            index++;
        }
        table.setVisibleColumns(columnNames);
        for (TableColumnDescription desc : columnDescription) {
            table.setColumnHeader(desc.getName(), desc.getHeader());
        }
    }

    @Override
    protected void tranferValuesToItem(TableModel model, int row) {
        Item item = getItems().addItem(new Integer(row));
        int columnCount = model.getColumnCount();
        for (int column = 0; column < columnCount; column++) {
            item.getItemProperty(model.getColumnName(column)).setValue(
                    model.getValueAt(row, column));
        }
    }

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/
