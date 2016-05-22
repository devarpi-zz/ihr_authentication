package com.istream.ihr.service;

import java.util.List;

import com.istream.ihr.vo.TimesheetSearch;
import com.istream.ihr.vo.orm.TimeSheet;

public interface TimeSheetService {
	
	public List<TimeSheet> getTimeSheets(TimesheetSearch searchRequest);
	
	public void submitTimeSheet(TimeSheet timeSheet);
	
	public void approveTimeSheet(TimeSheet timeSheet);	
	

}
