package builder.test;

import org.janus.binder.BindWalker;
import org.janus.binder.gui.GuiBuilderWalker;
import org.janus.builder.BuilderWalker;
import org.janus.data.DataContext;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.JanusApplication;
import org.janus.gui.basis.JanusPage;
import org.janus.gui.basis.JanusSession;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.vaadin.StageConnector;
import org.janus.gui.vaadin.VaadinBasisConnector;
import org.janus.gui.vaadin.builder.VaadinGuiElementBuilder;
import org.jdom2.Document;

import toni.druck.xml.XMLDocumentLoader;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class TestSinglePage extends UI {
    private String pagename;

    public TestSinglePage(String pagename) {
        this.pagename = pagename;
    }

    @Override
    protected void init(VaadinRequest request) {
        // TODO Auto-generated method stub
        startSinglePage();

    }

    public void startSinglePage() {
        Document page = new XMLDocumentLoader().createDocument("pages/"
                + pagename + ".xml");
        JanusPage dict = new JanusPage("test");

        BuilderWalker walker = new TestBuilderWalker(pagename);
        walker.setDict(dict);
        walker.walkAlong(page);

        BindWalker bindWalker = new TestBinderWalker();
        bindWalker.walkAlong(page);
        bindWalker.bind(dict);

        GuiElementBuilder elementBuilder = new VaadinGuiElementBuilder();
        GuiBuilderWalker guiWalker = new GuiBuilderWalker(elementBuilder);
        guiWalker.setDict(dict);
        guiWalker.walkAlong(page);

        JanusApplication app = new JanusApplication("test");
        app.addPage(dict);
        JanusSession session = new JanusSession(app);

        DataContext context = session.createContextInSession(dict);
        for (GuiComponent c : guiWalker.getComponents()) {
            if (c != null) {
                ((VaadinBasisConnector) c).setContext(context);
            }
        }

        GuiComponent comp = guiWalker.getRoot();
        StageConnector stageConnector = (StageConnector) comp;
        setContent(stageConnector.getScene());
    }

}