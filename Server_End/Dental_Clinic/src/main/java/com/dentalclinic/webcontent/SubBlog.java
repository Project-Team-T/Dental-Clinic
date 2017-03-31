package com.dentalclinic.webcontent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dentalclinic.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name="subblog")
public class SubBlog extends BaseEntity{
	
	@Lob @Column(name="text")
	private String text;
	
	@Column(name="image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;
	
	public void setText(String text){
		this.text = text;
	}
	public String getText(){
		return this.text;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	
	public void setBlog(Blog blog){
		this.blog = blog;
	}
	public Blog getBlog(){
		return this.blog;
	}
}
