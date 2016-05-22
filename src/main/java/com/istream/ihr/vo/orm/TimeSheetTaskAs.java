package com.istream.ihr.vo.orm;
// Generated 21-May-2016 13:20:47 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TimeSheetTaskAs generated by hbm2java
 */
@Entity
@Table(name = "time_sheet_task_as", catalog = "ihr")
public class TimeSheetTaskAs implements java.io.Serializable {

	private Integer timestTaskId;
	private ProjectTask projectTask;
	private TimeSheet timeSheet;
	private Date creationTs;
	private Date lastUpdateTs;

	public TimeSheetTaskAs() {
	}

	public TimeSheetTaskAs(ProjectTask projectTask, TimeSheet timeSheet, Date creationTs, Date lastUpdateTs) {
		this.projectTask = projectTask;
		this.timeSheet = timeSheet;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Timest_task_id", unique = true, nullable = false)
	public Integer getTimestTaskId() {
		return this.timestTaskId;
	}

	public void setTimestTaskId(Integer timestTaskId) {
		this.timestTaskId = timestTaskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	public ProjectTask getProjectTask() {
		return this.projectTask;
	}

	public void setProjectTask(ProjectTask projectTask) {
		this.projectTask = projectTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_sheet_id", nullable = false)
	public TimeSheet getTimeSheet() {
		return this.timeSheet;
	}

	public void setTimeSheet(TimeSheet timeSheet) {
		this.timeSheet = timeSheet;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creation_ts", nullable = false, length = 0)
	public Date getCreationTs() {
		return this.creationTs;
	}

	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Last_update_ts", nullable = false, length = 0)
	public Date getLastUpdateTs() {
		return this.lastUpdateTs;
	}

	public void setLastUpdateTs(Date lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}

}