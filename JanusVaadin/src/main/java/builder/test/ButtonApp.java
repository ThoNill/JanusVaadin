package builder.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinServlet;

@Theme("mytheme")
@Widgetset("thomas.nill.TestVaadin.MyAppWidgetset")
public class ButtonApp extends TestSinglePage {

    public ButtonApp() {
        // super("TablePage");
        super("ButtonPage");
    }

    @WebServlet(urlPatterns = "/Button", name = "ButtonServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ButtonApp.class, productionMode = false)
    public static class ButtonServlet extends VaadinServlet {
    }

}
