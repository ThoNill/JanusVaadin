/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.ComboBox;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class ComboBoxConnector extends SimpleTableConnector {

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
    public ComboBoxConnector(ComboBox list) {
        super(GuiType.COMBO, list);
    }

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

