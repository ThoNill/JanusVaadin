/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import javax.swing.table.TableModel;

import org.janus.gui.enums.GuiType;

import com.vaadin.data.Item;
import com.vaadin.ui.AbstractSelect;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class SimpleTableConnector extends VaadinTableModelConnector  {


	
	public SimpleTableConnector(GuiType type, AbstractSelect items) {
		super(type, items);
		addValueAndNameContainerProperties();
	}

	protected void addValueAndNameContainerProperties() {
		getItems().setNullSelectionAllowed(false);
		getItems().setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
	//	items.addContainerProperty("value", String.class, null);
	//	items.addContainerProperty("value", String.class, null);
		
	}

	@Override
	protected void tranferValuesToItem(TableModel model,int row) {
		Integer id = new Integer(row);
		Item item = getItems().addItem(new Integer(row));
		getItems().setItemCaption(id,model.getValueAt(row, 1).toString());
		//item.getItemProperty("value").setValue(model.getValueAt(row, 1));
	}
}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

