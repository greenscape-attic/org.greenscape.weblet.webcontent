package org.greenscape.weblet.webcontent.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.greenscape.core.service.Service;
import org.greenscape.web.rest.RestService;
import org.greenscape.weblet.journal.model.JournalArticle;
import org.greenscape.weblet.webcontent.WebContentDisplay;
import org.greenscape.weblet.webcontent.service.WebContentDisplayService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.log.LogService;

@Component(name = WebContentDisplayResource.FACTORY_DS, property = { Constants.SERVICE_RANKING + "=2000" })
public class WebContentDisplayResource implements RestService {
	static final String FACTORY_DS = "org.greenscape.site.rest.WebContentDisplayResource";
	private static final String MODEL_WEBCONTENTDISPLAY = "webcontentdisplay";
	private static final String MODEL_JOURNALARTICLE = "JournalArticle";
	private static final String PATH_DEF_DISPLAY_ID = "{displayId}";

	private WebContentDisplayService webcontentService;
	private Service service;

	private BundleContext context;
	private LogService logService;

	@Override
	public String getResourceName() {
		return MODEL_WEBCONTENTDISPLAY;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<WebContentDisplayParam> list(@Context UriInfo uriInfo) {
		List<WebContentDisplayParam> list = new ArrayList<>();
		List<WebContentDisplay> displayList = null;
		if (uriInfo.getQueryParameters() == null || uriInfo.getQueryParameters().size() == 0) {
			displayList = webcontentService.find();
		} else {
			displayList = webcontentService.find(uriInfo.getQueryParameters());
		}

		for (WebContentDisplay display : displayList) {
			WebContentDisplayParam param = new WebContentDisplayParam();
			param.setDisplayId(display.getModelId());
			param.setPageletId(display.getPageletId());
			JournalArticle article = service.findByModelId(MODEL_JOURNALARTICLE, display.getJournalArticleId());
			param.setArticleId(article.getModelId());
			param.setContent(article.getContent());
			param.setTitle(article.getTitle());
			param.setSiteId(article.getSiteId());
			list.add(param);
		}
		return list;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(PATH_DEF_DISPLAY_ID)
	public WebContentDisplayParam getWebContent(@PathParam("displayId") String displayId) {
		WebContentDisplayParam entity = null;
		try {
			WebContentDisplay display = webcontentService.find(displayId);
			JournalArticle article = service.findByModelId(MODEL_JOURNALARTICLE, display.getJournalArticleId());
			entity = new WebContentDisplayParam();
			entity.setArticleId(article.getModelId());
			entity.setContent(article.getContent());
			entity.setDisplayId(display.getModelId());
			entity.setPageletId(display.getPageletId());
			entity.setTitle(article.getTitle());
		} catch (Exception e) {
			logService.log(LogService.LOG_ERROR, e.getMessage(), e);
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("Server error").build());
		}
		return entity;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addWebContent(WebContentDisplayParam param) {
		WebContentDisplay entity;
		JournalArticle article = new JournalArticle();
		article.setContent(param.getContent());
		article.setSiteId(param.getSiteId());
		article.setTitle(param.getTitle());
		service.save(MODEL_JOURNALARTICLE, article);
		entity = new WebContentDisplay();
		entity.setJournalArticleId(article.getModelId());
		entity.setPageletId(param.getPageletId());
		entity = webcontentService.save(entity);
		return entity.getModelId();
	}

	@PUT
	@Path(PATH_DEF_DISPLAY_ID)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateWebContent(@PathParam("displayId") String displayId, WebContentDisplayParam param) {
		WebContentDisplay entity = webcontentService.find(displayId);
		if (entity == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND)
					.entity("No model with id " + displayId + " exists").build());
		}
		JournalArticle article = service.findByModelId(MODEL_JOURNALARTICLE, param.getArticleId());
		if (article == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND)
					.entity("No article with id " + param.getArticleId() + " exists").build());
		}
		article.setTitle(param.getTitle());
		article.setContent(param.getContent());
		article.setModifiedDate(new Date());
		service.update(MODEL_JOURNALARTICLE, article);
		return "OK";
	}

	@DELETE
	public void deleteModel() {
		webcontentService.delete();
	}

	@DELETE
	@Path(PATH_DEF_DISPLAY_ID)
	public void deleteModel(@PathParam("siteId") String id) {
	}

	@Activate
	public void activate(ComponentContext ctx, Map<String, Object> config) {
		context = ctx.getBundleContext();
	}

	@Reference(policy = ReferencePolicy.DYNAMIC)
	public void setSiteService(WebContentDisplayService siteService) {
		this.webcontentService = siteService;
	}

	public void unsetSiteService(WebContentDisplayService siteService) {
		this.webcontentService = null;
	}

	@Reference(policy = ReferencePolicy.DYNAMIC)
	public void setService(Service service) {
		this.service = service;
	}

	public void unsetService(Service service) {
		this.service = null;
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void unsetLogService(LogService logService) {
		this.logService = null;
	}

	@Override
	public String toString() {
		return getResourceName();
	}

}
