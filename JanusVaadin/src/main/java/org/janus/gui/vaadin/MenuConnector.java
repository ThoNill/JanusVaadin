/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;
import java.util.Vector;

import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class MenuConnector extends VaadinBasisConnector implements Command {
	private String text;

	/**
	 * Constructor declaration
	 * 
	 * 
	 * @param node
	 * @param name
	 * @param model
	 * 
	 * @see
	 */

	public MenuConnector(String text) {
		super(GuiType.MENU, null);
		this.text = text;
	}

	public MenuItem getMenu() {
		return (MenuItem) getComponent();
	}

	@Override
	public void setGuiValue(Serializable text) {
		if (text != null) {
			getMenu().setText((String) text);
		}
	}

	@Override
	public Serializable getGuiValue() {
		return getMenu().getText();
	};

	@Override
	public void addComponent(GuiComponent comp) {
		if (childComponents == null) {
			childComponents = new Vector<>();
		}
		childComponents.add(comp);
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		// TODO Auto-generated method stub

	}

	public MenuItem newItem(MenuBar bar) {
		MenuItem item = bar.addItem(text, null,null);
		setComponent(item);
		buildSubMenu(item);
		return item;
	}
	
	public MenuItem newItem(MenuItem parentItem) {
		MenuItem item = parentItem.addItem(text, null,null);
		setComponent(item);
		buildSubMenu(item);
		return item;
	}

	protected void buildSubMenu(MenuItem item) {
		for (GuiComponent comp : childComponents) {
			if (comp instanceof MenuItemConnector) {
				((MenuItemConnector) comp).newItem(item);
			}
			if (comp instanceof MenuConnector) {
				((MenuConnector) comp).newItem(item);
			}
		}
	}
}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

