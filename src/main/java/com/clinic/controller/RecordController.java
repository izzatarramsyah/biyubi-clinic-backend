package com.clinic.controller;

import java.util.HashMap;

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
import com.clinic.entity.CheckHealth;
import com.clinic.entity.User;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.CheckHealthService;
import com.clinic.service.UserService;
import com.clinic.service.VaccineService;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(RecordController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	CheckHealthService checkHealthService;
	
	@RequestMapping(value = "/addVaccineRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addVaccineRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap < String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < VaccineRecord > req = getRequestVaccineRecord(input);
			LOG.info("REQ::{}", req.toString());
			User user = userService.getUserByID(req.getPayload().getUserId());
			if (user == null) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Not Found");
			} else { 
				Boolean isSaved = vaccineService.addVaccineRecord(req.getPayload());
				if (isSaved == false) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Failed Saved Vacicne Record");
				} else {
					result.put("message", "Success Saved Vacicne Record");
				}
			}
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
	
	@RequestMapping(value = "/addCheckHealthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addCheckHealthRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap< String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < CheckHealth > req = getRequestCheckHealth(input);
			LOG.info("REQ::{}", req.toString());
			User user = userService.getUserByID(req.getPayload().getUserId());
			if (user == null) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Not Found");
			} else { 
				Boolean isSaved = checkHealthService.addCheckHealthRecord(req.getPayload());
				if (isSaved == false) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Failed Saved Child Record");
				} else {
					result.put("message", "Success Saved Child Record");
				}
			}
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
