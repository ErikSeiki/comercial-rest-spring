package br.com.erik.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	@Autowired
	private OportunidadeRepository oportunidadeRepositoy;
	
	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidadeRepositoy.findAll();
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepositoy.findById(id);
		
		if(oportunidade.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		return oportunidadeRepositoy.save(oportunidade);
	}
	
	
}
