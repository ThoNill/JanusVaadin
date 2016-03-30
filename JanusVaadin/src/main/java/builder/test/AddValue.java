package builder.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.janus.actions.Action;
import org.janus.data.DataContext;
import org.janus.data.DataDescription;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.NamedActionValue;
import org.janus.gui.actions.ElementConfigurable;
import org.jdom2.Element;

public class AddValue implements Action, ElementConfigurable {
	
	private static final Logger LOG = LogManager.getLogger(AddValue.class);

	private int step;
	private NamedActionValue value;
	private String valueName;
	
	public AddValue() {
		super();
	}

	
	@Override
	public void configure(DataDescription description) {
	}

	@Override
	public void perform(DataContext context) {
		LOG.debug("Aufruf");
		if (context == null) {
			LOG.debug("context = null");
			return;
		}
		if (value == null) {
			LOG.debug("Hole Value {}",valueName);
			ActionDictionary dict = (ActionDictionary)context.getDataDescription();
			value = dict.getAction(valueName);
		}
		Object obj = value.getObject(context);
		LOG.debug("Value= {}",obj);
		if (obj == null || "".equals(obj)) {
			obj = "0";
		}
		int a = Integer.parseInt(obj.toString()) + step;
		LOG.debug("Nach Step Value= {}",a);
		value.setObject(context, a);
		
	}


	@Override
	public void configure(Element elem) {
		step = getInt(elem,"step",1);
		valueName = getString(elem,"value","");
	}

}
