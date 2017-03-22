package com.dentalclinic.queryModel;

public class DentistInfoWithAppointmentType {
	
	private Integer id;
	private String name;
	
	public DentistInfoWithAppointmentType() {
		
	}
	public DentistInfoWithAppointmentType(Integer id, String fname, String lname){
		this.id = id;
		this.name = fname +" " +lname;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
