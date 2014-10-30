package org.greenscape.weblet.webcontent;

import org.greenscape.persistence.PersistedModelBase;

public class WebContentDisplay extends PersistedModelBase implements WebContentDisplayModel {
	private static final long serialVersionUID = -2659054351083446899L;

	@Override
	public String getPageletId() {
		return (String) getProperty(PAGELET_ID);
	}

	@Override
	public WebContentDisplayModel setPageletId(String pageletId) {
		setProperty(PAGELET_ID, pageletId);
		return this;
	}

	@Override
	public String getJournalArticleId() {
		return (String) getProperty(JOURNAL_ARTICLE_ID);
	}

	@Override
	public WebContentDisplayModel setJournalArticleId(String journalArticleId) {
		setProperty(JOURNAL_ARTICLE_ID, journalArticleId);
		return this;
	}

}
