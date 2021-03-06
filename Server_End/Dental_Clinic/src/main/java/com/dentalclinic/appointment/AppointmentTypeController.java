package com.dentalclinic.appointment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.queryModel.AppointmentTypeName;


@Controller
public class AppointmentTypeController {
	
	private static final String VIEWS_APPOINTMENTTYPE_CREATE_OR_UPDATE_FORM = "appointment/createOrUpdateATypeForm";
	private final AppointmentTypeRepository atRepository;
	
	@Autowired
	public AppointmentTypeController(AppointmentTypeRepository atRepository){
		this.atRepository = atRepository;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }	
		
	@GetMapping(value={"appointment/appointmentType"})
	public String AppointmentTypeList(Map<String, Object> model){	
		AppointmentTypes appointmentTypes = new AppointmentTypes();
		appointmentTypes.getAppointmenttypeList().addAll(this.atRepository.findAll());
		model.put("appointmentTypes", appointmentTypes);
		return "appointment/appointmentTypeList";
	}
	
	@GetMapping(value="appointment/appointmentType.json")
	public @ResponseBody List<AppointmentTypeName> showSourceAppointmentTypeList(){
//		AppointmentTypes appointmentTypes = new AppointmentTypes();
//		appointmentTypes.getAppointmenttypeList().addAll(this.atRepository.findAppointmentTypeName());
		List<AppointmentTypeName> appointmentTypes = new ArrayList<>();
		appointmentTypes.addAll(this.atRepository.findAppointmentTypeName());
		return appointmentTypes;
	}
	@GetMapping(path="appointment/queryappointmentType")
	public String RequestSourceAppointmentType(@RequestParam int fetchAppointmentTypes){
		if(fetchAppointmentTypes == 1){
			return "redirect:/appointment/appointmentType.json";
		}
		return "redirect:/appointment/appointmentType.json";
	}
	
	@RequestMapping(value="appointment/appointmentType/new",method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		AppointmentType appointmentType = new AppointmentType();
		model.put("appointmentType", appointmentType);
		return VIEWS_APPOINTMENTTYPE_CREATE_OR_UPDATE_FORM;
	} 
	
	@RequestMapping(value = "appointment/appointmentType/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid AppointmentType appointmentType, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_APPOINTMENTTYPE_CREATE_OR_UPDATE_FORM;
        } else {
            this.atRepository.save(appointmentType);
            return "redirect:/appointment/appointmentType/" + appointmentType.getId();
        }
	}
	
	@RequestMapping("appointment/appointmentType/{appointmentTypeId}")
	public ModelAndView showDentist(@PathVariable("appointmentTypeId") int appointmentTypeId){
		ModelAndView mav = new ModelAndView("/appointment/appointmentTypeDetails");
		mav.addObject(this.atRepository.findById(appointmentTypeId));
		return mav;
	}

}
