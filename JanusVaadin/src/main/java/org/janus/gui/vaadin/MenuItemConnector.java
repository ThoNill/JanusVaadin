/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;
import java.util.List; import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.actions.Action;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Notification;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

import allgemein.SessionInterface;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class MenuItemConnector extends VaadinBasisConnector implements Command {
    private static final Logger LOG = LogManager
            .getLogger(MenuItemConnector.class);
	Action action;
	String text;

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
	public MenuItemConnector(String text, Action action) {
		super(GuiType.MENUITEM, null);
		this.text = text;
		this.action = action;
		// item.setOnAction(this);

	}

	public MenuItem getMenuItem() {
		return (MenuItem) getComponent();
	}

	@Override
	public void setGuiValue(Serializable text) {
		if (text != null) {
			getMenuItem().setText((String) text);
		}
	}

	@Override
	public Serializable getGuiValue() {
		return getMenuItem().getText();
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		try {
			SessionInterface.performAction(action, context);
		} catch (Exception ex) {
		    LOG.error("fehler bei Perform Action",ex);
			Notification.show("Action", ex.getMessage(),
					Notification.Type.ERROR_MESSAGE);
		}
	}

	public MenuItem newItem(MenuItem parentItem) {
		MenuItem item = parentItem.addItem(text, null, this);
		setComponent(item);
		return item;
	}

	@Override
	public void addComponent(GuiComponent comp) {
		if (childComponents == null) {
			childComponents = new ArrayList<>();
		}
		childComponents.add(comp);
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

