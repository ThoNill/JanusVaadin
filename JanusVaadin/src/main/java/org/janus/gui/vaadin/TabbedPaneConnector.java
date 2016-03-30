package org.janus.gui.vaadin;

import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

public class TabbedPaneConnector extends VaadinBasisConnector implements
SelectedTabChangeListener

 {
	private static final long serialVersionUID = 4293866657795647140L;

	private static final Logger LOG = LogManager.getLogger(TabbedPaneConnector.class);

	TabSheet pane;
	
	public TabbedPaneConnector(TabSheet pane) {
		super(GuiType.TABS, pane);
		this.pane = pane;
		pane.addSelectedTabChangeListener(this);
	}

	@Override
	protected void setGuiValueWithText(String text) {
		LOG.debug("set gui {}",text);
		int tabIndex = Integer.parseInt(text);
		getTabSheet().setSelectedTab(tabIndex);
	}

	private TabSheet getTabSheet() {
		return pane;
	}

	@Override
	public Serializable getGuiValue() {
		if (getTabSheet() != null) {
			return getTabSheet().getTabIndex();
		}
		return -1;
	}

	@Override
	public void addComponent(GuiComponent comp) {
		if (comp instanceof TabConnector) {
			TabConnector childConnector = (TabConnector) comp;
			pane.addComponent(childConnector.getComponent());
		}
	}

	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		if (context != null) {
			Component c = getTabSheet().getSelectedTab();
			Tab t = getTabSheet().getTab(c);
			int index = getTabSheet().getTabPosition(t);
			value.setObject(context, "" + index);
		} else {
			LOG.error("context ist null");
		}
	}


}
