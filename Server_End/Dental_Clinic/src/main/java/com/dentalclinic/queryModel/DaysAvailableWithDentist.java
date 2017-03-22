/**
 * 
 */
package com.dentalclinic.queryModel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author fanfan
 *
 */
public class DaysAvailableWithDentist {
	
	private Integer id;
	private String Day;
	private Date Time_begin;
	private Date Time_end;
	
	public DaysAvailableWithDentist(){
		
	}
	public DaysAvailableWithDentist(Integer id, String Day, Date Time_begin, Date Time_end){
		this.id = id;
		this.Day = Day;
		this.Time_begin = Time_begin;
		this.Time_end = Time_end;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	
	@JsonFormat(pattern="h:mm a")
	public Date getTime_begin(){
		return this.Time_begin;
	}
	
	@JsonFormat(pattern="HH:mm",timezone = "GMT+0")
	public void setTime_begin(Date Time_begin){
		this.Time_begin = Time_begin;
	}
	
	@JsonFormat(pattern="h:mm a")
	public Date getTime_end(){
		return this.Time_end;
	}
	@JsonFormat(pattern="HH:mm",timezone = "GMT+0")
	public void setTime_end(Date Time_end){
		this.Time_end=Time_end;
	}
}
