package com.tobias.herodirectory.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tobias.herodirectory.models.AdminUser;
import com.tobias.herodirectory.models.Gender;
import com.tobias.herodirectory.models.HeroRank;
import com.tobias.herodirectory.models.HeroSpecialty;
import com.tobias.herodirectory.services.AdminUserService;
import com.tobias.herodirectory.services.GenderService;
import com.tobias.herodirectory.services.HeroRankService;
import com.tobias.herodirectory.services.HeroSpecialtyService;

@Controller
public class SuperAdminController {

	@Autowired
	private AdminUserService adminServ;

	@Autowired
	private GenderService genderServ;
	
	@Autowired
	private HeroRankService heroRankServ;
	
	@Autowired
	private HeroSpecialtyService heroSpecialtyServ;

	// super-admin portal with links to different routes
	@GetMapping("/super-admin")
	public String _home() {
		return "superadmin_home.jsp";
	}

	// AdminUser view all / create page
	@GetMapping("/super-admin/admin")
	public String _adminPage(@ModelAttribute("newAdmin") AdminUser adminUser,
			Model model) {
		List<AdminUser> allAdmins = adminServ.readAll();
		model.addAttribute("allAdmins", allAdmins);
		return "admin.jsp";
	}

	// AdminUser create submit
	@PostMapping("/super-admin/admin")
	public String _adminCreateSubmit(@Valid @ModelAttribute("newAdmin") AdminUser adminUser,
			BindingResult result, Model model) {
		// do further validation checks in the service, creates if no errors
		adminServ.create(adminUser, result);

		// re-render the page with errors if there were errors
		if (result.hasErrors()) {
			List<AdminUser> allAdmins = adminServ.readAll();
			model.addAttribute("allAdmins", allAdmins);
			return "admin.jsp";
		}

		return "redirect:/super-admin/admin";
	}

	// Gender view all / create page
	@GetMapping("super-admin/gender")
	public String _genderPage(@ModelAttribute("newGender") Gender gender, Model model) {
		List<Gender> allGenders = genderServ.readAll();
		model.addAttribute("allGenders", allGenders);
		return "gender.jsp";
	}

	// Gender create submit
	@PostMapping("/super-admin/gender")
	public String _genderCreateSubmit(@Valid @ModelAttribute("newGender") Gender gender, BindingResult result,
			Model model) {
		// do further validation checks in the service, creates if no errors
		genderServ.create(gender, result);

		// re-render the page with errors if there were errors
		if (result.hasErrors()) {
			List<Gender> allGenders = genderServ.readAll();
			model.addAttribute("allGenders", allGenders);
			return "gender.jsp";
		}

		return "redirect:/super-admin/gender";
	}
	
	// HeroRank view all / create page
	@GetMapping("super-admin/hero-rank")
	public String _heroRankPage(@ModelAttribute("newHeroRank") HeroRank heroRank, Model model) {
		List<HeroRank> allRanks = heroRankServ.readAll();
		model.addAttribute("allRanks", allRanks);
		return "herorank.jsp";
	}

	// HeroRank create submit
	@PostMapping("/super-admin/hero-rank")
	public String _heroRankCreateSubmit(@Valid @ModelAttribute("newHeroRank") HeroRank heroRank, BindingResult result,
			Model model) {
		// do further validation checks in the service, creates if no errors
		heroRankServ.create(heroRank, result);

		// re-render the page with errors if there were errors
		if (result.hasErrors()) {
			List<HeroRank> allRanks = heroRankServ.readAll();
			model.addAttribute("allRanks", allRanks);
			return "herorank.jsp";
		}

		return "redirect:/super-admin/hero-rank";
	}
	
	// HeroSpecialty view all / create page
	@GetMapping("super-admin/hero-specialty")
	public String _heroSpecialtyPage(@ModelAttribute("newHeroSpecialty") HeroSpecialty heroSpecialty, Model model) {
		List<HeroSpecialty> allSpecialties = heroSpecialtyServ.readAll();
		model.addAttribute("allSpecialties", allSpecialties);
		return "herospecialty.jsp";
	}

	// HeroSpecialty create submit
	@PostMapping("/super-admin/hero-specialty")
	public String _heroSpecialtyCreateSubmit(@Valid @ModelAttribute("newHeroSpecialty") HeroSpecialty heroSpecialty, BindingResult result,
			Model model) {
		// do further validation checks in the service, creates if no errors
		heroSpecialtyServ.create(heroSpecialty, result);

		// re-render the page with errors if there were errors
		if (result.hasErrors()) {
			List<HeroSpecialty> allSpecialties = heroSpecialtyServ.readAll();
			model.addAttribute("allSpecialties", allSpecialties);
			return "herospecialty.jsp";
		}

		return "redirect:/super-admin/hero-specialty";
	}
	
	
}
