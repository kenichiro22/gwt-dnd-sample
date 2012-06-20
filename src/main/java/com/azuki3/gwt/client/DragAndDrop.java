package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavCollapse;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class DragAndDrop extends Composite implements EntryPoint {

    interface DragAndDropUiBinder extends
            UiBinder<Widget, DragAndDrop> {
    }
    private static DragAndDropUiBinder uiBinder = GWT.create(DragAndDropUiBinder.class);
    @UiField
    HTMLPanel container;
    
    @UiField
    Nav nav;

    public void onModuleLoad() {
        RootPanel.get().add(this);
    }

    public DragAndDrop() {
        initWidget(uiBinder.createAndBindUi(this));

        nav.add(new NavLink("Native", "#nativeDnd"));
        
        container.add(new NativeSample());

    }
}
