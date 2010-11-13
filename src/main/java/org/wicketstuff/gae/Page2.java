package org.wicketstuff.gae;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Page2 extends WebPage {

	public Page2(PageParameters pageParameters) {
		
		add(new Link<Void>("noop") {

			@Override
			public void onClick() {
				System.err.println("In page 2");
			}
		});
	}
	
}
