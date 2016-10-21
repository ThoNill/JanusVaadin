package org.janus.gui.vaadin.builder;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.StyleSheet;

import org.janus.actions.Action;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.NamedActionValue;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.TableColumnDescription;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;
import org.janus.gui.vaadin.ButtonConnector;
import org.janus.gui.vaadin.CheckBoxConnector;
import org.janus.gui.vaadin.ComboBoxConnector;
import org.janus.gui.vaadin.HBoxConnector;
import org.janus.gui.vaadin.LabelConnector;
import org.janus.gui.vaadin.ListViewConnector;
import org.janus.gui.vaadin.MenuBarConnector;
import org.janus.gui.vaadin.MenuConnector;
import org.janus.gui.vaadin.MenuItemConnector;
import org.janus.gui.vaadin.RadioConnector;
import org.janus.gui.vaadin.StageConnector;
import org.janus.gui.vaadin.TabConnector;
import org.janus.gui.vaadin.TabbedPaneConnector;
import org.janus.gui.vaadin.TableViewConnector;
import org.janus.gui.vaadin.TextInputConnector;
import org.janus.gui.vaadin.VBoxConnector;
import org.janus.gui.vaadin.VaadinBasisConnector;
import org.janus.table.DefaultExtendedTableWrapper;
import org.janus.table.DefaultTableColumn;
import org.jdom2.Element;

import allgemein.EventActionBinder;
import allgemein.EventActionBinderList;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class VaadinGuiElementBuilder implements GuiElementBuilder {
    private static StyleSheet styleSheet = new StyleSheet();

    public VaadinGuiElementBuilder() {

    }

    @Override
    public GuiComponent createGuiElement(Element elem, Action a,
            ActionDictionary dict) {
        GuiComponent comp = createGuiElementIntern(elem, a);
        EventActionBinderList list = getEventActionBinderList(elem);
        if (comp instanceof VaadinBasisConnector) {
            VaadinBasisConnector connector = (VaadinBasisConnector) comp;
            connector.setEventActionList(list);

        }
        return comp;
    }

    private GuiComponent createGuiElementIntern(Element elem, Action a) {
        GuiType type = GuiType.valueOf(elem.getName());
        switch (type) {
        case GUI:
            return new StageConnector(new VerticalLayout());
        case GLUE:
            return createGlue(elem);
        case SHOWTABLE:
            return createTableConnector(elem, a);
        case VBOX:
            return createVBoxConnector(elem);
        case HBOX:
            return createHBoxConnector(elem);
        case TEXTFIELD:
            return createTextFieldConnector(elem, a);
        case MONEYFIELD:
            return createTextFieldConnector(elem, a);
        case DATEFIELD:
            return createTextFieldConnector(elem, a);
        case LABEL:
            return createLabelConnector(elem);
        case LIST:
            return createListConnector(elem, a);
        case COMBO:
            return createComboConnector(elem, a);
        case CHECK:
            return createCheckConnector(elem, a);
        case RADIO:
            return createRadioConnector(elem, a);
        case BUTTON:
            return createButtonConnector(elem, a);
        case TAB:
            return createTabConnector(elem, a);
        case TABS:
            return createTabsConnector(elem, a);
        case MENU:
            return new MenuConnector(getTextFromElement(elem));
        case MENUBAR:
            return new MenuBarConnector(new MenuBar());
        case MENUITEM:
            return new MenuItemConnector(getTextFromElement(elem), a);
        case POPUP:
            return null; // new ContextMenuConnector(new ContextMenu());
        case FRAME:
            return null;

        }
        return null;
    }

    private GuiComponent createGlue(Element elem) {
        GuiType type = GuiType.valueOf(elem.getParentElement().getName());
        /*
         * if (elem.getChildren().size() > 0) { StackPane pane = new
         * StackPane(); VBox.setVgrow(pane, Priority.ALWAYS);
         * HBox.setHgrow(pane, Priority.ALWAYS); pane.setAlignment(Pos.CENTER);
         * return new GlueConnector(pane);
         * 
         * 
         * } else {
         * 
         * if (type.equals(GuiType.VBOX)) { Region spacer = new Region();
         * VBox.setVgrow(spacer, Priority.ALWAYS); return new
         * GlueConnector(spacer); } if (type.equals(GuiType.HBOX)) { Region
         * spacer = new Region(); HBox.setHgrow(spacer, Priority.ALWAYS); return
         * new GlueConnector(spacer); } }
         */
        return null;
    }

    private ButtonConnector createButtonConnector(Element elem, Action a) {
        Button button = new Button(getTextFromElement(elem));
        attributeSetzen(elem, button);
        return new ButtonConnector(button, a);
    }

    private HBoxConnector createHBoxConnector(Element elem) {
        HorizontalLayout panel = new HorizontalLayout();
        attributeSetzen(elem, panel);
        return new HBoxConnector(panel);
    }

    private VBoxConnector createVBoxConnector(Element elem) {
        VerticalLayout panel = new VerticalLayout();
        attributeSetzen(elem, panel);
        return new VBoxConnector(panel);
    }

    private LabelConnector createLabelConnector(Element elem) {
        Label label = new Label(getTextFromElement(elem));
        attributeSetzen(elem, label);
        return new LabelConnector(label);
    }

    private GuiComponent createTabConnector(Element elem, Action a) {
        Component panel = new VerticalLayout();
        attributeSetzen(elem, panel);
        TabConnector gui = new TabConnector(panel,
                elem.getAttributeValue("name"));
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createTabsConnector(Element elem, Action a) {
        TabSheet pane = new TabSheet();
        attributeSetzen(elem, pane);
        TabbedPaneConnector gui = new TabbedPaneConnector(pane);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createTableConnector(Element elem, Action a) {
        List<TableColumnDescription> columnDescriptions = new ArrayList<>();
        boolean withColumns = false;
        for (Element e : elem.getChildren()) {
            if ("COLUMN".equals(e.getName())) {
                withColumns = true;
                columnDescriptions.add(new TableColumnDescription(e
                        .getAttributeValue("renderer"), e
                        .getAttributeValue("header"), e
                        .getAttributeValue("name")));
            }
        }
        if (!withColumns && a instanceof DefaultExtendedTableWrapper) {
            DefaultExtendedTableWrapper wrapper = (DefaultExtendedTableWrapper) a;
            for (DefaultTableColumn c : wrapper.getColumns()) {
                columnDescriptions.add(new TableColumnDescription(c.getFormat()
                        .getClass().getName(), c.getColumnName(), c
                        .getColumnName()));
            }
        }
        Table table = new Table();
        attributeSetzen(elem, table);
        TableViewConnector gui = new TableViewConnector(table,
                columnDescriptions);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createRadioConnector(Element elem, Action a) {
        OptionGroup group = new OptionGroup();
        attributeSetzen(elem, group);
        RadioConnector gui = new RadioConnector(group);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createCheckConnector(Element elem, Action a) {
        CheckBox box = new CheckBox();
        attributeSetzen(elem, box);
        CheckBoxConnector gui = new CheckBoxConnector(box);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createComboConnector(Element elem, Action a) {
        ComboBox combo = new ComboBox();
        attributeSetzen(elem, combo);
        ComboBoxConnector gui = new ComboBoxConnector(combo);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createListConnector(Element elem, Action a) {
        ListSelect list = new ListSelect();
        attributeSetzen(elem, list);
        ListViewConnector gui = new ListViewConnector(list);
        setValue(gui, a);
        return gui;
    }

    private GuiComponent createTextFieldConnector(Element elem, Action a) {
        TextField field = new TextField();
        /*
         * field.setAlignmentX(Component.LEFT_ALIGNMENT);
         * 
         * Font f = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);
         * field.setFont(f); FontMetrics fm = field.getFontMetrics(f); int h =
         * (int) (fm.getHeight() * 1.4f); int w = fm.charWidth('X') * 10;
         * Dimension dim = new Dimension(w, h); field.setSize(dim);
         * field.setPreferredSize(dim); field.setMaximumSize(dim);
         * field.setMinimumSize(dim);
         * 
         * attributeSetzen(elem, field);
         */
        TextInputConnector gui = new TextInputConnector(field);
        setValue(gui, a);
        return gui;
    }

    private void setValue(VaadinBasisConnector gui, Action a) {
        if (a instanceof NamedActionValue) {
            gui.setValue(((NamedActionValue) a));
        }
    }

    private String getTextFromElement(Element elem) {
        String t = elem.getAttributeValue("text");
        if (t == null) {
            t = "";
        }
        return t;
    }

    private void attributeSetzen(Element elem, Component comp) {
        setAttribut(elem, GuiField.FOREGROUND,
                (String value) -> comp.setStyleName(value));
        setAttribut(elem, GuiField.BACKGROUND,
                (String value) -> comp.setStyleName("bg" + value));
        /*
         * comp.setAlignmentX(Component.LEFT_ALIGNMENT);
         * 
         * setAttribut(elem, GuiField.FOREGROUND, (String value) ->
         * comp.setForeground(styleSheet .stringToColor(value)));
         * setAttribut(elem, GuiField.BACKGROUND, (String value) ->
         * comp.setBackground(styleSheet .stringToColor(value)));
         * setAttribut(elem, GuiField.FONT, (String value) ->
         * comp.setFont(Font.decode((value))));
         */
    }

    private void setAttribut(Element elem, GuiField field,
            VaadinAttributSetter setter) {
        String xmlValue = elem.getAttributeValue(field.name().toLowerCase());
        if (xmlValue != null) {
            setter.setAttribut(xmlValue);
        }
    }

    private EventActionBinderList getEventActionBinderList(Element elem) {
        EventActionBinderList list = new EventActionBinderList();
        for (Element e : elem.getChildren()) {
            if ("EVENT".equals(e.getName())) {
                EventActionBinder binder = new EventActionBinder(
                        e.getAttributeValue("name"),
                        e.getAttributeValue("action"));
                list.addElement(binder);
            }
        }
        return list;
    }

}
