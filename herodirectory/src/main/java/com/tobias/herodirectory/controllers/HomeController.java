package com.tobias.herodirectory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tobias.herodirectory.services.HeroUserService;

@Controller
public class HomeController {
	
	@Autowired
	private HeroUserService heroServ;
	
	@GetMapping("/")
	public String _index(Model model) {
		
		model.addAttribute("recentHeroes",heroServ.readRecent5());
		
		return "index.jsp";
	}
}
