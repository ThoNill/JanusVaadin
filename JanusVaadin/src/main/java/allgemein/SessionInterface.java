package allgemein;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.janus.actions.Action;
import org.janus.data.DataContext;
import org.janus.gui.basis.JanusSession;
import org.janus.gui.basis.PageContext;

public class SessionInterface  {
    private static final Logger LOG = Logger.getLogger(SessionInterface.class);

    private static JanusSession session;
    private static LinkedBlockingQueue<ActionWithContext> queue = new LinkedBlockingQueue<>();
    private static SessionInterface si = new SessionInterface();

    public static void setSession(JanusSession session) {
        SessionInterface.session = session;
    }

    public static PageContext getContext(String name) {
        int index = session.getHandle(name);
        return (PageContext) session.getObject(index);
    }

    private SessionInterface() {
        super();
    }

   
    public void run() {

        try {
            while (!queue.isEmpty()) {
                ActionWithContext a = queue.take();
                try {
                    a.actionPerformed();
                } catch (Exception ex) {
                    LOG.error("Fehler", ex);
                }
            }
        } catch (InterruptedException e) {
            LOG.error("Fehler", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void performAction(Action action, DataContext context) {
        ActionWithContext saction = new ActionWithContext(context, action);
        queue.add(saction);
        si.run();
    }

}
