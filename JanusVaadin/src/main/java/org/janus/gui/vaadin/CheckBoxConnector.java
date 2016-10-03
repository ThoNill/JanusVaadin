/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package org.janus.gui.vaadin;

import java.io.Serializable;

import org.janus.gui.enums.GuiType;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class CheckBoxConnector extends VaadinBasisConnector implements
        ValueChangeListener {

    public CheckBoxConnector(CheckBox box) {
        super(GuiType.CHECK, box);
        box.addValueChangeListener(this);

    }

    @Override
    protected void setGuiValueWithText(String text) {
        boolean b = Boolean.parseBoolean(text);
        if (getCheckBox() != null) {
            getCheckBox().setValue(b);
        }
    }

    CheckBox getCheckBox() {
        return (CheckBox) getComponent();
    }

    @Override
    public Serializable getGuiValue() {
        return getCheckBox().getValue();
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        setModelValue("" + event.getProperty().getValue());
    }

}

/*--- formatting done in "Sun Java Convention" style on 11-06-2003 ---*/

