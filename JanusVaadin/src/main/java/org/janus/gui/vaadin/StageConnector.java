package org.janus.gui.vaadin;




import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.data.DataContext;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.RootGuiComponent;
import org.janus.gui.enums.GuiType;

import com.vaadin.ui.Component;

public class StageConnector extends VaadinBasisConnector implements
		RootGuiComponent {
	private static final Logger LOG = LogManager.getLogger(StageConnector.class);
	
	
	private List<GuiComponent> components = null;

	public StageConnector(Component frame) {
		super(GuiType.FRAME, frame);
	}

	public Component getScene() {
		return getComponent();
	}

	@Override
	protected void setGuiValueWithText(String text) {
		if (text != null) {
			getComponent().setCaption(text);

		}
	}

	@Override
	protected Component getUpdateComponent() {
//		return frame.getContentPane();
		return null;
	}

	@Override
	public Serializable getGuiValue() {
		return getComponent().getCaption();
	}
	@Override
	public void addComponent(GuiComponent comp) {
		LOG.debug("add {}",comp.getClass().getCanonicalName());
		super.addComponent(comp);
	}

/*	@Override
	public void addComponent(GuiComponent comp) {
		if (comp instanceof MenuBarConnector) {
			setMenuBarConnector((MenuBarConnector) comp);
			return;
		}
		if (comp instanceof ContextMenuConnector) {
			ContextMenu contextMenu =	((ContextMenuConnector) comp).getMenu();
			
			 pane.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
			        contextMenu.show(pane, event.getScreenX(), event.getScreenY());
			        event.consume();
			    });
			    pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
				contextMenu.hide();
			});
			return;
		}
		if (comp instanceof VaadinBasisConnector) {
			VaadinBasisConnector childConnector = (VaadinBasisConnector) comp;
			pane.getChildren().add(childConnector.getNode());
		}
	}

	private void setMenuBarConnector(MenuBarConnector barConnector) {
		pane.getChildren().add(0,barConnector.getBar());
	}
*/
	@Override
	public List<GuiComponent> getAllComponents() {
		return components;
	}

	@Override
	public void setAllComponents(List<GuiComponent> components) {
		this.components = components;
	}

	@Override
	public void setContext(DataContext context) {
		super.setContext(context);
		for (GuiComponent c : components) {
			if (c != null && c != this) {
				((VaadinBasisConnector) c).setContext(context);
			}
		}
	}

}
