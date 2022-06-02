package com.clinic.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.clinic.entity.Notification;
import com.clinic.entity.Vaccine;
import com.clinic.service.NotificationService;

@CrossOrigin
@RestController
@RequestMapping("/notif")
public class NotificationController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(NotificationController.class);

	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/listNofitication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> listNofitication(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < List < Notification > > response = new APIResponse < List < Notification > > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		List < Notification > result = new ArrayList < Notification > ();
		try{
			APIRequest<Notification> req = getRequestNotification(input);
			LOG.info("REQ::{}", req.toString());
			result = notificationService.getListNotification(req.getPayload().getUserId());
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
