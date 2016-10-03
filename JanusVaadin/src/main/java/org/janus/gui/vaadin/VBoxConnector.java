/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;

import javafx.scene.layout.VBox;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.VerticalLayout;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class VBoxConnector extends VaadinBasisConnector {

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
    public VBoxConnector(VerticalLayout panel) {
        super(GuiType.VBOX, panel);
        // panel.setAlignment(Pos.BOTTOM_LEFT);
    }

    public VBox getBox() {
        return (VBox) getComponent();
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

