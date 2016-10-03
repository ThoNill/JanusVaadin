package builder.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinServlet;

@Theme("mytheme")
@Widgetset("thomas.nill.TestVaadin.MyAppWidgetset")
public class GlueApp extends TestSinglePage {

    public GlueApp() {
        super("GluePage");

    }

    @WebServlet(urlPatterns = "/Glue", name = "GlueServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GlueApp.class, productionMode = false)
    public static class GlueServlet extends VaadinServlet {
    }

}
