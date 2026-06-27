package com.lesson.memo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.security.AdminDetailService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AdminDetailService adminDetailService;
	
	@GetMapping("/signin")
	public String signin() {
		return "admin-signin";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin-signup";
	}
	
	@PostMapping("/signup")
    public String create(@ModelAttribute @Valid Admin admin,
            BindingResult result) {

		if (result.hasErrors()) {
			return "admin-signup";
		}
		
		adminDetailService.register(admin);
		
        return "redirect:/admin/signin";
    }
}
