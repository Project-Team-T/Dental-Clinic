/**
 * 
 */
package com.dentalclinic.dentist;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * @author fanfan
 *
 */
public class DentistFormatte implements Formatter<Dentist> {
	
	private final DentistRepository dentist;
	
	@Autowired
	public DentistFormatte(DentistRepository dentist){
		this.dentist = dentist;
	}

	@Override
	public String print(Dentist dentist, Locale locale) {
		// TODO Auto-generated method stub
		return dentist.getFirstName();
	}

	@Override
	public Dentist parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Collection<Dentist> finddentists = this.dentist.findAll();
		for(Dentist dentist : finddentists){
			if(dentist.getFullName().equals(text)){
				return dentist;
			}
		}
		throw new ParseException("Dentist not found: " + text, 0);
	}

}
