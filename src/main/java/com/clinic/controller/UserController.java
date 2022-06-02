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
import com.clinic.entity.Child;
import com.clinic.entity.User;
import com.clinic.service.NotificationService;
import com.clinic.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired 
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> login(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap< String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<User> req = getRequestUser(input);
			LOG.info("REQ::{}", req.toString());
			User user = userService.checkValidUser(req.getPayload().getUsername(), req.getPayload().getPassword());
			if (user == null) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Not Found");
			} else {
				result.put("message", "Login Success");
				result.put("object", user);
				userService.updateLastActivity(user);
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
	
	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> userRegistration(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap< String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < User > req = getRequestUser(input);
			LOG.info("REQ::{}", req.toString());
			User userDb = userService.getUserByUsername(req.getPayload().getUsername());
			if (userDb != null) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Is Already Registered");
			}
			boolean isUserSaved = userService.userRegistration(req.getPayload());
			if (isUserSaved == false) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "Failed Save User");
			}  else {
				User user = userService.getUserByUsername(req.getPayload().getUsername());
				result.put("message", "Save User Success");
				result.put("object", user);
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
	
	@RequestMapping(value = "/childRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> childRegistration(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap< String, Object > result = new HashMap< String, Object > ();
		Child child = new Child ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < Child > req = getRequestChild(input);
			LOG.info("REQ::{}", req.toString());
			User user = userService.getUserByID(req.getPayload().getUserId());
			if (user != null) {
				boolean isSavedChild = userService.childRegistration(req.getPayload());
				if (isSavedChild == false) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Failed Save Child");
				} else {
					child = userService.getChildByParentID(user.getId());
					result.put("message", "Save Child Success");
					result.put("object", child);
				}
			} else {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Not Found");
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
