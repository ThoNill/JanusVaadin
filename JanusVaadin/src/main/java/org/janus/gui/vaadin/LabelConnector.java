/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Label;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class LabelConnector extends VaadinBasisConnector {

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
	public LabelConnector(Label label) {
		super(GuiType.LABEL, label);
	//	label.setAlignmentX(Component.LEFT_ALIGNMENT);
	}

	public Label getJavaFXLabel() {
		return (Label) getComponent();
	}

	@Override
	protected void setGuiValueWithText(String text) {
		if (text != null) {
			getJavaFXLabel().setCaption(text);
		}
	}

	@Override
	public Serializable getGuiValue() {
		return getJavaFXLabel().getCaption();
	}

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

