package org.janus.gui.vaadin;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.gui.enums.GuiType;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.TextField;

public class TextInputConnector extends VaadinBasisConnector {
    private static final Logger LOG = LogManager
            .getLogger(TextInputConnector.class);
    private Color lastColor;
    private String lastValue;
    private boolean alreadyHasFocus = false;

    public TextInputConnector(TextField textField) {
        super(GuiType.TEXTFIELD, textField);
        textField.setValue("");
        textField.setImmediate(true);

        textField.addShortcutListener(new ShortcutListener("Shortcut Name",
                ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                setModelValue();
            }
        });

        /*
         * textField.setOnAction(this); textField.setOnKeyPressed(new
         * EventHandler<KeyEvent>() {
         * 
         * @Override public void handle(KeyEvent key) { if
         * (KeyEventType.CTRL_D.name().equals(key)) { resetToOldValue(); } if
         * (KeyEventType.COPY.name().equals(key)) {
         * setTextToClipboard(getTextfield().getText()); } if
         * (KeyEventType.PAST.name().equals(key)) {
         * getTextfield().setText(getTextFromClipboard()); }
         * 
         * } }); textField.focusedProperty().addListener(this);
         */
        setGuiValueWithText("");
    }

    public TextField getTextfield() {
        return (TextField) getComponent();
    }

    protected void setModelValue() {
        Serializable obj = getTextfield().getValue();
        setModelValue((obj == null) ? "" : obj);
    }

    @Override
    protected void setGuiValueWithText(String text) {
        if (text != null && getTextfield() != null) {
            getTextfield().setValue(text);
        }
    }

    @Override
    protected void updateValue() {
        if (value != null && context != null) {
            Serializable s = getModelValue();
            setGuiValue(s);
        }
    }

    /*
     * @Override public void changed(ObservableValue<? extends Boolean>
     * oldValue, Boolean newValue, Boolean arg2) { if (hasFocus()) {
     * focusGained(); } else { focusLost(); }
     * 
     * }
     */

    public void focusGained() {
        if (!alreadyHasFocus) {
            alreadyHasFocus = true;
            lastColor = getBackground();
            setBackgroundColor(Color.yellow);
        }
    }

    public void focusLost() {
        if (alreadyHasFocus) {
            alreadyHasFocus = false;
            setBackground(lastColor);
            safeOldValue();
            setModelValue();
        }
    }

    public void safeOldValue() {
        lastValue = getTextfield().getValue();
    }

    public void resetToOldValue() {
        getTextfield().setValue(lastValue);
    }

    public static void setTextToClipboard(String text) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection contents = new StringSelection(text);
        cb.setContents(contents, null);
    }

    public static String getTextFromClipboard() {
        String s = "";

        try {
            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable content = cb.getContents(null);
            s = (String) content.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
            LOG.error("Fehler bei hole vom Clipboard", ex);
        }
        return s;
    }

    @Override
    public Serializable getGuiValue() {
        return getTextfield().getValue();
    }

}
