/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azuki3.gwt.client;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ktana
 */
public abstract class AbstractSample extends Composite {

    protected HTML status;
    protected Label[][] label = new Label[4][4];
    protected HTMLPanel panel = new HTMLPanel("");

    public AbstractSample() {
    }

    protected void appendStatus(String str) {
        status.setHTML(str + "<br>" + status.getHTML());
    }

    protected Label getLabel(String id) {
        String[] pos = id.split("-");
        return label[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])];
    }

    protected void init() {
        status = new HTML();

        for (int i = 0; i < label.length; i++) {
            for (int j = 0; j < label[i].length; j++) {
                final String id = i + "-" + j;

                label[i][j] = new Label(id);
                label[i][j].setStyleName("dnd-label");
            }
        }
    }
}
