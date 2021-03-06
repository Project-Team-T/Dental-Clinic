package com.dentalclinic.dentist;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dentalclinic.appointment.AppointmentType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Dentist_Appointmenttype")
//@IdClass(DentistAppointmentTypeId.class)
public class DentistAppointmentType implements Serializable{
	
//	@Id
//	private Integer dentistId;
////	@Id
//	private Integer appointmentTypeId;
	
	@EmbeddedId
	private DentistAppointmentTypeId id;
	
	@Id
	@ManyToOne
	@JoinColumn(name="dentist_id", insertable=false, updatable=false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
//	@JoinColumn(name="dentist_id", referencedColumnName="id")
	private Dentist dentist;
	
	@Id
	@ManyToOne
	@JoinColumn(name="appointmenttype_id", insertable=false, updatable=false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
//	@JoinColumn(name="dentist_id", referencedColumnName="id")
	private AppointmentType appointmentType;
	
	//additional fields
	@Column(name = "time_required")
	private int duration;
	
	public DentistAppointmentType(){}
	
	public DentistAppointmentType(Dentist dentist, AppointmentType appointmentType, int duration){
		//Create primary key
		this.id = new DentistAppointmentTypeId(dentist.getId(), appointmentType.getId());
		
		// initialize attributes
		this.dentist = dentist;
		this.appointmentType = appointmentType;
		this.duration = duration;
		
		dentist.addDentistAppointmentType(this);
		appointmentType.addDentistAppointmentType(this);
	}
	//getter and setter
	public int getDuration(){
		return this.duration;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public Dentist getDentist(){
		return this.dentist;
	}
	public void setDentist(Dentist dentist){
		this.dentist = dentist;
	}
	
	public AppointmentType getAppointmentType(){
		return this.appointmentType;
	}
	
	public void setAppointmentType(AppointmentType appointmentType){
		this.appointmentType = appointmentType;
	}
}
