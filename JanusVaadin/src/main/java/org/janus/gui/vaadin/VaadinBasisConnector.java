package org.janus.gui.vaadin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.data.DataContext;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.NamedActionValue;
import org.janus.dict.helper.ID;
import org.janus.dict.interfaces.ActionListener;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.enums.GuiField;
import org.janus.gui.enums.GuiType;

import probe.ColorAWT2Fx;
import allgemein.EventActionBinderList;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;


public abstract class VaadinBasisConnector implements PropertyChangeListener,
        GuiComponent, ActionListener {
    private static final Logger LOG = LogManager
            .getLogger(VaadinBasisConnector.class);

    protected Object component;
    private Panel titleBorder = null;
    private String style;
    private int id;
    private GuiType type;
    protected NamedActionValue value;
    protected DataContext context;
    protected List<GuiComponent> childComponents = null;
    private java.awt.Font font;
    private javafx.scene.text.Font fxFont;
    private java.awt.Color background;
    private java.awt.Color foreground;
    private EventActionBinderList eventActionList;
    private float x;
    private float y;

    public VaadinBasisConnector(GuiType type, Object component) {
        super();
        id = ID.getId();
        this.type = type;
        this.component = component;
        this.childComponents = new ArrayList<>();
    }

    public Panel getTitleBorder() {
        return titleBorder;
    }

    public void setTitleBorder(Panel titleBorder) {
        this.titleBorder = titleBorder;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setField(GuiField field, Serializable value) {
        switch (field) {
        case LABEL:
            setComponentLabel((String) value);
            break;
        case BACKGROUND:
            this.background = (Color) value;
            setBackgroundColor(this.background);
            break;
        case FOREGROUND:
            this.foreground = (Color) value;
            setForegroundColor(foreground);
            break;
        case FONT:
            break;
        case ENABLED:
            getComponent().setEnabled(((Boolean) value).booleanValue());
            break;
        case VISIBLE:
            getComponent().setVisible(((Boolean) value).booleanValue());
            break;
        case FOCUS: {
            Component comp = getComponent();
            if (comp instanceof Component.Focusable) {
                if (value.equals(Boolean.TRUE)) {
                    ((Component.Focusable) getComponent()).focus();
                }
            }
        }
            break;
        case STYLE:
            setStyle((String) value);
            break;
        case TOOLTIP: {
            Component comp = getComponent();
            if (comp instanceof AbstractComponent) {
                ((AbstractComponent) getComponent()).setDescription(value
                        .toString());
            }
        }
            break;
        case WIDTH:
            getComponent().setWidth((float) value, Unit.EM);
            break;
        case HEIGHT:
            getComponent().setHeight((float) value, Unit.EM);
            break;
        case X:
            x = (float) value;
            break;
        case Y:
            y = (float) value;
            break;
        }
    }

    public void setBackgroundColor(Color background) {
        getComponent().setStyleName(ColorAWT2Fx.convert(background).getCSS());
    }

    public void setForegroundColor(Color background) {
        getComponent().setStyleName(ColorAWT2Fx.convert(background).getCSS());
    }

    private void setComponentLabel(String value) {
        if (value != null && !(component instanceof MenuItem)
                && !(component instanceof MenuBar)
                && !(component instanceof Button)
                && !(component instanceof Label)) {
            if (titleBorder == null) {
                titleBorder = new Panel(value);
                titleBorder.setContent(getComponent());
            }
        }

    }

    @Override
    public void setForeground(Color foreground) {
        setFieldInGuiThread(GuiField.FOREGROUND, foreground);
    }

    @Override
    public void setBackground(Color c) {
        setFieldInGuiThread(GuiField.BACKGROUND, c);

    }

    protected void setFieldInGuiThread(GuiField field, Serializable value) {
        setField(field, value);
    }

    @Override
    public void setGuiValue(Serializable v) {
        setGui(v);
    }

    protected void setGui(Serializable v) {
        setGuiValueWithText(v.toString());
    }

    protected void setGuiValueWithText(String v) {

    }

    protected Object getObject() {
        return component;
    }

    protected Component getComponent() {
        if (component instanceof Component) {
            return (Component) component;
        }
        return null;
    }

    protected Object getUpdateComponent() {
        return getComponent();
    }

    public void setSize(Dimension dim) {

        getComponent().setWidth((float) dim.getWidth(), Unit.EM);
        getComponent().setHeight((float) dim.getHeight(), Unit.EM);

    }

    protected Dimension getDefaultDimension() {
        return new Dimension(10, 10);
    }

    protected Component decorate() {
        setSize(getDefaultDimension());
        return getComponent();
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        setFieldInGuiThread(GuiField.FONT, font);
    }

    @Override
    public Color getForeground() {
        return foreground;
    }

    @Override
    public Color getBackground() {
        return background;
    }

    @Override
    public void setEnabled(boolean b) {
        setFieldInGuiThread(GuiField.ENABLED, b);

    }

    @Override
    public boolean isEnabled() {
        return getComponent().isEnabled();
    }

    @Override
    public void setVisible(boolean b) {
        setFieldInGuiThread(GuiField.VISIBLE, b);
    }

    @Override
    public boolean isVisible() {
        return getComponent().isVisible();
    }

    @Override
    public void setFocus(boolean b) {
        setFieldInGuiThread(GuiField.FOCUS, b);
    }

    @Override
    public boolean hasFocus() {
        return false;
    }

    @Override
    public void setStyle(String t) {

        if (style != null) {
            getComponent().removeStyleName(style);
        }

        style = t;

        if (style != null) {
            getComponent().addStyleName(style);
        }

    }

    @Override
    public String getStyle() {
        return style;
    }

    @Override
    public void setLabel(String t) {
        setFieldInGuiThread(GuiField.LABEL, t);
    }

    @Override
    public String getLabel() {
        if (titleBorder != null) {
            return titleBorder.getCaption();
        }
        return null;
    }

    @Override
    public void setTooltip(String t) {
        setFieldInGuiThread(GuiField.TOOLTIP, t);
    }

    @Override
    public String getTooltip() {
        Component comp = getComponent();
        if (comp instanceof AbstractComponent) {
            return ((AbstractComponent) getComponent()).getDescription();
        }
        return "";
    }

    @Override
    public void setWidth(float w) {
        setFieldInGuiThread(GuiField.WIDTH, new Float(w));

    }

    @Override
    public float getWidth() {
        return getComponent().getWidth();
    }

    @Override
    public void setHeight(float h) {
        setFieldInGuiThread(GuiField.HEIGHT, new Float(h));

    }

    @Override
    public float getHeight() {
        return getComponent().getHeight();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        setFieldInGuiThread(GuiField.X, new Float(x));
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        setFieldInGuiThread(GuiField.Y, new Float(y));

    }

    public void setModelValue(Serializable v) {
        value.setObject(context, v);
        performAllActions();
    }

    public Serializable getModelValue() {
        return value.getObject(context);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void validate() {
    }

    @Override
    public GuiType getGuiType() {
        return type;
    }

    public DataContext getContext() {
        return context;
    }

    public void setContext(DataContext context) {
        this.context = context;
        if (eventActionList != null) {
            eventActionList.setContext(context);
        }
        updateValue();
    }

    protected void updateValue() {
        if (value != null && context != null) {
            setGuiValue(getModelValue());
        }
    }

    public NamedActionValue getValue() {
        return value;
    }

    public void setValue(NamedActionValue value) {
        this.value = value;
        value.addActionListener(this);
        updateValue();
    }

    @Override
    abstract public Serializable getGuiValue();

    @Override
    public void addComponent(GuiComponent comp) {

        if (childComponents == null) {
            childComponents = new ArrayList<>();
        }
        childComponents.add(comp);

        LOG.debug("meine Klasse {}", this.getClass().getCanonicalName());
        if (component == null) {
            LOG.debug("component Klasse ist null");
        } else {
            LOG.debug("component Klasse "
                    + this.component.getClass().getCanonicalName());
        }
        if (comp == null) {
            LOG.debug("comp Klasse ist null");
        } else {
            LOG.debug("comp Klasse {} ", comp.getClass().getCanonicalName());
        }

        if (comp instanceof VaadinBasisConnector
                && component instanceof ComponentContainer) {
            Component c = ((VaadinBasisConnector) comp).getComponent();

            if (c == null) {
                LOG.debug("c Klasse ist null");
            } else {
                LOG.debug("c Klasse {}", c.getClass().getCanonicalName());
                ((ComponentContainer) component).addComponent(c);
            }

        }

    }

    @Override
    public List<GuiComponent> getChildComponents() {
        if (childComponents == null) {
            return Collections.emptyList();
        }
        return childComponents;
    }

    @Override
    public void actionPerformed(Object a, DataContext data) {
        context = data;
        setGuiValue(getModelValue());
    }

    public void performAllActions() {
        ActionDictionary dict = (ActionDictionary) context.getDataDescription();
        dict.perform(context);
    }

    public void setEventActionList(EventActionBinderList eventActionList) {
        Component node = getComponent();
        if (node != null && eventActionList.size() > 0) {
            this.eventActionList = eventActionList;
            eventActionList.register(node);
        }
    }

    public void setComponent(Object component) {
        this.component = component;
    }

}
