package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import java.util.HashMap;
import java.util.Map;

public class NativeSample extends Composite {

    private Map<String, Label> labels = new HashMap<String, Label>();
    private HTML status;

    HTMLPanel panel = new HTMLPanel("");

    public NativeSample() {
        initWidget(this.panel);

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


                        Label sourceLabel = labels.get(event.getData("sourceId"));

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


    private void appendStatus(String str) {
        status.setHTML(str + "<br>" + status.getHTML());
    }
}
