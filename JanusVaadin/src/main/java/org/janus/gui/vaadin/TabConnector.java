package org.janus.gui.vaadin;

import java.io.Serializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Component;

public class TabConnector extends VaadinBasisConnector implements
		ChangeListener {

	private String name;

	public TabConnector(Component tab,String name) {
		super(GuiType.TABS,tab);
		this.name = name;
		tab.setCaption(name);
				
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}

	@Override
	public Serializable getGuiValue() {
		return null;
	}

	protected String getName() {
		return name;
	}

	


}
