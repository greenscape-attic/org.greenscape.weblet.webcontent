package org.greenscape.weblet.webcontent.service.impl;

import java.util.List;
import java.util.Map;

import org.greenscape.core.service.Service;
import org.greenscape.persistence.DocumentModel;
import org.greenscape.weblet.webcontent.WebContentDisplay;
import org.greenscape.weblet.webcontent.service.WebContentDisplayService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class WebContentDisplayServiceImpl implements WebContentDisplayService {
	private Service service;

	@Override
	public <M extends DocumentModel> List<M> find(Class<? extends DocumentModel> clazz) {
		return service.find(clazz);
	}

	@Override
	public <M extends DocumentModel> M find(Class<? extends DocumentModel> clazz, String id) {
		return service.find(clazz, id);
	}

	@Override
	public <M extends DocumentModel> List<M> find(Class<? extends DocumentModel> clazz,
			Map<String, List<String>> properties) {
		return service.find(clazz, properties);
	}

	@Override
	public <M extends DocumentModel> List<M> find(Class<? extends DocumentModel> clazz, String propertyName,
			Object value) {
		return service.find(clazz, propertyName, value);
	}

	@Override
	public WebContentDisplay findByWebContentDisplayId(String displayId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByWebContentDisplayId(String displayId) {
		// TODO Auto-generated method stub

	}

	@Override
	public <M extends DocumentModel> M save(M model) {
		return service.save(model);
	}

	@Override
	public <M extends DocumentModel> M update(M model) {
		return service.update(model);
	}

	@Override
	public void delete(Class<? extends DocumentModel> clazz) {
		service.delete(clazz);
	}

	@Override
	public void delete(Class<? extends DocumentModel> clazz, String id) {
		service.delete(clazz, id);
	}

	@Reference
	public void setService(Service service) {
		this.service = service;
	}

	public void unsetService(Service service) {
		this.service = null;
	}

}
