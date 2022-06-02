package com.clinic.controller;

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
import com.clinic.entity.Vaccine;
import com.clinic.service.CheckHealthService;
import com.clinic.service.NotificationService;
import com.clinic.service.VaccineService;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(RecordController.class);

	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	CheckHealthService checkHealthService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/addVaccineRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addVaccineRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Vaccine> response = new APIResponse<Vaccine> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Vaccine> req = getRequestVaccine(input);
			LOG.info("REQ::{}", req.toString());
			Boolean result = vaccineService.addVaccineRecord(req.getPayload());
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
	
	@RequestMapping(value = "/addCheckHealthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addCheckHealthRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<CheckHealth> response = new APIResponse<CheckHealth> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<CheckHealth> req = getRequestCheckHealth(input);
			LOG.info("REQ::{}", req.toString());
			Boolean result = checkHealthService.addCheckHealthRecord(req.getPayload());
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
	
}
