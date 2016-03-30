package org.janus.gui.vaadin;


import com.vaadin.ui.UI;

public class CurrentThread {
	public static ThreadLocal<UI> aktualUI = new ThreadLocal<>();
}
