package com.dentalclinic.appointment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.dentist.DentistAppointmentType;
import com.dentalclinic.model.NamedEntity;;

@Entity
@Table(name = "appointment_type")
public class AppointmentType extends NamedEntity{
	
	@OneToMany(mappedBy = "appointmentType")
	private Set<DentistAppointmentType> dentistAppointmentTypes = new HashSet<DentistAppointmentType>();
	
	//@OneToMany(mappedBy = "primaryKey.AppointmentType", cascade = CascadeType.ALL)
	public Set<DentistAppointmentType> getDentistAppointmentTypes(){
		return dentistAppointmentTypes;
	}
	public void setDentistAppointmentTypes (DentistAppointmentType dentistAppointmentType){
		this.dentistAppointmentTypes.add(dentistAppointmentType);
	}
	public void addDentistAppointmentType(DentistAppointmentType dentistAppointmentType){
		this.dentistAppointmentTypes.add(dentistAppointmentType);
	}
	

}
