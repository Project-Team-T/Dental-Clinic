package com.dentalclinic.appointment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class AppointmentTypes {
	private List<AppointmentType> appointmenttypeList;
	
	@XmlElement
	public List<AppointmentType> getAppointmenttypeList(){
		if(appointmenttypeList == null)
			appointmenttypeList = new ArrayList<>();
		return appointmenttypeList;
	}
}
