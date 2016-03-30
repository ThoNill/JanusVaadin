package builder.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinServlet;


@Theme("mytheme")
@Widgetset("thomas.nill.TestVaadin.MyAppWidgetset")
public class TabelleApp extends TestSinglePage{

	public TabelleApp() {
		super("TablePage");
	}

	@WebServlet(urlPatterns = "/Tabelle", name = "TabelleServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = TabelleApp.class, productionMode = false)
	public static class TabelleServlet extends VaadinServlet {
	}

	
}
