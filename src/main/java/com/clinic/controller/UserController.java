package com.clinic.controller;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.clinic.service.MailService;
import com.clinic.service.UserService;
import com.clinic.util.MailHelper;
import com.clinic.util.Security;
import com.clinic.util.Util;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired 
	UserService userService;
	
	@Autowired
	MailService mailService;
	
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
			Boolean isValid = userService.checkValidUser(req.getPayload().getUsername(), req.getPayload().getPassword());
			if (!isValid) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Is Not Valid");
			} else {
				User user = userService.getUserByUsername(req.getPayload().getUsername());
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
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> forgotPassword(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse < HashMap<String, Object> > response = new APIResponse < HashMap<String, Object> > ();
		HashMap < String, Object > result = new HashMap< String, Object > ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest < User > req = getRequestUser(input);
			LOG.info("REQ::{}", req.toString());
			User user = userService.getUserByUsername(req.getPayload().getUsername());
			if (user == null) {
				statusTrx = StatusCode.INVALID;
				responseMsg = StatusCode.INVALID.toString();
				result.put("message", "User Not Found");
			} else {
				if (!user.getEmail().equals(req.getPayload().getEmail())) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Email not match");
				} else if (!user.getPhone_no().equals(req.getPayload().getPhone_no())) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Phone number not match");
				} else {
					String password = Security.decrypt(user.getPassword());
					String message = MailHelper.forgotPasswordMessage(user.getFullname(), user.getUsername(), password);
					mailService.sendEmail(user.getEmail(), "Lupa password Biyubi App", message, "");
					result.put("message", "Send password Success");
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
			} else {
				if (userService.getUserByEmail(req.getPayload().getEmail()) != null) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Email Is Already Registered");
				} else if (userService.getUserByPhoneNo(req.getPayload().getPhone_no()) != null) {
					statusTrx = StatusCode.INVALID;
					responseMsg = StatusCode.INVALID.toString();
					result.put("message", "Phone No Is Already Registered");
				} else {
					boolean isUserSaved = userService.userRegistration(req.getPayload());
					if (isUserSaved == false) {
						statusTrx = StatusCode.INVALID;
						responseMsg = StatusCode.INVALID.toString();
						result.put("message", "Failed Save User");
					}  else {
						User user = userService.getUserByUsername(req.getPayload().getUsername());
						result.put("message", "Save User Success");
						result.put("object", user);
						String password = Security.decrypt(user.getPassword());
						String message = MailHelper.successRegistrationMessage(user.getFullname(), user.getUsername(), password);
						mailService.sendEmail(user.getEmail(), "Daftar Akun Biyubi App Berhasil", message, "");
					}
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
