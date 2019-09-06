package com.vikash.restraunt.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.restraunt.entities.CuisineStyle;
import com.vikash.restraunt.entities.Restraunt;
import com.vikash.restraunt.exception.handling.RestrauntNotFoundException;
import com.vikash.restraunt.repos.RestrauntRepository;

@RestController
public class RestrauntRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestrauntRestController.class);

	@Autowired
	RestrauntRepository restrauntRepository;

	@PostMapping("/create")
	@ResponseBody
	public Restraunt createRestraunt(@RequestBody Restraunt restraunt) {
		
		LOGGER.info("Creating restraunt of the following fields " + restraunt);
		Restraunt response = restrauntRepository.save(restraunt);
		return response;

	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteRestraunt(@RequestParam("id") int id) {
		
		Optional<Restraunt> restraunt = restrauntRepository.findById(id);
		if (!restraunt.isPresent()) {
			LOGGER.error("Id not found id: " + id);
			throw new RestrauntNotFoundException("Id not found id: " + id);
		}
		
		LOGGER.info("Delete restraunt of id = " + id);
		restrauntRepository.deleteById(id);
		return "deleted successfully";
		
	}

	@GetMapping("/getAll")
	@ResponseBody
	public List<Restraunt> getAllRestraunt() {
		LOGGER.info("Get all the restraunt");
		List<Restraunt> restraunts = restrauntRepository.findAll();
		return restraunts;
	}

	@GetMapping("/name/{name}")
	@ResponseBody
	public Optional<Restraunt> searchByName(@PathVariable("name") String name) {
		
		LOGGER.info("Search restraunt by restraunt name " + name);
		Optional<Restraunt> restraunt = restrauntRepository.findByName(name);
		if (!restraunt.isPresent()) {
			LOGGER.error("Name not found name: " + name);
			throw new RestrauntNotFoundException("Name not found name: " + name);
		}
		return restraunt;
	}

	@GetMapping("/city/{city}")
	@ResponseBody
	public List<Restraunt> searchByCity(@PathVariable("city") String city) {

		LOGGER.info("Search restraunt by restraunt city " + city);
		List<Restraunt> restraunts = restrauntRepository.findByCity(city);
		if (restraunts.size() == 0) {
			LOGGER.info("No data restraunt present in " + city);
			throw new RestrauntNotFoundException("No data restraunt present in " + city);
		}
		return restraunts;
	}

	@GetMapping("/sort/ranking")
	@ResponseBody
	public List<Restraunt> sortByRanking() {
		
		LOGGER.info("Sort restraunt by restraunt ranking");
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
	@ResponseBody
	public List<Restraunt> sortByRating() {
		
		LOGGER.info("Sort restraunt by restraunt rating");
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
	@ResponseBody
	public Set<Restraunt> filterByStyle(@RequestBody CuisineStyle style) {
		
		LOGGER.info("Filter Cuisine Style of restraunt " + style);
		List<String> styles = style.getCstyle();
		List<Restraunt> restraunts = restrauntRepository.findAll();
		
		Set<Restraunt> results = new HashSet<>();
		
		for (Restraunt r : restraunts) {
			
			for (String s : styles) {
				
				if (r.getCuisineStyle().contains(s))
					results.add(r);
				
			}
			
		}
		
		if (results.size() == 0) {
			
			LOGGER.info("No data restraunt serve " + style + " style");
			throw new RestrauntNotFoundException("No data restraunt serve " + style + " style");
		}
		
		return results;
	}

}
