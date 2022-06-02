package com.hello.world;

//
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.include.Include;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.time.Duration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.WebPage;

public class HelloWorld extends WebPage {
	private String werda = "";
	private String lena = "i bims lena";
	private int onClickLinkClickCount = 0;

	public HelloWorld() {
		
		
		
		// 1 Hello World
		add(new Label("helloworld", "moin moin"));

		// 2 Erste Form, reply message
		// Ein Panel m die Nachricht anzuzeigen
		FeedbackPanel feedbackPanel1 = new FeedbackPanel("feedback1");
		add(feedbackPanel1);

		// Add a form with an onSubmit implementation that sets a message
		add(new Form("form1") {
			protected void onSubmit() {
				info("the form was submitted!");
			}
		});

		// 3 display the given input

		// Dieses Modell verweist auf die Nachrichteneigenschaft der Seite
		// und wird wird vom Label und der Formularkomponente gemeinsam genutzt

		PropertyModel<String> messageModel = new PropertyModel<>(this, "werda");

		// Formular zum Ändern der Nachricht.
		// Mit dem Formular braucht man nix weiter tun, weil das eben erwähnte
		// gemeinsame Modell "messageModel"
		// beim Absenden des Formulars automatisch aktualisiert wird.
		Form<?> form = new Form("form2");
		// mit form.add fügt man dem Formular ein Textfield hinzu
		form.add(new TextField<>("msgInput", messageModel));
		add(form);
		Label input = new Label("msg", messageModel);
		input.add(new AttributeModifier("style", "color:green;font-weight:bold;text-align:left;"));
		add(input);

		// 4 Pop-Up mit einem Link oder einem Button
		// Popup example
		PopupSettings popupSettings = new PopupSettings("popuppagemap").setHeight(400).setWidth(400);
		add(new BookmarkablePageLink<>("popupLink", Popup.class).setPopupSettings(popupSettings));
		// Popup example
		add(new BookmarkablePageLink<>("popupButtonLink", Popup.class).setPopupSettings(popupSettings));

		// 5 Link zu Google
		// External site link
		ExternalLink extLink = new ExternalLink("google", "http://www.google.com", "Click this link to go to Google");
		add(extLink);

		// And that link as a popup
		PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE | PopupSettings.SCROLLBARS)
				.setHeight(500).setWidth(700);
		add(new ExternalLink("googlePopup", "http://www.google.com", "Click this link to go to Google in a popup")
				.setPopupSettings(googlePopupSettings));

		// 6 Modifying Tag Attributs
		Label lab = new Label("testlabel", "my style was changed by a java file");
		Label lab1 = new Label("testlabel1", "my style was changed by a java file");
		Label lab2 = new Label("testlabel2", "my style was changed by a java file");
		Label lab3 = new Label("testlabel3", "my style was changed by a java file");
		Label lab4 = new Label("testlabel4", "my style was changed by a java file");
		MarkupContainer cont = new MarkupContainer("container") {
		};
		cont.add(lab);
		cont.add(lab1);
		cont.add(lab2);
		cont.add(lab3);
		cont.add(lab4);
		cont.add(new AttributeModifier("style", "color:blue;text-align:center;"));
		add(cont);

		// 6a modifizieren mit if abfrage
		PropertyModel<String> lenaModel = new PropertyModel<>(this, "lena");
		// Formular zum Ändern der Nachricht.
		// Mit dem Formular braucht man nix weiter tun, weil das eben erwähnte
		// gemeinsame Modell "messageModel"
		// beim Absenden des Formulars automatisch aktualisiert wird.
		Form<?> lenaForm = new Form("lenaForm");
		// mit form.add fügt man dem Formular ein Textfield hinzu
		lenaForm.add(new TextField<>("lenaInput", lenaModel));
		add(lenaForm);
		Label lenaInputMsg = new Label("lenaMsg", lenaModel);
		Label res = new Label("moin");
		add(lenaInputMsg);

		Label label4;
		Label label3;
		int zahl = 5;
		if (zahl == 4) {
			label4 = new Label("four", "number is 4!");
			lenaInputMsg.add(new AttributeModifier("style", "color:yellow;font-weight:bold;text-align:left;"));

		} else {
			label4 = new Label("four", "number is not 4!");

		}

		if (zahl == 3) {
			label3 = new Label("three", "number is 3!");
		} else {
			label3 = new Label("three", "number is not 3!");

		}
		add(label4);
		add(label3);

		// ################################################################################
		// 7 Download Button // #
		DownloadLink dl = new DownloadLink("downloadLink", new java.io.File("temp.tmp"));
		dl.setCacheDuration(Duration.NONE);
		dl.setDeleteAfterDownload(true);
		add(dl);
		// ###################################################

		// 8 redirect to the input url
//		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
//		add(feedbackPanel);
		add(new RedirectForm("redirectForm"));
		add(new RedirectForm("redirectForm1"));
		add(new RedirectForm("redirectForm2"));
		add(new RedirectForm("redirectForm3"));

		// 9 link with label ?
		Link<Void> anotherLinkToAnchor = new Link<Void>("anotherLinkToAnchor") {
			@Override
			public void onClick() {

			}
		};
		add(anotherLinkToAnchor);

		Component anchorLabel = new Label("anchorLabel",
				"this label is here to function as an achor for the anchor link").setOutputMarkupId(true);
		add(anchorLabel);
		anotherLinkToAnchor.setAnchor(anchorLabel);

		// 10
		// ein button den man anklicken kann, neben dran eine Anzeige wie oft er
		// angeklickt wurde
		// Action link counts link clicks on works with onclick handler
		final Link<Void> actionOnClickLink = new Link<Void>("actionOnClickLink") {
			public void onClick() {
				onClickLinkClickCount++;
			}
		};

		add(actionOnClickLink);
		add(new Label("onClickLinkClickCount", new PropertyModel<Integer>(this, "onClickLinkClickCount")));

		// onClick Funktion wird dem reset Button zugewiesen
		final Link<Void> reset = new Link<Void>("clickReset") {
			public void onClick() {
				onClickLinkClickCount = 0;
			}
		};
		// reset Button wird der HTML Seite hinzugefügt, wenn eine componente
		// (wie zb hier dieser reset button) nicht hinzugefügt wird, dann bekommt
		// man eine Fehlermeldung, dass er keine komponente mit der entsprechenden ID
		// finden kann.
		add(reset);

		Label rouven = new Label("rouven", "Rouven Rouven");
		rouven.add(new AttributeModifier("style", "color:#777777; text-align:center;"));
		add(rouven);

		// Panel
	}

	private final class RedirectForm extends Form<RedirectForm> {
		/** receives form input. */
		private String redirectUrl = "http://www.theserverside.com";

		public RedirectForm(String id) {
			super(id);
			setDefaultModel(new CompoundPropertyModel<>(this));
			add(new TextField<>("redirectUrl"));
		}

		@Override
		protected void onSubmit() {
			setResponsePage(new RedirectPage(redirectUrl));
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public void setRedirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
		}
	}
	/**
	 * @return the message
	 */
//    public String getMessage()
//    {
//        return werda;
//    }
//
//    /**
//     * @param message
//     *            the message to set
//     */
//    public void setMessage(String message)
//    {
//        this.werda = message;
//    }
}