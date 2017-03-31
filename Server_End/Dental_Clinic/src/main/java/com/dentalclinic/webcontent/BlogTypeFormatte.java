/**
 * 
 */
package com.dentalclinic.webcontent;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * @author fanfan
 *
 */
@Component
public class BlogTypeFormatte implements Formatter<BlogType>{
	
	private final BlogTypeRepository blogtype;
	
	@Autowired
	public BlogTypeFormatte(BlogTypeRepository type){
		this.blogtype = type;
	}

	@Override
	public String print(BlogType blogtype, Locale locale) {
		// TODO Auto-generated method stub
		return blogtype.getName();
	}

	@Override
	public BlogType parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Collection<BlogType> findBlogTypes = this.blogtype.findAll();
		for(BlogType type : findBlogTypes) {
			if(type.getName().equals(text)){
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
	
	
}	
