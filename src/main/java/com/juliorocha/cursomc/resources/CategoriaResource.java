package com.juliorocha.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.dto.CategoriaDTO;
import com.juliorocha.cursomc.services.CategoriaService;

@RestController //controlador rest
@RequestMapping(value="/categorias") //Responde por esse end point
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //O end point receberá um id por parametro
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { //anotação diz que o id é recebido via url

		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj); //objeto recebe pq a operação save retorna um objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		//pega a URI do novo recurso que foi inserido, padrão de Eng. de Soft.
		
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //O end point receberá um id por parametro
	public ResponseEntity<Void> delete(@PathVariable Integer id) { //anotação diz que o id é recebido via url
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) //O end point receberá um id por parametro
	public ResponseEntity<List<CategoriaDTO>> findAll() { 
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	
}
