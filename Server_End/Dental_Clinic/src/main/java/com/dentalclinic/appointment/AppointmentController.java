package com.dentalclinic.appointment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.dentist.Schedule;
import com.dentalclinic.patient.Patient;

@Controller
@RequestMapping(path="/appointment")
public class AppointmentController {
	
	private static final String VIEWS_APPOINTMENT_CREATE_OR_UPDATE_FORM = "appointment/createOrUpdateAppointmentForm";
	private final AppointmentRepository appointmentRP;
	
	@Autowired
	public AppointmentController(AppointmentRepository appointmentService){
		this.appointmentRP = appointmentService;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	
	@ModelAttribute("appointment_types")
    public Collection<AppointmentType> populateAppointmentTypes() {
        return this.appointmentRP.findAppointmentTypes();
    }
	
	@ModelAttribute("appointment_dentists")
	public Collection<Dentist> populateAppointmentDentists() {
		return this.appointmentRP.findAppointmentDentists();
	}
	
	@ModelAttribute("appointment_Patients")
    public Collection<Patient> populateAppointmentPatients() {
        return this.appointmentRP.findAppointmentPatients();
    }
	
	@RequestMapping(path="")
	public String AppointmentList(Map<String, Object> model){
		List<Appointment> appointments = new ArrayList<>();
		appointments.addAll(this.appointmentRP.findAll());
		model.put("appointments", appointments);
		return "appointment/appointmentList";
	}
	
	@RequestMapping(path="appointment.json")
	public @ResponseBody List<Appointment> showResourcesDentistList(){
		List<Appointment> appointments = new ArrayList<>();
		appointments.addAll(this.appointmentRP.findAll());
		return appointments;
	}
	
	@RequestMapping(path="/new", method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model){
		Appointment appointment = new Appointment();
//		dentist.addAppointment(appointment);
//		patient.addAppointment(appointment);
		//appointmentType.addAppointmentType(appointment);
		model.put("appointment", appointment);
		return VIEWS_APPOINTMENT_CREATE_OR_UPDATE_FORM;
	}
	@RequestMapping(path="/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Appointment appointment,BindingResult result, Map<String, Object> model){
		if(result.hasErrors()){
			model.put("appointment", appointment);
			return VIEWS_APPOINTMENT_CREATE_OR_UPDATE_FORM;
		//	return "ERROR!";
		}else{
//			dentist.addAppointment(appointment);
//			patient.addAppointment(appointment);
			this.appointmentRP.save(appointment);
			return "redirect:/appointment/{appointmentId}";
		}
	}
	
}
