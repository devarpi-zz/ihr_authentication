package com.istream.ihr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istream.ihr.service.TimeSheetService;
import com.istream.ihr.vo.TimesheetSearch;
import com.istream.ihr.vo.orm.TimeSheet;

public class TimesheetController {
	
	@Autowired
	TimeSheetService timeSheetService;
	
	
	
	@RequestMapping(value = "/timesheets/", method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<TimeSheet>> getTimeSheets(TimesheetSearch searchRequest) {
		List<TimeSheet> timeSheets = timeSheetService.getTimeSheets(searchRequest);
		return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/timesheet/submit", method = RequestMethod.POST, headers="Accept=application/json")
	public void submitTimeSheet() {
		
	}
	
	@RequestMapping(value = "/timesheet/update", method = RequestMethod.PUT, headers="Accept=application/json")
	public void updateTimeSheet() {
		
	}
	
	@RequestMapping(value = "/timesheet/unsubmit", method = RequestMethod.PUT, headers="Accept=application/json")
	public void unSubmitTimeSheet() {
		
	}
	
	@RequestMapping(value = "/timesheet/approve", method = RequestMethod.PUT, headers="Accept=application/json")
	public void approveTimeSheet() {
		
	}


	public TimeSheetService getTimeSheetService() {
		return timeSheetService;
	}



	public void setTimeSheetService(TimeSheetService timeSheetService) {
		this.timeSheetService = timeSheetService;
	}	 

}
