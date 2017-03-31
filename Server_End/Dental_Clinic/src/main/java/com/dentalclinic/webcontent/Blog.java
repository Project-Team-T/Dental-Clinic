package com.dentalclinic.webcontent;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name = "blog")
public class Blog extends BaseEntity{

	@NotEmpty
    @Column(name = "title")
    private String title;
	
	@Column(name="keywords")
	private String keywords;
	
	@Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date publishdate = new Date();	
	
	@ManyToOne
    @JoinColumn(name = "type_id")
	private BlogType blogType;
	
	@OneToMany
	private Set<SubBlog> subBlogs = new HashSet<SubBlog>();
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return this.keywords;
	}
	
	public void setPublishdate(Date publishdate){
		this.publishdate = publishdate;
	}	
	public Date getPublishdate(){
		return this.publishdate;
	}
		
	public void setBlogType(BlogType blogType){
		this.blogType = blogType;
	}
	public BlogType getBlogType(){
		return this.blogType;
	}
	
	public Set<SubBlog> getSubBlogs(){
		return this.subBlogs;
	}
	public void setSubBlogs(SubBlog subBlog){
		this.subBlogs.add(subBlog);
	}
}
