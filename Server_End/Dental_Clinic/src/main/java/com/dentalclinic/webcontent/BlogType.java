package com.dentalclinic.webcontent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dentalclinic.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name = "blog_type")
public class BlogType extends NamedEntity{
	
	@OneToMany
	private Set<Blog> blogs = new HashSet<Blog>();
	
	public Set<Blog> getBlogs(){
		return this.blogs;
	}
	public void setBlogs(Blog blog){
		this.blogs.add(blog);
	}
}
