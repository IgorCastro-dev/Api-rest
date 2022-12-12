package com.igoreandre.apirest1.contoller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igoreandre.apirest1.model.dto.AtividadeDTO;
import com.igoreandre.apirest1.model.entity.Atividade;
import com.igoreandre.apirest1.model.services.AtividadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizador")
public class AtividadesController {
	
	@Autowired
	AtividadeService atividadeservice;
	
	@PostMapping("/cadastraratividade")
	public ResponseEntity<Object> cadastraratividade(@RequestBody @Valid AtividadeDTO atividadedto) {
		Atividade atividade = new Atividade();
		
		atividade.setNome(atividadedto.getNome());
		atividade.setTipoatividade(atividadedto.getTipoatividade());
		atividade.setDescricao(atividadedto.getDescricao());
		atividade.setData(atividadedto.getData());
		atividade.setHora_inicial(atividadedto.getHora_inicial());
		atividade.setHora_final(atividadedto.getHora_final());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(atividadeservice.salvar(atividade));
	}
	
	@DeleteMapping("/deletaratividade/{id}")
	public ResponseEntity<Object> deletaratividade(@PathVariable(value = "id") long id){
		Optional<Atividade> atividadeOptional = atividadeservice.findById(id);
		if (!atividadeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("atividade não encontrada");
		}
		atividadeservice.deletaratividade(atividadeOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("atividade deletada com sucesso");
	}
	
	@PutMapping("/atualizaatividade/{id}")
	public ResponseEntity<Object> atualizaatividade(@PathVariable(value = "id") long id, @RequestBody @Valid AtividadeDTO atividadedto){
		Optional<Atividade> atividadeOptional = atividadeservice.findById(id);
		if (!atividadeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("atividade não encontrada");
		}
		Atividade atividade = atividadeOptional.get();
		
		atividade.setNome(atividadedto.getNome());
		atividade.setTipoatividade(atividadedto.getTipoatividade());
		atividade.setDescricao(atividadedto.getDescricao());
		atividade.setData(atividadedto.getData());
		atividade.setHora_inicial(atividadedto.getHora_inicial());
		atividade.setHora_final(atividadedto.getHora_final());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(atividadeservice.salvar(atividade));

	}
	
	@GetMapping("/mostraratividade")
	public ResponseEntity<Object> mostraratividade(){
		return ResponseEntity.status(HttpStatus.OK).body(atividadeservice.findAll());
	}
	
}
