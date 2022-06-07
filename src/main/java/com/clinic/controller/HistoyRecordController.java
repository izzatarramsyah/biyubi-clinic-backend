package com.clinic.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.clinic.api.object.VaccineHistory;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.constant.StatusCode;
import com.clinic.entity.CheckHealth;
import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.CheckHealthService;
import com.clinic.service.VaccineService;

@CrossOrigin
@RestController
@RequestMapping("/historyRecord")
public class HistoyRecordController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(HistoyRecordController.class);
	
	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	CheckHealthService checkHealthService;
	
	@RequestMapping(value = "/listVaccineRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> listVaccineRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap < String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < VaccineRecord > req = getRequestVaccineRecord(input);
			LOG.info("REQ::{}", req.toString());
			List < VaccineRecord > vaccineRecord = vaccineService.getListVaccine(req.getPayload().getUserId());
			List < VaccineHistory > listVaccineHistory = new ArrayList < VaccineHistory> ();
			for (VaccineRecord l : vaccineRecord) {
				VaccineHistory history = new VaccineHistory ();
				VaccineMaster mstVaccine = vaccineService.getMstVaccineByCode(l.getVaccineCode());
				history.setDate(l.getVaccineDate());
				history.setName(mstVaccine.getName());
				history.setType(mstVaccine.getType());
				history.setBatch(l.getBatch());
				history.setNextVaccineDate(l.getNextVaccineDate());
				history.setNotes(l.getNotes());
				history.setExpDate(l.getExpiredDate());
				listVaccineHistory.add(history);
			}
			result.put("Object", listVaccineHistory);
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
	
	@RequestMapping(value = "/listCheckHealthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> listCheckHealthRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<CheckHealth> response = new APIResponse<CheckHealth> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<CheckHealth> req = getRequestCheckHealth(input);
			LOG.info("REQ::{}", req.toString());
			List < CheckHealth > result = checkHealthService.getListVaccine(req.getPayload().getUserId());
			if (result.size() == 0) {
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
