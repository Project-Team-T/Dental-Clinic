package com.dentalclinic.patient;

import java.util.Collection;
import java.util.Map;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.patient.Patient;
import com.dentalclinic.patient.PatientRepository;

/**
 * @author Xiangting Fan
 */
@Controller
@RequestMapping(path="/patient")
public class PatientController {
	
	private static final String VIEWS_PATIENT_CREATE_OR_UPDATE_FORM = "patient/createOrUpdatePatientForm";
	private final PatientRepository patientRep;
	
	@Autowired
	public PatientController(PatientRepository clinicService) {
		this.patientRep = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	 }
	@GetMapping(path="")
	public String PatientList(Map<String, Object> model){
		Patients patients = new Patients();
		patients.getPatientList().addAll(patientRep.findAll());
		model.put("patients", patients);
		return "patient/patientList";
	}
	
	// firstName lastName title birthDate address city telephone email patient_type pps medical_consideration
	@GetMapping(path="/add")
	public String addPatient (@RequestParam String Lname, 
			@RequestParam String Fname, @RequestParam String Title,@RequestParam Date Birthdate, 
			@RequestParam String Phonenumber,@RequestParam String Email, @RequestParam String Address,
			@RequestParam String City,@RequestParam String Patient_type,@RequestParam String pps,
			@RequestParam String Medical_consideration){
//		Patient new_patient = new Patient(Lname,Fname, Gender, Phonenumber,
//				 Email, Address, AdditionalInformation, Image);
//		
		Patient new_patient = new Patient();
		new_patient.setLastName(Lname);
		new_patient.setFirstName(Fname);
		new_patient.setTitle(Title);
		new_patient.setBirthDate(Birthdate);
		new_patient.setTelephone(Phonenumber);
		new_patient.setEmail(Email);
		new_patient.setAddress(Address);
		new_patient.setCity(City);
		new_patient.setPatientType(Patient_type);
		new_patient.setPps(pps);
		new_patient.setMedical_consideration(Medical_consideration);
		
		patientRep.save(new_patient);
		return "add";
		}
	 
	@RequestMapping(value = "/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Patient patient = new Patient();
        model.put("patient", patient);
        return VIEWS_PATIENT_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PATIENT_CREATE_OR_UPDATE_FORM;
        } else {
            this.patientRep.save(patient);
            return "redirect:/patient/" + patient.getId();
        }
    }
    
    @RequestMapping("/{patientId}")
	public ModelAndView showPatient(@PathVariable("patientId") int patientId){
		ModelAndView mav = new ModelAndView("patient/patientDetails");
		mav.addObject(this.patientRep.findById(patientId));
		return mav;
	}

}
