package com.dentalclinic.webcontent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/blog")
public class BlogController {
	
	private static final String VIEWS_BLOG_CREATE_OR_UPDATE_FORM = "blog/createOrUpdateBlogForm";
	private final BlogRepository blogRp;
	private final BlogTypeRepository blogtypeRp;
	
	@Autowired
	public BlogController(BlogRepository Blogservice, BlogTypeRepository BlogtypeService){
		this.blogRp = Blogservice;
		this.blogtypeRp = BlogtypeService;
	}
	
	@ModelAttribute("blogtypes")
	public Collection<BlogType> populateBlogtype() {
		return this.blogtypeRp.findAll();
	}
	
	@GetMapping(path = "")
	public String BlogList(Map<String, Object> model){
		List<Blog> blogs = new ArrayList<>();
		blogs.addAll(this.blogRp.findAll());
		model.put("blogs", blogs);		
		return "blog/bloglist";
	}
	
	@GetMapping(path = "blogs.json")
	public @ResponseBody List<Blog> BlogSourceList(Map<String, Object> model){
		List<Blog> blogs = new ArrayList<>();
		blogs.addAll(this.blogRp.findAll());		
		return blogs;
	}
		
	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String initCreationForm(Map<String, Object> model) {
		Blog blog = new Blog();
		model.put("blog", blog);
		return VIEWS_BLOG_CREATE_OR_UPDATE_FORM;
	} 
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Blog blog, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_BLOG_CREATE_OR_UPDATE_FORM;
        } else {
            this.blogRp.save(blog);
            return "redirect:/blog/" + blog.getId();
        }
	}
	
	@RequestMapping("/{blogId}")
	public ModelAndView showDentist(@PathVariable("blogId") int blogId){
		ModelAndView mav = new ModelAndView("blog/blogDetails");
		mav.addObject(this.blogRp.findById(blogId));
		return mav;
	}

}
