package com.clinic.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.api.object.HeaderResponse;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.constant.StatusCode;
import com.clinic.entity.Article;
import com.clinic.service.ArticleService;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addMedicine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < Article > response = new APIResponse < Article > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{ 
			APIRequest < Article > req = getRequestArticle(input);
			LOG.info("REQ::{}", req.toString());
			boolean result = articleService.insertArticle(req.getPayload());
			if (result == false) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
			}
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/getListArticle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> getListArticle() {
		LOG.traceEntry();
		APIResponse < List<Article> > response = new APIResponse < List<Article> > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		List < Article > result = new ArrayList < Article > ();
		try{
			result = articleService.getListArticle();
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}

	@RequestMapping(value = "/getArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> getArticle(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < Article > response = new APIResponse < Article > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Article result = new Article ();
		try{ 
			APIRequest < Article > req = getRequestArticle(input);
			LOG.info("REQ::{}", req.toString());
			result = articleService.getArticle(req.getPayload().getId());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
}
