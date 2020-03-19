package com.juliorocha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired //Instacia automaticamente por injeção de controle e inversão de dependencia
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		}
}
