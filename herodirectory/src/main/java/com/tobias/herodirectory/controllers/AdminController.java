package com.tobias.herodirectory.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tobias.herodirectory.models.AdminLoginAttempt;
import com.tobias.herodirectory.models.AdminUser;
import com.tobias.herodirectory.models.HeroUser;
import com.tobias.herodirectory.services.AdminUserService;
import com.tobias.herodirectory.services.GenderService;
import com.tobias.herodirectory.services.HeroRankService;
import com.tobias.herodirectory.services.HeroSpecialtyService;
import com.tobias.herodirectory.services.HeroUserService;
import com.tobias.herodirectory.services.UploadedImageService;

@Controller
public class AdminController {

	@Autowired
	private AdminUserService adminServ;

	@Autowired
	private HeroUserService heroServ;

	@Autowired
	private GenderService genderServ;

	@Autowired
	private HeroRankService heroRankServ;

	@Autowired
	private HeroSpecialtyService heroSpecialtyServ;

	@Autowired
	private UploadedImageService uploadedImageServ;

	// admin login page
	@GetMapping("/admin")
	public String _adminLoginPage(@ModelAttribute("newAdminLoginAttempt") AdminLoginAttempt adminLoginAttempt,
			HttpSession session) {
		// if admin is already logged in, send to admin home page
		Long loggedInAdminID = (Long) session.getAttribute("admin_id");
		if (loggedInAdminID != null)
			return "redirect:/admin/home";

		return "adminlogin.jsp";
	}

	// admin login submit
	@PostMapping("/admin")
	public String _adminLoginSubmit(@Valid @ModelAttribute("newAdminLoginAttempt") AdminLoginAttempt adminLoginAttempt,
			BindingResult result, HttpSession session) {
		// validate the login in the service
		AdminUser admin = adminServ.login(adminLoginAttempt, result);

		if (result.hasErrors())
			return "adminlogin.jsp";

		// log in the admin
		session.setAttribute("admin_id", admin.getId());

		return "redirect:/admin/home";
	}

	// admin home = create hero page
	@GetMapping("/admin/home")
	public String _adminHome(@ModelAttribute("newHero") HeroUser heroUser, HttpSession session, Model model) {
		// route guard
		Long loggedInAdminID = (Long) session.getAttribute("admin_id");
		if (loggedInAdminID == null)
			return "redirect:/admin";

		model.addAttribute("allGenders", genderServ.readAll());
		model.addAttribute("allRanks", heroRankServ.readAll());
		model.addAttribute("allSpecialties", heroSpecialtyServ.readAll());
		model.addAttribute("recentHeroes", heroServ.readRecent20());

		return "createhero.jsp";
	}

	// create hero submit
	@PostMapping("/admin/home")
	public String _adminCreateHeroSubmit(@Valid @ModelAttribute("newHero") HeroUser heroUser, BindingResult result,
			HttpSession session, Model model, @RequestParam("pic") MultipartFile file) {
		// route guard
		Long loggedInAdminID = (Long) session.getAttribute("admin_id");
		if (loggedInAdminID == null)
			return "redirect:/admin";

		// try create hero with additional validation
		heroServ.create(heroUser, result, file);

		if (result.hasErrors()) {
			model.addAttribute("allGenders", genderServ.readAll());
			model.addAttribute("allRanks", heroRankServ.readAll());
			model.addAttribute("allSpecialties", heroSpecialtyServ.readAll());
			model.addAttribute("recentHeroes", heroServ.readRecent20());

			return "createhero.jsp";
		}

		return "redirect:/admin/home";
	}

	// admin logout
	@GetMapping("/admin/logout")
	public String _adminLogout(HttpSession session) {
		session.removeAttribute("admin_id");

		return "redirect:/admin";
	}
}
