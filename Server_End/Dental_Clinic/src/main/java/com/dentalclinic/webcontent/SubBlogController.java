package com.dentalclinic.webcontent;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/blog/{blogId}/sublog")
public class SubBlogController {
	
	private static final String VIEWS_SubBLOG_CREATE_OR_UPDATE_FORM = "blog/createOrUpdateSubBlogForm";
	private final SubBlogRepository subblogRp;
	private final BlogRepository blogRp;

	public SubBlogController(SubBlogRepository subBlogService, BlogRepository blogService){
		this.subblogRp = subBlogService;
		this.blogRp = blogService;
	}
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	
	@ModelAttribute("bog")
	public Blog findDentist(@PathVariable("blogId") int blogId){
		return this.blogRp.findById(blogId);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String initUpdateForm(Blog blog, ModelMap model){
    	SubBlog subBlog = new SubBlog();
    	blog.setSubBlogs(subBlog);
    	model.put("subBlog", subBlog);
    	return VIEWS_SubBLOG_CREATE_OR_UPDATE_FORM;
    }
    
    @RequestMapping(value="/add", method = RequestMethod.POST) 
    public String processUpdateForm(Blog blog, @Valid SubBlog subBlog, BindingResult result, ModelMap model){
    	if(result.hasErrors()){
    		model.put("subBlog", subBlog);
    		return VIEWS_SubBLOG_CREATE_OR_UPDATE_FORM;
    	}else{
    		blog.setSubBlogs(subBlog);
    		this.subblogRp.save(subBlog);
    		return "redirect:/blog/{blogId}";
    	}
    }
}
