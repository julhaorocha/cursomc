package com.juliorocha.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.repositories.CategoriaRepository;
import com.juliorocha.cursomc.services.exceptions.DataIntegrityException;
import com.juliorocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //Instacia automaticamente por injeção de controle e inversão de dependencia
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //garante que no insert sempre sera um obj novo
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);//testa se existe antes de deletar
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	
	
}
