package io.onedev.server.web.component.milestone.closelink;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

import io.onedev.server.OneDev;
import io.onedev.server.manager.IssueManager;
import io.onedev.server.model.Milestone;
import io.onedev.server.model.support.issue.StateSpec;
import io.onedev.server.security.SecurityUtils;
import io.onedev.server.web.component.modal.ModalLink;
import io.onedev.server.web.component.modal.ModalPanel;

@SuppressWarnings("serial")
public abstract class MilestoneCloseLink extends ModalLink {

	public MilestoneCloseLink(String id) {
		super(id);
	}

	@Override
	protected Component newContent(String id, ModalPanel modal) {
		if (OneDev.getInstance(IssueManager.class).count(getMilestone(), SecurityUtils.getUser(), StateSpec.Category.OPEN) != 0) {
			return new ConfirmCloseWithOpenIssuesPanel(id) {

				@Override
				protected Milestone getMilestone() {
					return MilestoneCloseLink.this.getMilestone();
				}

				@Override
				protected void onClose(AjaxRequestTarget target) {
					modal.close();
				}

				@Override
				protected void onMilestoneClosed(AjaxRequestTarget target) {
					modal.close();
					MilestoneCloseLink.this.onMilestoneClosed(target);
				}
				
			};
		} else {
			return new ConfirmCloseWithoutOpenIssuesPanel(id) {

				@Override
				protected Milestone getMilestone() {
					return MilestoneCloseLink.this.getMilestone();
				}

				@Override
				protected void onClose(AjaxRequestTarget target) {
					modal.close();
				}

				@Override
				protected void onMilestoneClosed(AjaxRequestTarget target) {
					modal.close();
					MilestoneCloseLink.this.onMilestoneClosed(target);
				}
				
			};
		}
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new MilestoneCloseLinkCssResourceReference()));
	}

	protected abstract Milestone getMilestone();
	
	protected abstract void onMilestoneClosed(AjaxRequestTarget target);

}
