package com.hello.world;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * A explanation panel component.
 * 
 * @author Gwyn Evans
 */
class ExplainPanel extends Panel
{
    /**
     * Construct.
     * 
     * @param html
     * @param code
     */
    public ExplainPanel(String html, String code)
    {
        super("explainPanel");
        add(new MultiLineLabel("html", html));
        add(new MultiLineLabel("code", code).setEscapeModelStrings(false));
    }
}