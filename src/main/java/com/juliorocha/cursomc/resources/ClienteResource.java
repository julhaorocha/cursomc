package com.juliorocha.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juliorocha.cursomc.domain.Cliente;
import com.juliorocha.cursomc.services.ClienteService;

@RestController //controlador rest
@RequestMapping(value="/clientes") //Responde por esse end point
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //O end point receberá um id por parametro
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { //anotação diz que o id é recebido via url

		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
}
