package allgemein;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.janus.actions.Action;
import org.janus.data.DataContext;

public class CentralActionPerformingQueue extends
        LinkedBlockingQueue<ActionWithContext> implements Runnable {
  
    private static final long serialVersionUID = 3608192141516920906L;

    private static final Logger LOG = Logger
            .getLogger(CentralActionPerformingQueue.class);

    private static CentralActionPerformingQueue queue = new CentralActionPerformingQueue();

    private CentralActionPerformingQueue() {
        super();
    }

    public void run() {

        try {
            while (!isEmpty()) {
                ActionWithContext a = take();
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
        try {
            queue.put(saction);
            queue.run();
        } catch (InterruptedException e) {
            LOG.error("Fehler in put", e);
        }
    }

}
