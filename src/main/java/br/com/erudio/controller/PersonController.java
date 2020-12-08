package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;

	// @RequestMapping(method=RequestMethod.GET
	// produces = MediaType.APPLICATION_JSON_VALUE OBS: por convecao o produces e
	// consumes ja vem)
	@GetMapping
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	// @RequestMapping(value="/{id}",
	// method=RequestMethod.GET
	// produces = MediaType.APPLICATION_JSON_VALUE)

	@GetMapping(value = "/{id}")
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	// @RequestMapping(method=RequestMethod.POST
	// consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	// @RequestMapping(method=RequestMethod.PUT
	// consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}

	// @RequestMapping(value="/{id}",
	// method=RequestMethod.DELETE)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();

	}

}