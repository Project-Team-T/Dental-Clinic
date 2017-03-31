/**
 * 
 */
package com.dentalclinic.appointment;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * @author fanfan
 *
 */
public class AppointmentTypeFormatte implements Formatter<AppointmentType> {
	
	private final AppointmentTypeRepository appointmenttypes;
	
	@Autowired
	public AppointmentTypeFormatte(AppointmentTypeRepository appointmenttype){
		this.appointmenttypes = appointmenttype;
	}

	@Override
	public String print(AppointmentType appointmenttype, Locale locale) {
		// TODO Auto-generated method stub
		return appointmenttype.getName();
	}

	@Override
	public AppointmentType parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Collection<AppointmentType> findappointmenttypes = this.appointmenttypes.findAll();
		for(AppointmentType type : findappointmenttypes){
			if(type.getName().equals(text)){
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
