package allgemein;

import java.util.List; import java.util.ArrayList;

import org.janus.data.DataContext;

import com.vaadin.ui.Component;

public class EventActionBinderList {
	List<EventActionBinder> list = new ArrayList<>();

	public EventActionBinderList() {
		
	}

	public void addElement(EventActionBinder obj) {
		list.add(obj);
	}

	public void register(Component component) {
		for( EventActionBinder b : list) {
			b.register(component);
		}
	}
	
	public void setContext(DataContext context) {
		for( EventActionBinder b : list) {
			b.setContext(context);
		}
	}

	public int size() {
		return list.size();
	}
	
}
