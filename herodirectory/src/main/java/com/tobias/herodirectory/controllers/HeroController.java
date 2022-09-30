package com.tobias.herodirectory.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tobias.herodirectory.services.HeroUserService;

@Controller
public class HeroController {
	
	@Autowired
	private HeroUserService heroServ;
	
	@GetMapping("/heroes")
	public String _allheroes(Model model, HttpSession session) {
		model.addAttribute("allHeroes", heroServ.readAll());
		return "allheroes.jsp";
	}
}
