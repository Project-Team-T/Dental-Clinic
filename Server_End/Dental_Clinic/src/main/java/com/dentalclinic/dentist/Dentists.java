package com.dentalclinic.dentist;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of dentists. Mostly here to be used for the 'dentists' {@link
 * org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Xiangting Fan
 */
@XmlRootElement
public class Dentists {
	private List<Dentist> dentists;
	
	@XmlElement
	public List<Dentist> getDentistList(){
		if(dentists == null){
			dentists = new ArrayList<>();
		}
		return dentists;
	}

}
