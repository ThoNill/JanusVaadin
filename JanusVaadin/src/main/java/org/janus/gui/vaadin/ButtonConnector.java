/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.actions.Action;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import allgemein.SessionInterface;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class ButtonConnector extends VaadinBasisConnector implements
		Button.ClickListener {
	private static final Logger LOG = LogManager
			.getLogger(ButtonConnector.class);

	private static final long serialVersionUID = 7853650935411181004L;
	private Action action;

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
	public ButtonConnector(Button box, Action action) {
		super(GuiType.BUTTON, box);
		this.action = action;
		box.addClickListener(this);
	}

	public Button getButton() {
		return (Button) getComponent();
	}

	@Override
	public void setField(GuiField field, Serializable value) {

		switch (field) {
		case LABEL:
			getButton().setCaption((String) value);
			break;
		default:
			super.setField(field, value);
			break;
		}
	}

	@Override
	public void setGuiValueWithText(String text) {
		getButton().setCaption(text);
	}

	@Override
	public Serializable getGuiValue() {
		return "";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		LOG.debug("Button pressed {}", event);
		try {
			merkeUIimThread();
			SessionInterface.performAction(action, context);
		} catch (Exception ex) {
			Notification.show("Action", ex.getMessage(),
					Notification.Type.ERROR_MESSAGE);
		}
	}

	protected void merkeUIimThread() {
		UI ui = getButton().getUI();
		if (ui != null) {
			CurrentThread.aktualUI.set(getButton().getUI());
		} else {
			LOG.error("UI ist null");
		}
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

