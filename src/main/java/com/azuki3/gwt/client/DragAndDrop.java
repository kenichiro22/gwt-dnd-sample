package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class DragAndDrop extends Composite implements EntryPoint, ValueChangeHandler<String> {

    public static final String TOKEN_GWT_QUERY = "gwtQuery";
    public static final String TOKEN_NATIVE_DND = "nativeDnd";

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

        History.addValueChangeHandler(this);

        // 初期状態での履歴トークンを処理する
        if (!History.getToken().isEmpty()) {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        if (container.getWidgetCount() > 0) {
            container.remove(0);
        }
        GWT.log(event.getValue());
        if (TOKEN_NATIVE_DND.equals(event.getValue())) {
            container.add(new NativeSample());
        } else if (TOKEN_GWT_QUERY.equals(event.getValue())) {
            container.add(new GwtQuerySample());
        }
    }

    public DragAndDrop() {
        initWidget(uiBinder.createAndBindUi(this));

        nav.add(new NavLink("Native", "#" + TOKEN_NATIVE_DND));
        nav.add(new NavLink("GWT Query", "#" + TOKEN_GWT_QUERY));
    }
}
