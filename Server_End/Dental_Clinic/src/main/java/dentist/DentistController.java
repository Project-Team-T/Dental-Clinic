package dentist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dentist.Dentist;
import dentist.DentistRepository;

@Controller
@RequestMapping(path="/dentist")
public class DentistController {
	@Autowired
	
	private DentistRepository dtRepository;
	
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
	public @ResponseBody String addNewUser (@RequestParam String Lname, 
			@RequestParam String Fname, @RequestParam String Gender,@RequestParam String Phonenumber,
			@RequestParam String Email, @RequestParam String Address, 
			@RequestParam String AdditionalInformation, @RequestParam String Image){
		Dentist new_dentist = new Dentist(Lname,Fname, Gender, Phonenumber,
				 Email, Address, AdditionalInformation, Image);
		
		dtRepository.save(new_dentist);
		return "saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Dentist> getAllUsers() {
		// This returns a JSON or XML with the users
		return dtRepository.findAll();
	}
		
	
}
