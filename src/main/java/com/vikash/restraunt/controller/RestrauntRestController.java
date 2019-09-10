package com.vikash.restraunt.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vikash.restraunt.library.ResponseLibrary;
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
	public ResponseLibrary createRestraunt(@RequestBody Restraunt restraunt) {
		
		LOGGER.info("Creating restraunt of the following fields " + restraunt);
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt Created Successfully");
		response.setStatus(200);
		//Restraunt result = restrauntRepository.save(restraunt);
		response.setData(restrauntRepository.save(restraunt));
		return response;

	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteRestraunt(@RequestParam("id") int id) {

		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt deleted Successfully");
		response.setStatus(200);
		response.setData(null);
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
	public ResponseLibrary getAllRestraunt() {
		LOGGER.info("Get all the restraunt");
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunts Available");
		response.setStatus(200);
		//List<Restraunt> restraunts = restrauntRepository.findAll();
		response.setData(restrauntRepository.findAll());
		return response;
	}

	@GetMapping("/name/{name}")
	@ResponseBody
	public ResponseLibrary searchByName(@PathVariable("name") String name) {
		
		LOGGER.info("Search restraunt by restraunt name " + name);
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt Found");
		response.setStatus(200);
		response.setData(restrauntRepository.findByName(name));
		Optional<Restraunt> restraunt = restrauntRepository.findByName(name);
		if (!restraunt.isPresent()) {
			LOGGER.error("Name not found name: " + name);
			throw new RestrauntNotFoundException("Name not found name: " + name);
		}
		return response;
	}

	@GetMapping("/city/{city}")
	@ResponseBody
	public ResponseLibrary searchByCity(@PathVariable("city") String city) {

		LOGGER.info("Search restraunt by restraunt city " + city);
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt Found");
		response.setStatus(200);
		response.setData(restrauntRepository.findByCity(city));
		List<Restraunt> restraunts = restrauntRepository.findByCity(city);
		if (restraunts.size() == 0) {
			LOGGER.info("No data restraunt present in " + city);
			throw new RestrauntNotFoundException("No data restraunt present in " + city);
		}
		return response;
	}

	@GetMapping("/sort/ranking")
	@ResponseBody
	public ResponseLibrary sortByRanking() {
		
		LOGGER.info("Sort restraunt by restraunt ranking");

		List<Restraunt> restraunts = restrauntRepository.findAll();

		Comparator<Restraunt> compareByRanking = new Comparator<Restraunt>() {
			@Override
			public int compare(Restraunt o1, Restraunt o2) {

				return o1.getRanking().compareTo(o2.getRanking());
			}
		};
		Collections.sort(restraunts, compareByRanking);
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt in sorted order");
		response.setStatus(200);
		response.setData(restraunts);
		return response;
	}

	@GetMapping("/sort/rating")
	@ResponseBody
	public ResponseLibrary sortByRating() {
		
		LOGGER.info("Sort restraunt by restraunt rating");
		List<Restraunt> restraunts = restrauntRepository.findAll();

		Comparator<Restraunt> compareByRating = new Comparator<Restraunt>() {
			@Override
			public int compare(Restraunt o1, Restraunt o2) {

				return o1.getRating().compareTo(o2.getRating());
			}
		};
		Collections.sort(restraunts, compareByRating);
		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunt in sorted order");
		response.setStatus(200);
		response.setData(restraunts);
		return response;
	}

	@PostMapping("/filter/style")
	@ResponseBody
	public ResponseLibrary filterByStyle(@RequestBody CuisineStyle style) {
		
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

		ResponseLibrary response = new ResponseLibrary();
		response.setError(null);
		response.setMessage("Restraunts Found");
		response.setStatus(200);
		response.setData(results);
		
		return response;
	}

}
