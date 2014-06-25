package org.greenscape.weblet.webcontent;

import org.greenscape.persistence.PersistedModel;

public interface WebContentDisplayModel extends PersistedModel {
	String MODEL_NAME = "WebContentDisplay";
	String PAGELET_ID = "pageletId";
	String JOURNAL_ARTICLE_ID = "journalArticleId";

	String getPageletId();

	WebContentDisplayModel setPageletId(String pageletId);

	String getJournalArticleId();

	WebContentDisplayModel setJournalArticleId(String journalArticleId);
}
