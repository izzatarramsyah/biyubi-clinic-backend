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
import com.clinic.entity.User;
import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.VaccineService;

@CrossOrigin
@RestController
@RequestMapping("/mst")
public class MasterConfigController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(MasterConfigController.class);

	@Autowired
	VaccineService vaccineService;
	
	@RequestMapping(value = "/addVaccineMaster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> addVaccineMaster(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap < String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < VaccineMaster > req = getRequestVaccineMaster(input);
			LOG.info("REQ::{}", req.toString());
			Boolean isSaved = vaccineService.addVaccineMaster(req.getPayload());
			if (isSaved == false) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "Failed Saved Vacicne Master");
			} else {
				result.put("message", "Success Saved Vacicne Master");
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
