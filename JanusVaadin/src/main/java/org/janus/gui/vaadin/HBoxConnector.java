/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;

import javafx.scene.layout.HBox;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.HorizontalLayout;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class HBoxConnector extends VaadinBasisConnector {

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
    public HBoxConnector(HorizontalLayout panel) {
        super(GuiType.HBOX, panel);
        // panel.setAlignment(Pos.CENTER_LEFT);

    }

    public HBox getBox() {
        return (HBox) getComponent();
    }

    @Override
    protected void setGuiValueWithText(String text) {

    }

    @Override
    public Serializable getGuiValue() {
        return "";
    }

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

