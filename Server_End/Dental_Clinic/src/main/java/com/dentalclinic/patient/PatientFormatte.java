/**
 * 
 */
package com.dentalclinic.patient;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * @author fanfan
 *
 */
public class PatientFormatte implements Formatter<Patient> {
	
	private final PatientRepository patient;
	
	@Autowired
	public PatientFormatte(PatientRepository patient){
		this.patient = patient;
	}

	@Override
	public String print(Patient patient, Locale locale) {
		// TODO Auto-generated method stub
		return patient.getFirstName();
	}

	@Override
	public Patient parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Collection<Patient> findpatients = this.patient.findAll();
		for(Patient patient : findpatients){
			if(patient.getFullName().equals(text)){
				return patient;
			}
		}
		throw new ParseException("Patient not found: " + text, 0);
	}

}
