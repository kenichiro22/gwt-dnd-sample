/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azuki3.gwt.client;

import static com.google.gwt.query.client.GQuery.$;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import gwtquery.plugins.draggable.client.events.BeforeDragStartEvent;
import gwtquery.plugins.draggable.client.events.BeforeDragStartEvent.BeforeDragStartEventHandler;
import gwtquery.plugins.draggable.client.events.DragStopEvent;
import gwtquery.plugins.draggable.client.events.DragStopEvent.DragStopEventHandler;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.OutDroppableEvent;
import gwtquery.plugins.droppable.client.events.OutDroppableEvent.OutDroppableEventHandler;
import gwtquery.plugins.droppable.client.events.OverDroppableEvent;
import gwtquery.plugins.droppable.client.events.OverDroppableEvent.OverDroppableEventHandler;

/**
 *
 * @author ktana
 */
public class GwtQuerySample extends AbstractSample {

    protected Label[][] label = new Label[4][4];

    public GwtQuerySample() {
        initWidget(this.panel);

        this.panel.add(new Label("Gwtquery Drag and drop sample"));


        for (int i = 0; i < label.length; i++) {
            FluidRow row = new FluidRow();
            for (int j = 0; j < label[i].length; j++) {
                Column col = new Column(12 / label[i].length);

                DraggableLabel draggable = new DraggableLabel(i + "-" + j);
                DroppablePanel droppable = new DroppablePanel(draggable);

                col.add(droppable);
                row.add(col);
            }
            row.getElement().getStyle().setMarginBottom(10, Style.Unit.PX);
            panel.add(row);
        }
        panel.add(status);
    }

    private class DraggableLabel extends DraggableWidget<Label> implements
            BeforeDragStartEventHandler, DragStopEventHandler {

        private Label label;

        public Label getLabel() {
            return label;
        }

        public DraggableLabel(String str) {
            this.label = new Label(str);

            initWidget(label);

            this.label.getElement().setId(str);
            this.label.addStyleName("dnd-label");

            this.addBeforeDragHandler(this);
            this.addDragStopHandler(this);
        }

        /**
         * before that the drag operation starts, we will "visually" detach the
         * draggable by setting it css position to absolute.
         */
        public void onBeforeDragStart(BeforeDragStartEvent event) {
            GwtQuerySample.this.appendStatus("drag start " + event.getDraggable().getId());
            this.setDraggingOpacity(new Float(0.8));
            this.setDraggingZIndex(1000);
        }

        public void onDragStop(DragStopEvent event) {
            GwtQuerySample.this.appendStatus("drag stop " + event.getDraggable().getId());

            $(event.getDraggable()).css("position", "relative").css("top", null).css(
                    "left", null);

        }
    }

    private class DroppablePanel extends DroppableWidget<Widget> {

        private FlowPanel panel = new FlowPanel();
        private DraggableLabel draggable;

        public DroppablePanel(DraggableLabel draggable) {
            initWidget(panel);

            this.draggable = draggable;
            this.panel.add(draggable);
            this.addDropHandler(new DragAndDropHandler());
        }

        public Label getLabel() {
            return this.draggable.getLabel();
        }
    }

    public class DragAndDropHandler implements DropEventHandler,
            OverDroppableEventHandler, OutDroppableEventHandler {

        public void onDrop(DropEvent event) {
            DraggableLabel draggable = (DraggableLabel) event.getDraggableWidget();
            DroppablePanel droppable = (DroppablePanel) event.getDroppableWidget();

            swapLabel(droppable.getLabel(), draggable.getLabel());

            appendStatus("drop : " + draggable.getElement().getId());
        }

        public void onOverDroppable(OverDroppableEvent event) {
            // doesn't work ??
            GWT.log("over");
        }

        public void onOutDroppable(OutDroppableEvent event) {
            // doesn't work ??
            GWT.log("out");
        }
    }
}
