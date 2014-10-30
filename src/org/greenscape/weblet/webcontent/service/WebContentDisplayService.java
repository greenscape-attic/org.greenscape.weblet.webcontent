package org.greenscape.weblet.webcontent.service;

import java.util.List;
import java.util.Map;

import org.greenscape.weblet.webcontent.WebContentDisplay;

public interface WebContentDisplayService {

	List<WebContentDisplay> find();

	WebContentDisplay find(String id);

	List<WebContentDisplay> find(Map<String, List<String>> properties);

	List<WebContentDisplay> find(String propertyName, Object value);

	WebContentDisplay save(WebContentDisplay model);

	WebContentDisplay update(WebContentDisplay model);

	void delete(String modelName);

	void delete();

}
