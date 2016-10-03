/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import org.janus.gui.enums.GuiType;

import com.vaadin.ui.OptionGroup;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class RadioConnector extends SimpleTableConnector {

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
    public RadioConnector(OptionGroup list) {
        super(GuiType.RADIO, list);
    }

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

