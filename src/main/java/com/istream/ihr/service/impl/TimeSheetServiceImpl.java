/**
 * 
 */
package com.istream.ihr.service.impl;

import java.util.List;

import com.istream.ihr.service.TimeSheetService;
import com.istream.ihr.vo.TimesheetSearch;
import com.istream.ihr.vo.orm.TimeSheet;

/**
 * @author Istream2
 *
 */
public class TimeSheetServiceImpl implements TimeSheetService {

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#getTimeSheets(com.istream.ihr.vo.TimesheetSearch)
	 */
	@Override
	public List<TimeSheet> getTimeSheets(TimesheetSearch searchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#submitTimeSheet(com.istream.ihr.vo.orm.TimeSheet)
	 */
	@Override
	public void submitTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#approveTimeSheet(com.istream.ihr.vo.orm.TimeSheet)
	 */
	@Override
	public void approveTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub

	}

}
