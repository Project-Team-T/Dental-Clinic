package com.dentalclinic.dentist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.dentalclinic.appointment.Appointment;
import com.dentalclinic.appointment.AppointmentType;
import com.dentalclinic.model.Person;
/**
 * @author Xiangting Fan
 */

@Entity  // This tells Hibernate to make a table out of this class
public class Dentist extends Person{
	
	private String Gender;  //gender
	//private String Birthday;  //birthday

	@Column(name = "Phonenumber")
    @Digits(fraction = 0, integer = 10)
    private String Phonenumber;
	
	private String Email;   //email
	private String Address; //Address
	private String AdditionalInformation;
	private String Image; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dentist")
    private Set<Appointment> appointments;
	
	@OneToMany(mappedBy="dentist")
	private Set<DentistAppointmentType> dentistAppointmentTypes = new HashSet<DentistAppointmentType>();
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name = "Dentist_AppointmentType", joinColumns = @JoinColumn(name = "dentist_id"), inverseJoinColumns = @JoinColumn(name = "appointmenttype_id"))
//    private Set<AppointmentType> appointmentTypes;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Dentist_Schedule", joinColumns = @JoinColumn(name = "dentist_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private Set<Schedule> schedules;
	
	public Dentist(){
		
	}
//	public Dentist(String lastName,String firstName,String Gender,String Phonenumber,
//			String Email, String Address,String AdditionalInformation,String Image){
//		this.lastName = lastName;
//		this.firstName=firstName;
//		this.Gender=Gender;
//		this.Phonenumber=Phonenumber;
//		this.Email=Email;
//		this.Address=Address;
//		this.AdditionalInformation=AdditionalInformation;
//		this.Image=Image;
//	}
	
	public String getGender(){
		return Gender;
	}
	public void setGender(String Gender){
		this.Gender=Gender;
	}
	
	public String getPhonenumber(){
		return Phonenumber;
	}
	public void setPhonenumber(String Phonenumber){
		this.Phonenumber=Phonenumber;
	}
	
	public String getEmail(){
		return Email;
	}
	public void setEmail(String Email){
		this.Email=Email;
	}
	
	public String getAddress(){
		return Address;
	}
	public void setAddress(String Address){
		this.Address=Address;
	}
	
	public String getAdditionalInformation(){
		return AdditionalInformation;
	}
	public void setAdditionalInformation(String AdditionalInformation){
		this.AdditionalInformation=AdditionalInformation;
	}
	public String getImage(){
		return Image;
	}
	public void setImage(String Image){
		this.Image=Image;
	}
	
	protected Set<Appointment> getAppointmentsInternal() {
		if (this.appointments == null) {
			this.appointments = new HashSet<>();
	    }
	    return this.appointments;
	}

	protected void setAppointmentsInternal(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		List<Appointment> sortedAppointments = new ArrayList<>(getAppointmentsInternal());
	    PropertyComparator.sort(sortedAppointments, new MutableSortDefinition("name", true, true));
	    return Collections.unmodifiableList(sortedAppointments);
	 }

	public void addAppointment(Appointment appointment) {
		if (appointment.isNew()) {
			getAppointmentsInternal().add(appointment);
	    }
	    appointment.setDentist(this);
	}
	
	//AppointmentTypes
//	protected Set<AppointmentType> getAppointmentTypesInternal() {
//		if (this.appointmentTypes == null) {
//			this.appointmentTypes = new HashSet<>();
//	    }
//	    return this.appointmentTypes;
//	}
//
//	protected void setAppointmentTypesInternal(Set<AppointmentType> appointmentTypes) {
//		this.appointmentTypes = appointmentTypes;
//	}
//
//	public List<AppointmentType> getAppointmentTypes() {
//		List<AppointmentType> sortedAppointmentTypes = new ArrayList<>(getAppointmentTypesInternal());
//	    PropertyComparator.sort(sortedAppointmentTypes, new MutableSortDefinition("name", true, true));
//	    return Collections.unmodifiableList(sortedAppointmentTypes);
//	 }
//
//	public int getNrOfAppointmentTypes(){
//		return getAppointmentTypesInternal().size();
//	}
//	public void addAppointmentType(AppointmentType appointmentType){
//		getAppointmentTypesInternal().add(appointmentType);
//	}
	
	//appointmentType
	@OneToMany(mappedBy = "primaryKey.dentist", cascade = CascadeType.ALL)
	public Set<DentistAppointmentType> getDentistAppointmentTypes(){
		return dentistAppointmentTypes;
	}
	
	public void setDentistAppointmentTypes (Set<DentistAppointmentType> dentistAppointmentTypes){
		this.dentistAppointmentTypes = dentistAppointmentTypes;
	}
	
	public void addDentistAppointmentType(DentistAppointmentType dentistAppointmentType){
		this.dentistAppointmentTypes.add(dentistAppointmentType);		
	}
	//end
	
	//Schedules
	protected Set<Schedule> getSchedulesInternal() {
		if (this.schedules == null) {
			this.schedules = new HashSet<>();
	    }
	    return this.schedules;
	}

	protected void setSchedulesInternal(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Schedule> getSchedules() {
		List<Schedule> sortedSchedules = new ArrayList<>(getSchedulesInternal());
	    PropertyComparator.sort(sortedSchedules, new MutableSortDefinition("Day", true, true));
	    return Collections.unmodifiableList(sortedSchedules);
	 }

	public int getNrOfSchedules(){
		return getSchedulesInternal().size();
	}
	public void addSchedule(Schedule schedule){
		getSchedulesInternal().add(schedule);
	}
		
}
