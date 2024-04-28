package in.ashokit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.bindingfroms.Passenger;
import in.ashokit.bindingfroms.ticket;
import reactor.core.publisher.Mono;

@Service
public class MakeMyTripService {
	
public Mono<ticket> bookticket(Passenger p) {
		
		String apiurl = "http://43.205.144.253:8080/ticket";
		
		WebClient webclient = WebClient.create();
		
	Mono<ticket> bodytomono = webclient.post()
		                              .uri(apiurl)
		                              .body(BodyInserters.fromValue(p))
		                              .retrieve()
		                              .bodyToMono(ticket.class);
		return bodytomono;
	}
	
	public Mono<ticket[]> getalltickets(){
		
		String apiurl = "http://43.205.144.253:8080/tickets";
		
		WebClient webclient = WebClient.create();
		
		 Mono<ticket[]>  tickets =   webclient.get()
		                                      .uri(apiurl)
		                                      .retrieve()
		                                      .bodyToMono(ticket[].class);
		
		return tickets;
	}
}
