package com.clinic.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.clinic.entity.MstVaccine;
import com.clinic.service.ArticleService;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> addMedicine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{ 
			APIRequest<Article> req = getRequestArticle(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = articleService.insertArticle(req.getPayload());
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
	
	@RequestMapping(value = "/getListArticle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getListArticle() {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			Map<String, Object> result = articleService.getListArticle();
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
	public APIResponse<Map<String, Object>> getArticle(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{ 
			APIRequest<Article> req = getRequestArticle(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = articleService.getArticle(req.getPayload().getId());
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
