package com.dentalclinic.appointment;

import java.util.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.model.BaseEntity;
import com.dentalclinic.patient.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.dentalclinic.dentist.*;
//import com.dentalclinic.appointment.*;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity{

	 /**
     * Holds value of property date.
     */
    @Column(name = "appointment_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    
   // @NotEmpty
    @Column(name = "description")
    private String description;
  
    @ManyToOne
    @JoinColumn(name="patient_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name="dentist_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
    private Dentist dentist;
    
    @ManyToOne
    @JoinColumn(name="appointment_type")
    private AppointmentType appointmentType;
    
    public void setDate(Date date){
    	this.date = date;
    }  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+0")
    public Date getDate(){
    	return this.date;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }   
    public String getDescription(){
    	return this.description;
    }
    
    public void setPatient(Patient patient){
    	this.patient = patient;
    }   
    public Patient getPatient(){
    	return this.patient;
    }
    
    public void setDentist(Dentist dentist){
    	this.dentist = dentist;
    }   
    public Dentist getDentist(){
    	return this.dentist;
    }
    
    public void setAppointmentType(AppointmentType appointmentType){
    	this.appointmentType = appointmentType;
    }   
    public AppointmentType getAppointmentType(){
    	return this.appointmentType;
    }
}
