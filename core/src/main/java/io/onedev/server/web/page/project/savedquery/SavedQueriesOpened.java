package io.onedev.server.web.page.project.savedquery;

import org.apache.wicket.ajax.AjaxRequestTarget;

import io.onedev.server.web.util.AjaxPayload;

public class SavedQueriesOpened extends AjaxPayload {

	public SavedQueriesOpened(AjaxRequestTarget target) {
		super(target);
	}

}