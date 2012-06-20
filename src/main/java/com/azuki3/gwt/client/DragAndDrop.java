package com.azuki3.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class DragAndDrop implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get().add(new DragAndDropSample());
	}

}
