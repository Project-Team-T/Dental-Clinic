package com.dentalclinic.dentist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.dentist.DentistRepository;
import com.dentalclinic.queryModel.DaysAvailableWithDentist;
import com.dentalclinic.queryModel.DentistInfoWithAppointmentType;

/**
 * @author Xiangting Fan
 */

@Controller
@RequestMapping(path="/dentist")
public class DentistController {
	
	private static final String VIEWS_DENTIST_CREATE_OR_UPDATE_FORM = "dentist/createOrUpdateDentistForm";
	private final DentistRepository dtRepository;
	private final ScheduleRepository scReposity;
	
	@Autowired
	public DentistController(DentistRepository dentalService, ScheduleRepository scheduleService){
		this.dtRepository = dentalService;
		this.scReposity = scheduleService;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	
	@GetMapping(path="")
    public String DentistList(Map<String, Object> model) {
		// Here we are returning an object of type 'Dentists' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
		Dentists dentists = new Dentists();
		dentists.getDentistList().addAll((Collection<? extends Dentist>) this.dtRepository.findAll());
		model.put("dentists", dentists);
        return "dentist/dentistList";
    }
	
	@RequestMapping(value = { "/dentists.json", "/dentists.xml" })
	//@GetMapping(path = {"/dentists.json", "/dentists.xml"})
    public @ResponseBody Dentists showResourcesDentistList() {
        // Here we are returning an object of type 'Dentists' rather than a collection of Dentist
        // objects so it is simpler for JSon/Object mapping
        Dentists dentists = new Dentists();
//        dentists.getDentistList().addAll((Collection<? extends Dentist>) this.dtRepository.findAll());
        dentists.getDentistList().addAll(this.dtRepository.findDentistNames());
//        dentists.getDentistList().addAll(this.dtRepository.findAll());
        return dentists;
    }
	
	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		Dentist dentist = new Dentist();
		model.put("dentist", dentist);
		return VIEWS_DENTIST_CREATE_OR_UPDATE_FORM;
	} 
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Dentist dentist, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_DENTIST_CREATE_OR_UPDATE_FORM;
        } else {
            this.dtRepository.save(dentist);
            return "redirect:/dentist/" + dentist.getId();
        }
	}
	
	@RequestMapping("/{dentistId}")
	public ModelAndView showDentist(@PathVariable("dentistId") int dentistId){
		ModelAndView mav = new ModelAndView("dentist/dentistDetails");
		mav.addObject(this.dtRepository.findById(dentistId));
		return mav;
	}
	 
//	private long Dentist_id;	
//	@NotNull
//	private String Lname;  //Last Name	
//	@NotNull
//	private String Fname;  //First Name	
//	private String Gender;  //gender
//	//private String Birthday;  //birthday
//	private String Phonenumber; //number
//	private String Email;   //email
//	private String Address; //Address
//	private String AdditionalInformation;
//	private String Image; 
	
	@GetMapping(path="/add")
	public String addNewDentist (@RequestParam String Lname, 
			@RequestParam String Fname, @RequestParam String Gender,@RequestParam String Phonenumber,
			@RequestParam String Email, @RequestParam String Address, 
			@RequestParam String AdditionalInformation, @RequestParam String Image){
//		Dentist new_dentist = new Dentist(Lname,Fname, Gender, Phonenumber,
//				 Email, Address, AdditionalInformation, Image);
		
//		dtRepository.save(new_dentist);
		return "add";
	}
	
	@GetMapping(path="/querybyAppointmentType")
	public @ResponseBody List<DentistInfoWithAppointmentType> QueryDentistByApptype (@RequestParam int Aptype_id){
		List<DentistInfoWithAppointmentType> dentists = new ArrayList<>();
		dentists.addAll(this.dtRepository.findDentistbyAppType(Aptype_id));
		return dentists;	
		}
	
	@GetMapping(path="/querytimeavailabe")
	public @ResponseBody List<DaysAvailableWithDentist> queryavailabletime (@RequestParam int dentist_id){
		List<DaysAvailableWithDentist> schedules = new ArrayList<>();
		schedules.addAll(this.scReposity.findTimeAvailableByDentistId(dentist_id));
		return schedules;	
	}
	
	@GetMapping(path="find")
	public @ResponseBody Dentists showResourceDentistListById(){
		Dentists dentists = new Dentists();
		
		return dentists;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Dentist> getAllUsers() {
		// This returns a JSON or XML with the users
		return dtRepository.findAll();
	}
	
	@GetMapping(path="/saved")
	public @ResponseBody String hello() {
		// This returns a JSON or XML with the users
		return "saved ";
	}
		
	
}
