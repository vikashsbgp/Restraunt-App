package com.vikash.restraunt.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.restraunt.entities.CuisineStyle;
import com.vikash.restraunt.entities.Restraunt;
import com.vikash.restraunt.repos.RestrauntRepository;

@RestController
public class RestrauntRestController {

	@Autowired
	RestrauntRepository restrauntRepository;

	@PostMapping("/create")
	public Restraunt createRestraunt(@RequestBody Restraunt restraunt) {

		Restraunt response = restrauntRepository.save(restraunt);
		return response;

	}
	
	@DeleteMapping("/delete")
	public String deleteRestraunt(@RequestParam("id") int id) {
		restrauntRepository.deleteById(id);
		return "deleted successfully";
		
	}

	@GetMapping("/getAll")
	public List<Restraunt> getAllRestraunt() {
		List<Restraunt> restraunts = restrauntRepository.findAll();
		return restraunts;
	}

	@GetMapping("/name/{name}")
	public Restraunt searchByName(@PathVariable("name") String name) {

		Restraunt restraunt = restrauntRepository.findByName(name);
		return restraunt;
	}

	@GetMapping("/city/{city}")
	public List<Restraunt> searchByCity(@PathVariable("city") String city) {

		List<Restraunt> restraunts = restrauntRepository.findByCity(city);
		return restraunts;
	}

	@GetMapping("/sort/ranking")
	public List<Restraunt> sortByRanking() {

		List<Restraunt> restraunts = restrauntRepository.findAll();

		Comparator<Restraunt> compareByRanking = new Comparator<Restraunt>() {
			@Override
			public int compare(Restraunt o1, Restraunt o2) {

				return o1.getRanking().compareTo(o2.getRanking());
			}
		};
		Collections.sort(restraunts, compareByRanking);
		return restraunts;
	}

	@GetMapping("/sort/rating")
	public List<Restraunt> sortByRating() {
		
		List<Restraunt> restraunts = restrauntRepository.findAll();

		Comparator<Restraunt> compareByRating = new Comparator<Restraunt>() {
			@Override
			public int compare(Restraunt o1, Restraunt o2) {

				return o1.getRating().compareTo(o2.getRating());
			}
		};
		Collections.sort(restraunts, compareByRating);
		return restraunts;
	}

	@PostMapping("/filter/style")
	public Set<Restraunt> filterByStyle(@RequestBody CuisineStyle style) {
		
		List<String> styles = style.getCstyle();
		List<Restraunt> restraunts = restrauntRepository.findAll();
		
		Set<Restraunt> results = new HashSet<>();
		
		for (Restraunt r : restraunts) {
			
			for (String s : styles) {
				
				if (r.getCuisineStyle().contains(s))
					results.add(r);
				
			}
			
		}
		
		return results;
	}

}
