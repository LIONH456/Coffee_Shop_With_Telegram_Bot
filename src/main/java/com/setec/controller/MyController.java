package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.Entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.telegramBot.MyTelegramBot;

@Controller
public class MyController {
	// http://localhost:8080/
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked(
//				1,
//				"Ho Jun Hong",
//				"098986363",
//				"junhongho@gamail.com",
//				"09/03/2025",
//				"8:30 AM",
//				5
				);
		mod.addAttribute("booked", booked);
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/service")
	public String servive() {
		return "service";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(
//				1,
//				"Ho Jun Hong",
//				"098986363",
//				"junhongho@gamail.com",
//				"09/03/2025",
//				"8:30 AM",
//				5
				);
		mod.addAttribute("booked", booked);
		return "reservation";
	}
	
	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}
	
	@Autowired
	private BookedRepo bookedRepo;
	
	@Autowired
	private MyTelegramBot bot;
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
		bookedRepo.save(booked);
		bot.message(booked.toString());
		return "success";
	}
}
