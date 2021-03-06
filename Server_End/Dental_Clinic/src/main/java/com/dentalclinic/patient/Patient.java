package com.dentalclinic.patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.appointment.Appointment;
import com.dentalclinic.model.Person;

/**
 * Simple JavaBean domain object representing a Patient.
 *
 * @author Xiangting Fan
 */

@Entity
@Table(name = "patient")
public class Patient extends Person{
	
	@Column(name = "title")
	@NotEmpty
	private String title;
	
	@Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthDate;
	
	@Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;
    
    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "patient_type")
    private String patientType;
    
    @Column(name = "pps")
    private String pps;
    
    @Column(name = "medical_consideration")
    private String medical_consideration;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<Appointment> appointments;
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getTitle(){
    	return this.title;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPatientType() {
        return this.patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }
    
    public String getPps() {
        return this.pps;
    }

    public void setPps(String pps) {
        this.pps = pps;
    }
//    
    public String getMedical_consideration() {
        return this.medical_consideration;
    }

    public void setMedical_consideration(String medical_consideration) {
        this.medical_consideration = medical_consideration;
    }
//    
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
	    appointment.setPatient(this);
	}
}
