package com.dentalclinic.webcontent;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dentalclinic.model.BaseEntity;

@Entity
@Table(name = "blog")
public class Blog extends BaseEntity{

	@NotEmpty
    @Column(name = "title")
    private String title;
	
	@NotEmpty
	@Column(name="keywords")
	private String keywords;
	
	@Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
	
	@NotEmpty
	@Column(name="content")
	private String content;
	
	@Column(name="images")
	private String images;
	
	@ManyToOne
    @JoinColumn(name = "type_id")
	private BlogType bype;
}
