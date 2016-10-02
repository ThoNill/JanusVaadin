/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List; import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.binder.gui.NeedLaterGuiBuild;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class MenuBarConnector extends VaadinBasisConnector implements
		java.awt.event.ActionListener, NeedLaterGuiBuild {
	private static final Logger LOG = LogManager.getLogger(MenuBarConnector.class);
	
	MenuBar bar;
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
	public MenuBarConnector(MenuBar bar) {
		super(GuiType.MENUBAR, bar);
		this.bar = bar;
	}

	@Override
	public Component getComponent() {
		return bar;
	};

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public Serializable getGuiValue() {
		return null;
	}

	@Override
	public void addComponent(GuiComponent comp) {
		/*
		 * 
		 * MenuItem eins = getBar().addItem("Eins", null,null); MenuItem zwei =
		 * getBar().addItem("Zwei", null,null);
		 * 
		 * eins.addItem("in eins", null,null); eins.addItem("nochmal in eins",
		 * null,null);
		 * 
		 * zwei.addItem("in zwei", null,null);
		 */

		if (childComponents == null) {
			childComponents = new ArrayList<>();
		}
		childComponents.add(comp);
	}

	@Override
	public void buildGui() {
		LOG.debug("buildGui");
		if (childComponents != null) {
			for (GuiComponent comp : childComponents) {
				if (comp instanceof MenuConnector) {
					((MenuConnector) comp).newItem(bar);
				}
			}
		}
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

