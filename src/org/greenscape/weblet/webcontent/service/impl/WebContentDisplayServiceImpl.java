package org.greenscape.weblet.webcontent.service.impl;

import java.util.List;
import java.util.Map;

import org.greenscape.core.service.Service;
import org.greenscape.weblet.webcontent.WebContentDisplay;
import org.greenscape.weblet.webcontent.service.WebContentDisplayService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component
public class WebContentDisplayServiceImpl implements WebContentDisplayService {
	private static final String MODEL_WEBCONTENTDISPLAY = "webcontentdisplay";
	private Service service;

	@Override
	public List<WebContentDisplay> find() {
		return service.find(MODEL_WEBCONTENTDISPLAY);
	}

	@Override
	public WebContentDisplay find(String id) {
		return service.find(MODEL_WEBCONTENTDISPLAY, id);
	}

	@Override
	public List<WebContentDisplay> find(Map<String, List<String>> properties) {
		return service.find(MODEL_WEBCONTENTDISPLAY, properties);
	}

	@Override
	public List<WebContentDisplay> find(String propertyName, Object value) {
		return service.find(MODEL_WEBCONTENTDISPLAY, propertyName, value);
	}

	@Override
	public WebContentDisplay save(WebContentDisplay model) {
		return service.save(MODEL_WEBCONTENTDISPLAY, model);
	}

	@Override
	public WebContentDisplay update(WebContentDisplay model) {
		return service.update(MODEL_WEBCONTENTDISPLAY, model);
	}

	@Override
	public void delete() {
		service.delete(MODEL_WEBCONTENTDISPLAY);
	}

	@Override
	public void delete(String id) {
		service.delete(MODEL_WEBCONTENTDISPLAY, id);
	}

	@Reference(policy = ReferencePolicy.DYNAMIC)
	public void setService(Service service) {
		this.service = service;
	}

	public void unsetService(Service service) {
		this.service = null;
	}

}
