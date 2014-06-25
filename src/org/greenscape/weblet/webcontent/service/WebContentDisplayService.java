package org.greenscape.weblet.webcontent.service;

import org.greenscape.core.service.Service;
import org.greenscape.weblet.webcontent.WebContentDisplay;

public interface WebContentDisplayService extends Service {
	WebContentDisplay findByWebContentDisplayId(String displayId);

	void deleteByWebContentDisplayId(String displayId);

}
