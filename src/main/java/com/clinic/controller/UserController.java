package com.clinic.controller;

import java.text.SimpleDateFormat;
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
import com.clinic.api.object.MessageRq;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.constant.Constant;
import com.clinic.constant.StatusCode;
import com.clinic.entity.User;
import com.clinic.service.CheckUpService;
import com.clinic.service.MailService;
import com.clinic.service.MasterService;
import com.clinic.service.RestService;
import com.clinic.service.UserService;
import com.clinic.util.Security;
import com.clinic.api.object.ChangePasswordRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(UserController.class);

	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	@Autowired
	CheckUpService checkUpService;

	@Autowired
	MasterService masterService;

	@Autowired
	RestService restService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> login(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<HashMap<String, Object>> response = new APIResponse<HashMap<String, Object>>();
		StatusCode statusTrx = StatusCode.SUCCESS;
		try {
			LOG.info("LOGIN");
			APIRequest<User> req = getRequestUser(input);
			if (req.getHeader().getChannel().equals(Constant.CHANNEL_MOBILE)) {
				User user = userService.getUserByUsername(req.getPayload().getUsername());
				if (user == null) {
					statusTrx = StatusCode.USER_NOT_FOUND;
				} else {
					Boolean isValid = userService.checkValidUser(req.getPayload().getUsername(),
							req.getPayload().getPassword());
					if (!isValid) {
						statusTrx = StatusCode.INVALID_USER;
					}
				}
			} else {
				statusTrx = StatusCode.INVALID_CHANNEL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
		}
		response.setHeader(new HeaderResponse(statusTrx.getCode(), statusTrx.getStatusDesc()));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}

	@RequestMapping(value = "/changePasword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> changePasword(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<HashMap<String, Object>> response = new APIResponse<HashMap<String, Object>>();
		StatusCode statusTrx = StatusCode.SUCCESS;
		try {
			LOG.info("CHANGE PASSWORD");
			APIRequest<ChangePasswordRequest> req = getChangePasswordRequest(input);
			if (req.getHeader().getChannel().equals(Constant.CHANNEL_MOBILE)) {
				User user = userService.getUserByUsername(req.getPayload().getUsername());
				if (user == null) {
					statusTrx = StatusCode.USER_NOT_FOUND;
				} else {
					Boolean isValid = userService.checkValidUser(req.getPayload().getUsername(),
							req.getPayload().getPassword());
					if (!isValid) {
						statusTrx = StatusCode.INVALID_USER;
					} else {
						boolean isChanged = userService.changePassword(user, req.getPayload().getNewPassword());
						if (!isChanged) {
							statusTrx = StatusCode.FAILED_PROCESS;
						} else {
							String password = Security.decrypt(user.getPassword());
							String message = "Halo, " + user.getFullname() + " password Biyubi App terbaru anda adalah "
									+ password;
							restService.post(new MessageRq(user.getPhone_no(), message));
						}
					}
				}
			} else {
				statusTrx = StatusCode.INVALID_CHANNEL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
		}
		response.setHeader(new HeaderResponse(statusTrx.getCode(), statusTrx.getStatusDesc()));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<?> forgotPassword(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<HashMap<String, Object>> response = new APIResponse<HashMap<String, Object>>();
		StatusCode statusTrx = StatusCode.SUCCESS;
		try {
			LOG.info("CHANGE PASSWORD");
			APIRequest<User> req = getRequestUser(input);
			if (req.getHeader().getChannel().equals(Constant.CHANNEL_MOBILE)) {
				User user = userService.getUserByUsername(req.getPayload().getUsername());
				if (user == null) {
					statusTrx = StatusCode.USER_NOT_FOUND;
				} else {
					String decryptedPassword = Security.decrypt(user.getPassword());
					String message = "Halo, " + user.getFullname() + " password Biyubi App anda adalah "
							+ decryptedPassword;
					restService.post(new MessageRq(user.getPhone_no(), message));
				}
			} else {
				statusTrx = StatusCode.INVALID_CHANNEL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
		}
		response.setHeader(new HeaderResponse(statusTrx.getCode(), statusTrx.getStatusDesc()));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}

}
