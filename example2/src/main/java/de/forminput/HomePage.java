package de.forminput;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.forminput.login.Login;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HomePage(final PageParameters parameters) {
		super(parameters);

		WebMarkupContainer navbar = new WebMarkupContainer("navbar");

        navbar.add(new Label("label1", "label1"));
        Label label2 = new Label("label2", "label2");
//      navbar.add(new BookmarkablePageLink<>("login", Login.class));
        
        navbar.add(new BookmarkablePageLink("login", Login.class));

        add(navbar);
        add(label2);

		// TODO Add your page's components here

	}
}
