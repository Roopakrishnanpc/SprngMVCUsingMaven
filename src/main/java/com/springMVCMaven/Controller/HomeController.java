package com.springMVCMaven.Controller;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springMVCMaven.DAO.SprngMVCDemodDAO;
import com.springMVCMaven.Model.SpringMVCModel;
import com.springMVCMaven.Model.SprngMVCDemo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	SprngMVCDemodDAO sprngMVCDemodDAO;
//	@RequestMapping("/")
//	public String home()
//	{
//		System.out.println("Home page requested");
//		return "index.jsp";
//	}
//	@RequestMapping("/")
//	public String home()
//	{
//		System.out.println("Home page requested");
//		return "index";
//	}

	//1. Uing httpsesson & httprequest
//	@RequestMapping("add")
//	public String add(HttpServletRequest request )
//	{
//		int i=Integer.parseInt(request.getParameter("num1"));
//		int j=Integer.parseInt(request.getParameter("num2"));
//		int result=i+j;
//		//return "result.jsp?result=" +result";
//		//We can do using session usig setattribute
//		HttpSession session=request.getSession();
//		session.setAttribute("result", result);
//		return "result.jsp";
//	}
//Using RequestParam
//	@RequestMapping("add")
//	public String add(@RequestParam("num1")int i,@RequestParam("num2") int j,HttpSession session )
//	{
//
//		int result=i+j;
//
//		session.setAttribute("result", result);
//		return "result.jsp";
//	}
	//Using ModelAndView
//	@RequestMapping("add")
//	public ModelAndView add(@RequestParam("num1")int i,@RequestParam("num2") int j)
//	{
//		ModelAndView modelvew =new ModelAndView();
//		modelvew.setViewName("result.jsp");
//		int result=i+j;
//
//		modelvew.addObject("result",result);
//		return modelvew;
//	}
	//check applicatio propertes - o remove the extenstion and pathame
	//when client send request, client expect response in html or a view. we also send data
	//seo what we do is send modelandview using below class. basically our jsp converted to html after rendering
//	@RequestMapping("add")
//	public ModelAndView addMethod(@RequestParam("num1")int i,@RequestParam("num2") int j)
//	{
////		ModelAndView modelvew =new ModelAndView();
////		modelvew.setViewName("result");
//		//we can also gie directly instead of giving above line result drectly you can give
//		ModelAndView modelvew =new ModelAndView("result");
//
//		int result=i+j;
//
//		modelvew.addObject("result",result);
//		return modelvew;
//	}
	//when you want to specify both model and view then we can use modeladview, oly when you want to concentrate on model and 
	//consider view later then use model
//	@RequestMapping("add")
//	public String addMethod(@RequestParam("num1")int i,@RequestParam("num2") int j, Model m)
//	{
//
//		int result=i+j;
//		
//		m.addAttribute("result",result);
//		return "result";
//	}
	//modelmap your data will automically changed in map format
	@RequestMapping("add")
	public String addMethod(@RequestParam("num1")int i,@RequestParam("num2") int j, ModelMap m)
	{

		int result=i+j;
		
		m.addAttribute("result",result);
		return "result";
	}
	//ModelAttribte on method level
	@ModelAttribute
	public void modelData(Model m)
	{
		m.addAttribute("name","sprngMVCDemos");
		
	}
	@RequestMapping("/")
	public String home()
	{
		System.out.println("Home page requested");
		//return "index";
		
		return "index";
	}
	
	@RequestMapping("addModelAnother")
	public String addMod(@ModelAttribute("result") SprngMVCDemo springMVCdemo)
	{
		sprngMVCDemodDAO.addModelAnother(springMVCdemo);
		return "showSpringMVCData";
	}
	@PostMapping(value="addModel")//sending data
	//public String addMethod(@ModelAttribute SpringMVCModel springMVCdemo)
	public String addMethod(@ModelAttribute SpringMVCModel springMVCdemo,Model m)
//	public String addMethod(@ModelAttribute("result") SpringMVCModel springMVCModel )//Model m)
	//public String addMethod(SpringMVCModel s )//Model m)
	{
//For public String addMethod(@ModelAttribute SpringMVCModel springMVCModel , Model m) -> m.addAttribute("result",springMVCModel);
		m.addAttribute("result",springMVCdemo);
		return "result";
	}
	@GetMapping("getModels")
	public String getModel(Model m)
	{
	//List<SprngMVCDemo>	spring=Arrays.asList(new SpringMVCModel(1,"Roopa"), new SpringMVCModel(2,"Sam"));
	m.addAttribute("result", sprngMVCDemodDAO.getModels());
	//return spring.toString();
	return "showSpringMVCData";
	}
    @GetMapping("updateModel")
    public String updateModel(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name, Model m) {
    	sprngMVCDemodDAO.updateModel(id, name);

        // Set the success message in the model
        m.addAttribute("successMessage", "Entity with ID " + id + " updated successfully.");

        return "sucess";
    }
	@GetMapping("getMol")
	public String getMol(@RequestParam(name="id") int id, Model m)
	{
	m.addAttribute("result", sprngMVCDemodDAO.getMod(id));
	//return spring.toString();
	return "showSpringMVCData";
	}
    @GetMapping("/deleteAllEntities")
    public String deleteAllEntities() {
        
        // Call service method to delete all entities
    	sprngMVCDemodDAO.deleteAllEntities();

        return "sucess";  // Redirect to a success page or list page
    }
    @GetMapping("/deleteEntity")
    public String deleteEntity(@RequestParam(name="id") int id, RedirectAttributes redirectAttributes, Model model) {
        
        // Call service method to delete the entity
    	sprngMVCDemodDAO.deleteEntity(id);

    	 model.addAttribute("successMessage", "Entity with ID " + id + " deleted successfully.");
        //redirectAttributes.addFlashAttribute("message", "ID " + id + " removed successfully.");

        // Redirect to a success page or list page
        return "sucess";
    }
//	@RequestMapping("addModel")
//	public String addMethod(@RequestParam("id")int i,@RequestParam("name") String j, Model m)
//	{
//		SpringMVCModel sprngMVCDemo =new SpringMVCModel();
//		
//		sprngMVCDemo.setId(i);
//		sprngMVCDemo.setName(j);
//		m.addAttribute("result",sprngMVCDemo);
//		return "result";
//	}
	
//	@RequestMapping("addModel")
//	public String addMethod(@ModelAttribute SpringMVCModel springMVCModel )//Model m)
////	public String addMethod(@ModelAttribute("result") SpringMVCModel springMVCModel )//Model m)
//	//public String addMethod(SprngMVCDemo springMVCModel )//Model m)
//	{
//
//		//m.addAttribute("result",springMVCModel);
//		return "result";
//	}
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // Step 4: Entry point for processing the request
//        String action = request.getRequestURI();
//        if (action.endsWith("addModel")) {
//            return addMethod(request);}
//            
//           return new ModelAndView("error", "errorMessage", "Invalid action.");
//            
//    }
//    private ModelAndView addMethod(HttpServletRequest request) {
//        // Step 6: Specific logic for handling the 'addModel' action
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//
//        if (id==0 || name == null) {
//            throw new IllegalArgumentException("Id and Name are required.");
//        }
//
//        SpringMVCModel springMVCModel = new SpringMVCModel();
//        springMVCModel.setId(id);
//        springMVCModel.setName(name);
//
//        ModelAndView modelAndView = new ModelAndView("result");
//        modelAndView.addObject("result", springMVCModel);
//        
//        return modelAndView;
//    }
	

}
