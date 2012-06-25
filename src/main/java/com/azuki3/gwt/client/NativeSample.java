package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;

public class NativeSample extends AbstractSample {

    protected Label[][] label = new Label[4][4];

    public NativeSample() {
        initWidget(this.panel);

        for (int i = 0; i < label.length; i++) {
            FluidRow row = new FluidRow();
            for (int j = 0; j < label[i].length; j++) {
                Column col = new Column(12 / label[i].length);

                final String id = i + "-" + j;

                label[i][j] = new Label(id);
                label[i][j].setStyleName("dnd-label");

                final Label l = label[i][j];
                l.getElement().setDraggable(Element.DRAGGABLE_TRUE);
                l.addDragStartHandler(new DragStartHandler() {

                    public void onDragStart(DragStartEvent event) {
                        // To enable DnD , some data is needed
                        event.setData("text", id);
                        event.setData("sourceId", id);

                        appendStatus("start drag : " + id);
                    }
                });

                l.addDragOverHandler(new DragOverHandler() {

                    public void onDragOver(DragOverEvent event) {
                        if (!l.getStyleName().contains("dnd-label-over")) {
                            l.addStyleName("dnd-label-over");
                            appendStatus("drag over : " + id);
                        }
                    }
                });

                l.addDragLeaveHandler(new DragLeaveHandler() {

                    public void onDragLeave(DragLeaveEvent event) {
                        l.removeStyleName("dnd-label-over");
                        appendStatus("drag leave : " + id);

                    }
                });

                l.addDropHandler(new DropHandler() {

                    public void onDrop(DropEvent event) {
                        event.preventDefault();

                        l.removeStyleName("dnd-label-over");
                        appendStatus("drop : " + id);

                        swapLabel(l, getLabel(event.getData("sourceId")));
                    }
                });
                col.add(l);
                row.add(col);
            }
            row.getElement().getStyle().setMarginBottom(10, Unit.PX);
            panel.add(row);
        }

        if (!DragDropEventBase.isSupported()) {
            Modal alert = new Modal(false);
            alert.setTitle("Oops!");
            alert.add(new Label("Native drag and drop feature is not supported !"));
            
            
            alert.show();
        }
        panel.add(status);
    }

    
    private Label getLabel(String id) {
        String[] pos = id.split("-");
        return label[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])];
    }
}
