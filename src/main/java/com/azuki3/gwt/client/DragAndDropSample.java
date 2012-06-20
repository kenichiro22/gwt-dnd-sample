package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;
import java.util.HashMap;
import java.util.Map;

public class DragAndDropSample extends Composite {

    private static DragAndDropSampleUiBinder uiBinder = GWT.create(DragAndDropSampleUiBinder.class);

    interface DragAndDropSampleUiBinder extends
            UiBinder<Widget, DragAndDropSample> {
    }
    @UiField
    HTMLPanel container;
    private Map<String, Label> labels = new HashMap<String, Label>();
    private HTML status;

    public DragAndDropSample() {
        initWidget(uiBinder.createAndBindUi(this));

        status = new HTML();

        for (int i = 0; i < 4; i++) {
            FluidRow row = new FluidRow();
            for (int j = 0; j < 4; j++) {
                final String id = i + "-" + j;

                Column col = new Column(3);

                final Label l = new Label(id);
                l.addStyleName("dnd-label");
                l.getElement().setDraggable(Element.DRAGGABLE_TRUE);

                l.addDragStartHandler(new DragStartHandler() {

                    public void onDragStart(DragStartEvent event) {
                        // To enable DnD , some data is needed
                        event.setData("text", "Hello!");
                        
                        
//                        appendStatus("start drag: " + id);
                    }
                });

//                l.addDragEnterHandler(new DragEnterHandler() {
//
//                    public void onDragEnter(DragEnterEvent event) {
//                        l.addStyleName("dnd-label-over");
//
//                        appendStatus("drag enter: " + id);
//
//                    }
//                });
                
                l.addDragLeaveHandler(new DragLeaveHandler() {

                    public void onDragLeave(DragLeaveEvent event) {
                        l.removeStyleName("dnd-label-over");
                        appendStatus("drag leave: " + id);
                    }
                });

                l.addDropHandler(new DropHandler() {

                    public void onDrop(DropEvent event) {
                        event.preventDefault();

//                        appendStatus("drop: " + id);
//
//                        l.removeStyleName("dnd-label-over");

                        Label sourceLabel = labels.get(event.getData("text"));
                        
                        String text = l.getText();
                        l.setText(sourceLabel.getText());
                        sourceLabel.setText(text);
                    }
                });

                labels.put(id, l);
                col.add(l);
                row.add(col);
            }
            row.getElement().getStyle().setMarginBottom(10, Unit.PX);
            container.add(row);
            container.add(status);
        }

    }

    private void appendStatus(String string) {

//        status.setHTML(string + "<br/>" + status.getHTML());
    }
}
