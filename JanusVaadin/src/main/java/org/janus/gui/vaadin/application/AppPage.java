package org.janus.gui.vaadin.application;

import org.janus.gui.basis.JanusSession;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class AppPage extends VerticalLayout implements View {

    public AppPage(JanusSession session, Component comp) {
        super();
        this.session = session;

        addComponent(comp);
    }

    private static final long serialVersionUID = 6771810939480304498L;
    private JanusSession session;

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
