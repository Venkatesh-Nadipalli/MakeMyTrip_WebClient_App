package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.bindingfroms.Passenger;
import in.ashokit.bindingfroms.ticket;
import in.ashokit.service.MakeMyTripService;
import reactor.core.publisher.Mono;

@Controller
public class MakeMyTripcontroller {

	@Autowired
	 private MakeMyTripService service;
	
	@GetMapping("/")
	public String index(Model model) {
		
		Mono<ticket[]> alltickets = service.getalltickets();
		model.addAttribute("tickets",alltickets);
		return "index";
	}
	
	@GetMapping("/book-ticket")
	public String bookticket(Model model) {
		
		model.addAttribute("p", new Passenger());
		model.addAttribute("ticket", new ticket());
		return "bookticket";
	}
	
	@PostMapping("/ticket") 
	public String ticketbook(@ModelAttribute("p") Passenger p,Model model) {
	Mono<ticket>	tckt = service.bookticket(p);
	model.addAttribute("ticket",tckt);
	model.addAttribute("msg","your ticket is confirmed");
	return "bookticket";
	}
	
}
