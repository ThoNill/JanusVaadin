package builder.test;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;
import org.janus.gui.vaadin.application.AppPageProvider;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
	 *
	 */
@Theme("mytheme")
@Widgetset("thomas.nill.TestVaadin.MyAppWidgetset")
public class TestVaadinApp extends UI {
    private static final Logger LOG = Logger.getLogger(TestVaadinApp.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            Navigator navigator = new Navigator(this, this);
            navigator.addProvider(new AppPageProvider("testapp"));
            String path = vaadinRequest.getContextPath();
            if (path == null || "".equals(path)) {
                path = "login";
            }
            // CurrentThread.aktualUI.set(getUI());
            navigator.navigateTo(path);

        } catch (Exception e) {
            LOG.error("Fehler", e);
            ;
        }

    }

    @WebServlet(urlPatterns = "/*", name = "TestVaadinAppServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TestVaadinApp.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
