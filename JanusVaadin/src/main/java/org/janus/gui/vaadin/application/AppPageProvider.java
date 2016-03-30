package org.janus.gui.vaadin.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.appbuilder.AppBuilder;
import org.janus.gui.basis.JanusApplication;
import org.janus.gui.basis.JanusPage;
import org.janus.gui.basis.JanusSession;
import org.janus.gui.basis.PageContext;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.vaadin.StageConnector;
import org.janus.gui.vaadin.builder.VaadinGuiElementBuilder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

public class AppPageProvider implements ViewProvider {
	private static final Logger LOG = LogManager.getLogger(AppPageProvider.class);
	
	private JanusApplication app;
	private JanusSession session;

	public AppPageProvider(String appName) throws Exception {
		LOG.debug("AppPage für Anwendung {}",appName);
		GuiElementBuilder elementBuilder = new VaadinGuiElementBuilder();
		AppBuilder builder = new AppBuilder(elementBuilder);
		builder.setPageListe("data ButtonPage TablePage");
		app = builder.getApplication(appName);
		session = app.newContext();
		LOG.debug("initialisiert");
	}

	@Override
	public String getViewName(String viewAndParameters) {
		LOG.debug("getViewName {}",viewAndParameters);
		int posQuestionMark = viewAndParameters.indexOf('?');
		String beforeQuestionMark = (posQuestionMark < 0) ? viewAndParameters
				: viewAndParameters.substring(0, posQuestionMark);
		int posDiv = beforeQuestionMark.lastIndexOf('/');
		String viewName =  (posDiv < 0) ? beforeQuestionMark : beforeQuestionMark
				.substring(posDiv);
		LOG.debug("getViewName Ergebnis {}",viewName);
		return viewName;
	}

	@Override
	public View getView(String viewName) {
		LOG.debug("hole View {}",viewName);
		JanusPage login = session.searchPage(viewName);
		PageContext context = session.getPageContext(login);
		StageConnector frameConnector = (StageConnector) login.getGui();
		frameConnector.setContext(context);
		View view = new AppPage(session, frameConnector.getScene());
		LOG.debug("View geholt");
		return view;
	}

}
