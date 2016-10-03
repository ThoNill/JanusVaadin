package org.janus.gui.vaadin;

import java.io.Serializable;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.dict.actions.NamedActionValue;
import org.janus.gui.enums.GuiType;
import org.janus.table.DefaultExtendedTableWrapper;
import org.janus.table.ExtendedTableModel;

import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractSelect;

public abstract class VaadinTableModelConnector extends VaadinBasisConnector
        implements ValueChangeListener, ItemSetChangeListener {
    /**
	 * 
	 */
    private static final long serialVersionUID = 2284875125508532718L;

    private static final Logger LOG = LogManager
            .getLogger(VaadinTableModelConnector.class);

    private ExtendedTableModel tableModel = null;
    private int lastRow = -1;
    protected DefaultExtendedTableWrapper tableWrapper;
    private AbstractSelect items;

    public VaadinTableModelConnector(GuiType type, AbstractSelect items) {
        super(type, items);
        this.items = items;
        items.addValueChangeListener(this);
        items.addItemSetChangeListener(this);
    }

    public void SelectionChanged(int pos) {
        items.setValue(new Integer(getTableModel().getCurrentRow()));
    }

    @Override
    public void setValue(NamedActionValue value) {
        super.setValue(value);
        if (value.getAction() instanceof DefaultExtendedTableWrapper) {
            tableWrapper = (DefaultExtendedTableWrapper) value.getAction();
            tableWrapper.getCurrentRow().addActionListener(this);
        }
    }

    private int getCurrentRowInTheModel() {
        return tableWrapper.getCurrentRow(context);

    }

    void setCurrentRowInTheModel(int selectedRow) {
        try {
            tableWrapper.setCurrentRow(context, selectedRow);
            performAllActions();
        } catch (Exception ex) {
            LOG.error("Fehler", ex);
            ;

        }

    }

    @Override
    public void setGuiValue(Serializable tm) {

        if (tm instanceof ExtendedTableModel) {
            ExtendedTableModel aktuellesModel = (ExtendedTableModel) tm;
            if (tableModel == null || !tableModel.equals(aktuellesModel)) {
                tableModel = aktuellesModel;
                setModel(aktuellesModel);
                lastRow = -1;
            } else {
                fillTheValues(aktuellesModel, items.size() + 1);
            }

            int aktuelleRow = getCurrentRowInTheModel();
            if (lastRow != aktuelleRow) {
                lastRow = aktuelleRow;
                SelectionChanged(aktuelleRow);
            }
        }
        if (tm instanceof Integer) {
            int aktuelleRow = ((Integer) tm).intValue();
            if (lastRow != aktuelleRow) {
                lastRow = aktuelleRow;
                SelectionChanged(aktuelleRow);
            }
        }
    }

    public ExtendedTableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void containerItemSetChange(ItemSetChangeEvent event) {
        setSelectedRowInTableModel(items.getValue());
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Object obj = event.getProperty().getValue();
        setSelectedRowInTableModel(obj);

    }

    protected void setSelectedRowInTableModel(Object obj) {
        if (obj == null)
            return;

        LOG.debug("setSelectedRowInTableModel {} class= {}", obj, obj
                .getClass().getCanonicalName());
        LOG.debug("tableModel {} ", tableModel);
        if (tableModel != null && obj instanceof Integer) {
            int currentRow = ((Integer) obj).intValue();
            setCurrentRowInTheModel(currentRow);
        }
    }

    @Override
    public Serializable getGuiValue() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setModel(ExtendedTableModel tm) {
        try {
            if (getItems() != null && tm != null) {
                fill(tm);
                getItems().select(tm.getCurrentRow());
            }
        } catch (Exception ex) {
            LOG.error("Fehler", ex);
            ;
        }
    }

    public AbstractSelect getItems() {
        return items;
    }

    private void fill(TableModel model) {
        items.clear();
        fillTheValues(model, 0);
    }

    private void fillTheValues(TableModel model, int startRow) {
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        for (int row = startRow; row < rowCount; row++) {

            tranferValuesToItem(model, row);
        }
    }

    protected abstract void tranferValuesToItem(TableModel model, int row);

}