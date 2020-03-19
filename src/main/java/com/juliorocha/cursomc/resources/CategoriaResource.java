package com.juliorocha.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.services.CategoriaService;

@RestController //controlador rest
@RequestMapping(value="/categorias") //Responde por esse end point
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //O end point receberá um id por parametro
	public ResponseEntity<?> find(@PathVariable Integer id) { //anotação diz que o id é recebido via url

		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
}
