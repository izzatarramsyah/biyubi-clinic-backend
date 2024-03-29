package com.clinic.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.clinic.api.object.CheckUpSchedule;
import com.clinic.api.object.InfoRq;
import com.clinic.api.object.VaccineSchedule;
import com.clinic.api.request.APIRequest;
import com.clinic.constant.Constant;
import com.clinic.entity.Log;
import com.clinic.entity.CheckUpMaster;
import com.clinic.entity.CheckUpRecord;
import com.clinic.entity.Child;
import com.clinic.entity.User;
import com.clinic.entity.UserAdmin;
import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;
import com.clinic.report.ExportExcel;
import com.clinic.report.ExportPDF;
import com.clinic.service.LogService;
import com.clinic.service.CheckUpService;
import com.clinic.service.MasterService;
import com.clinic.service.UserAdminService;
import com.clinic.service.UserService;
import com.clinic.service.VaccineService;
import com.clinic.util.Util;

@CrossOrigin
@RestController
@RequestMapping("/export")
public class ExportReportController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(ExportReportController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserAdminService userAdminService;

	@Autowired
	VaccineService vaccineService;

	@Autowired
	CheckUpService checkUpService;

	@Autowired
	MasterService masterService;

	@Autowired
	LogService logService;

	@RequestMapping(value = "/excel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] excel(@RequestBody String input) {
		LOG.traceEntry();
		byte[] excelAsByte = null;
		try {
			LOG.info("EXPORT EXCEL");
			APIRequest<InfoRq> req = getInfoRq(input);
			UserAdmin userAdmin = userAdminService.getAdminByUsername(req.getHeader().getuName());
			if (userAdmin != null) {
				switch (req.getHeader().getCommand()) {
					case Constant.SCHEDULE_CHECKUP:
						List<CheckUpSchedule> checkUpSchedule = checkUpService.getSchedule(req.getPayload().getUserId(),
								req.getPayload().getChildId());
						excelAsByte = ExportExcel.checkUp(checkUpSchedule,
								userService.getChildByID(req.getPayload().getChildId()));
						break;
					case Constant.SCHEDULE_VACCINE:
						List<VaccineSchedule> vaccineSchedule = vaccineService.getSchedule(req.getPayload().getUserId(),
								req.getPayload().getChildId());
						excelAsByte = ExportExcel.vaccine(vaccineSchedule,
								userService.getChildByID(req.getPayload().getChildId()));
						break;
					case Constant.INFO_LIST_VACCINE:
						List<VaccineMaster> listVaccineMst = masterService.getListMstVaccine();
						excelAsByte = ExportExcel.vaccineMst(listVaccineMst);
						break;
					case Constant.INFO_LIST_CHECKUP:
						List<CheckUpMaster> listCheckUpMst = masterService.getListMstCheckUp();
						excelAsByte = ExportExcel.checkUpMst(listCheckUpMst);
						break;
					case Constant.INFO_LIST_USER:
						List<User> listUser = userService.getListUser();
						excelAsByte = ExportExcel.listUser(listUser);
						break;
					case Constant.INFO_LOGS:
						List<Log> logs = logService.getLogs(req.getPayload().getUsername());
						excelAsByte = ExportExcel.log(logs);
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
		}
		LOG.traceExit();
		return excelAsByte;

	}

	@RequestMapping(value = "/pdf", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] pdf(@RequestBody String input) {
		LOG.traceEntry();
		byte[] pdfAsByte = null;
		try {
			LOG.info("EXPORT PDF");
			APIRequest<InfoRq> req = getInfoRq(input);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> object = new HashMap<String, Object>();
			UserAdmin userAdmin = userAdminService.getAdminByUsername(req.getHeader().getuName());
			if (userAdmin != null) {
				User user = userService.getUserByID(req.getPayload().getUserId());
				if (user != null) {
					Child child = userService.getChildByID(req.getPayload().getChildId());
					if (child != null) {
						switch (req.getHeader().getCommand()) {
							case Constant.SCHEDULE_CHECKUP:
								CheckUpRecord record = checkUpService.getCheckUpRecord(user.getId(), child.getId(),
										req.getPayload().getMstCode());
								CheckUpMaster cm = masterService.getMstCheckUpByCode(req.getPayload().getMstCode());
								String weightCategory = masterService.category(Constant.WEIGHT, cm.getBatch(),
										record.getWeight());
								String lengthCategory = masterService.category(Constant.LENGTH, cm.getBatch(),
										record.getLength());
								String headDiameterCategory = masterService.category(Constant.HEAD_CIRCUMFERENCE,
										cm.getBatch(), record.getHeadDiameter());
								object.put("parentName", user.getFullname());
								object.put("address", user.getAddress());
								object.put("childName", child.getFullname());
								object.put("age",
										String.valueOf(Util.calculateMonth(formatDate.format(child.getBirthDate()),
												formatDate.format(new Date()))) + " Bulan");
								object.put("birthDate", formatDate.format(child.getBirthDate()));
								object.put("checkUpDate", Util.formatDate(record.getCheckUpDate()));
								object.put("weight", String.valueOf(record.getWeight()) + " KG");
								object.put("length", String.valueOf(record.getLength()) + " CM");
								object.put("headDiameter", String.valueOf(record.getHeadDiameter()) + " CM");
								object.put("weightNotes", Util.getWeightNotes(weightCategory));
								object.put("lengthNotes", Util.getLengthNotes(lengthCategory));
								object.put("headDiameterNotes", Util.getHeadDiameterNotes(headDiameterCategory));
								pdfAsByte = ExportPDF.download(object, "report/template_checkup.jrxml");
								break;
							case Constant.SCHEDULE_VACCINE:
								LOG.info("COMMAND : REPORT-VACCINE");
								VaccineRecord recordd = vaccineService.getVaccineRecord(user.getId(), child.getId(),
										req.getPayload().getBatch(), req.getPayload().getMstCode());
								VaccineMaster vm = masterService.getMstVaccineByCode(req.getPayload().getMstCode());
								object.put("parentName", user.getFullname());
								object.put("address", user.getAddress());
								object.put("childName", child.getFullname());
								object.put("age",
										String.valueOf(Util.calculateMonth(formatDate.format(child.getBirthDate()),
												formatDate.format(new Date()))) + " Bulan");
								object.put("birthDate", formatDate.format(child.getBirthDate()));
								object.put("vaccineDate", Util.formatDate(recordd.getVaccineDate()));
								SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
								Calendar expDate = Calendar.getInstance();
								expDate.setTime(recordd.getVaccineDate());
								expDate.add(Calendar.DATE, vm.getExpDays());
								object.put("expDate", Util.formatDate(sdf.parse(sdf.format(expDate.getTime()))));
								object.put("vaccineName", vm.getVaccineName());
								object.put("batch", String.valueOf(recordd.getBatch()));
								object.put("vaccineNotes", recordd.getNotes());
								pdfAsByte = ExportPDF.download(object, "report/template_vaccine.jrxml");
								break;
							default:
								break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
		}
		LOG.traceExit();
		return pdfAsByte;
	}

}
