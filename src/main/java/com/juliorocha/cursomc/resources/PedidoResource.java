package com.juliorocha.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.domain.Pedido;
import com.juliorocha.cursomc.dto.CategoriaDTO;
import com.juliorocha.cursomc.services.PedidoService;

@RestController //controlador rest
@RequestMapping(value="/pedidos") //Responde por esse end point
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //O end point receberá um id por parametro
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { //anotação diz que o id é recebido via url

		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj); //objeto recebe pq a operação save retorna um objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		//pega a URI do novo recurso que foi inserido, padrão de Eng. de Soft.
		
		return ResponseEntity.created(uri).build();
	}
}
