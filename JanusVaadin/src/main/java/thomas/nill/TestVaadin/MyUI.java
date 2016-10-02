package thomas.nill.TestVaadin;

import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("thomas.nill.TestVaadin.MyAppWidgetset")
public class MyUI extends UI {
    private static final Logger LOG = LogManager
            .getLogger(MyUI.class);

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField a = new TextField();
        a.setCaption("A:");
        
        final TextField b = new TextField();
        b.setCaption("B:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
        	int erg = getIntValue(a) * getIntValue(b);
            layout.addComponent(new Label("Thanks a * b =  " + erg
                    + ", it works!"));
        });
        
        layout.addComponents(a, button);
        layout.addComponents(b, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

	protected int getIntValue(final TextField a) {
		try {
			return Integer.parseInt(a.getValue());
		} catch (Exception ex) {
		    LOG.debug("Eingabefehler",ex);
			Notification.show("Fehler bei der Eingabe \n'" + a.getValue() + "' ist keine Zahl!",Type.ERROR_MESSAGE);
		}
		return 0;
	}

	@WebServlet(urlPatterns = "/MyUI", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
