package com.dentalclinic.dentist;

import java.util.ArrayList;
import java.util.Collections;
//import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{
	
	@Column(name="Day")
	@NotNull
	private String Day;
	
	@NotNull
	@Column(name = "Time_begin")
 //   @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    private Date Time_begin;
	
	@NotNull
	@Column(name = "Time_end")
	@DateTimeFormat(pattern = "HH:mm")
	private Date Time_end;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Dentist_Schedule", joinColumns = @JoinColumn(name = "dentist_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private Set<Dentist> dentists;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+0")
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	
	public Date getTime_begin(){
		return this.Time_begin;
	}
	
	public void setTime_begin(Date Time_begin){
		this.Time_begin = Time_begin;
	}
	public Date getTime_end(){
		return this.Time_end;
	}
	public void setTime_end(Date Time_end){
		this.Time_end=Time_end;
	}
	
	/*
	 * Dentists  getter and setter
	 * */
	protected Set<Dentist> getDentistsInternal(){
		if(this.dentists == null){
			this.dentists = new HashSet<>();
		}
		return this.dentists;
	}
	protected void setDentistsInternal(Set<Dentist> dentists){
		this.dentists = dentists;
	}
	
	public List<Dentist> getDentists(){
		List<Dentist> sortedDentists = new ArrayList<>(getDentistsInternal()); 
		PropertyComparator.sort(sortedDentists, new MutableSortDefinition("Day", true, true));
		return Collections.unmodifiableList(sortedDentists);
	}
	/* **********End* *********** */

}
