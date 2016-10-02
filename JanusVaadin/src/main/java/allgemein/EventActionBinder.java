package allgemein;

import java.awt.event.ActionEvent;


import org.apache.log4j.Logger;
import org.janus.actions.Action;
import org.janus.data.DataContext;
import org.janus.dict.actions.ActionDictionary;
import org.janus.gui.enums.MouseEvents;


import com.vaadin.ui.Component;

public class EventActionBinder {
    private static final Logger LOG = Logger.getLogger(EventActionBinder.class);

	private String actionName;
	private Action action;
	private DataContext context;
	MouseEvents mouseEvent;
	JavaFXKeyEventType keyEvent = null;

	public EventActionBinder(String eventName, String actionName) {
		super();
		this.actionName = actionName;
		try {
			keyEvent = JavaFXKeyEventType.valueOf(eventName);
		} catch (Exception ex) {
		    LOG.error("can not bind KeyEvent",ex);
		}
		try {
			mouseEvent = MouseEvents.valueOf(eventName);
		} catch (Exception ex) {
		    LOG.error("can not bind MouseEvent",ex);
		}
	}

	public void register(Component node) {
		registerKeyEvent(node);

		registerMouseEvent(node);
	}

	@SuppressWarnings("serial")
	protected void registerMouseEvent(Component node) {
		if (mouseEvent != null) {

			if (mouseEvent.equals(MouseEvents.MOUSE_IN)) {
			}
			if (mouseEvent.equals(MouseEvents.MOUSE_OUT)) {
			}
			/*
			if (mouseEvent.equals(MouseEvents.MOUSE_CLICK)) {
				node.addListener(
				new com.vaadin.event.MouseEvents.ClickListener() {

					@Override
					public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
						System.out.println("Mouse click");
						doAction();
					}
					
					
				});
			}
			if (mouseEvent.equals(MouseEvents.MOUSE_DOUBLECLICK)) {
				node.addListener(
						new com.vaadin.event.MouseEvents.DoubleClickListener() {

							@Override
							public void doubleClick(com.vaadin.event.MouseEvents.DoubleClickEvent event) {
								System.out.println("Mouse click");
								doAction();
							}
							
							
						});
			}
			*/
		}
	}

	protected void registerKeyEvent(Component node) {
/*		if (keyEvent != null) {

			node.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					if (keyEvent.getKeyEvent().equals(ke.getCode())) {
						switch (keyEvent.getMask()) {
						case InputEvent.CTRL_MASK:
							if (ke.isControlDown()) {
								doAction();
							}
							break;
						case InputEvent.SHIFT_MASK:
							if (ke.isShiftDown()) {
								doAction();
							}
							break;
						default:
							doAction();
							break;
						}
					}
				}
			});
		}*/
	}

	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	protected void doAction() {
		if (action == null) {
			action = ((ActionDictionary) context.getDataDescription())
					.getAction(actionName);
		}
		SessionInterface.performAction(action, context);
	}

	public void setContext(DataContext context) {
		this.context = context;
		action = ((ActionDictionary) context.getDataDescription())
				.getAction(actionName);
	}

}
