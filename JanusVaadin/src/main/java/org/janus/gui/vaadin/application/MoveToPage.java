package org.janus.gui.vaadin.application;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.gui.vaadin.CurrentThread;

import com.vaadin.ui.UI;

public class MoveToPage implements Serializable {
    private static final long serialVersionUID = -9086690268741572183L;

    private static final Logger LOG = LogManager.getLogger(MoveToPage.class);

    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void move() {
        UI ui = CurrentThread.aktualUI.get();
        if (ui != null) {
            ui.getNavigator().navigateTo(getPage());
        } else {
            LOG.error("UI ist null");
        }

    }

}
