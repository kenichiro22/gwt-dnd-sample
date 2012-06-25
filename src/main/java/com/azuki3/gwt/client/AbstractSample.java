/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azuki3.gwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author ktana
 */
public abstract class AbstractSample extends Composite {

    protected HTML status = new HTML("");
    protected HTMLPanel panel = new HTMLPanel("");

    public AbstractSample() {
    }

    protected void appendStatus(String str) {
        status.setHTML(str + "<br>" + status.getHTML());
    }

    protected void swapLabel(Label l, Label sourceLabel) {
        String text = l.getText();
        l.setText(sourceLabel.getText());
        sourceLabel.setText(text);
    }
}
