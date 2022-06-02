package de.forminput.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;

import de.forminput.HomePage;
import de.forminput.resources.BootstrapCssResourceReference;
import de.forminput.resources.DefaultTheme;

@SuppressWarnings("serial")
public class Login extends WebPage {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final TextField<String> username = new TextField("username", Model.of());
	private final PasswordTextField password = new PasswordTextField("password", Model.of());
	private final SubmitLink loginButton = new SubmitLink("login") {
		@SuppressWarnings("serial")
		@Override
		public void onSubmit() {
			super.onSubmit();
			final String username = Login.this.username.getModelObject();
			if ("42".equals(username) && password.getModelObject().equals("42")) {
				// ((SgSession) Session.get()).loginUser(username);
				Login.this.setResponsePage(HomePage.class);
			} else {
				final ModalDialog modal = new ModalDialog("modal");
				modal.closeOnClick();
				Label label = new Label(ModalDialog.CONTENT_ID, "I'm a modal dialog!");

				modal.setContent(label);

				add(modal);
				add(new AjaxLink<Void>("open") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						modal.open(target);
					}
				});

			}

		}

	};
	@SuppressWarnings("serial")
	public Login() {
		final ModalDialog modal = new ModalDialog("modal");
//        modal.add(new DefaultTheme());
		modal.closeOnClick();
		Label label = new Label(ModalDialog.CONTENT_ID, "I'm a modal dialog!");

		modal.setContent(label);

		add(modal);
		add(new AjaxLink<Void>("open") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.open(target);
			}
		});
	}
	private Form<Void> form = new Form<>("form");

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultTheme.get()));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(form);
		form.add(username);
		form.add(password);
		form.add(loginButton);
	}
}
