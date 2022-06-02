package com.hello.world;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.PopupCloseLink;


/**
 * Trivial popup page.
 * 
 * @author Jonathan Locke
 */
public class Popup extends WebPage{
    /**
     * Constructor
     */
    public Popup()
    {
        add(new PopupCloseLink("close"));
    }
}