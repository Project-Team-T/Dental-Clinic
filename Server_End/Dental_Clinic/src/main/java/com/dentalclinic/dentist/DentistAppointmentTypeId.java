package com.dentalclinic.dentist;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.dentalclinic.appointment.AppointmentType;

@Embeddable
public class DentistAppointmentTypeId implements Serializable{
	@Column(name="dentist_id")
	private Integer dentistId;
	@Column(name="appointmenttype_id")
	private Integer appointmentTypeId;
	
	public int hashCode(){
		return (int) (dentistId + appointmentTypeId);
	}
	
	public DentistAppointmentTypeId(){
		
	}
	
	public DentistAppointmentTypeId(Integer dentistId, Integer appointmentTypeId){
		this.dentistId = dentistId;
		this.appointmentTypeId = appointmentTypeId;
	}
	
	public boolean equals(Object object){
		if(object instanceof DentistAppointmentTypeId){
			DentistAppointmentTypeId otherId = (DentistAppointmentTypeId) object;
			return (otherId.dentistId == this.dentistId) && (otherId.appointmentTypeId == this.appointmentTypeId);
		}
		return false;
	}
//	private Dentist dentist;
//	private AppointmentType appointmentType;
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	public Dentist getDentist(){
//		return dentist;
//	}
//	public void setDentist(Dentist dentist){
//		this.dentist = dentist;
//	}
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	public AppointmentType getAppointmentType(){
//		return appointmentType;
//	}
//	
//	public void setAppointmentType(AppointmentType appointmentType){
//		this.appointmentType = appointmentType;
//	}
	
}
