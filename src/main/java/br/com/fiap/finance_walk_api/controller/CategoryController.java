package br.com.fiap.finance_walk_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.finance_walk_api.model.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Category> repository = new ArrayList<>();

    @GetMapping
    public List<Category> index() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        log.info("Cadastrando categoria " + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    @GetMapping("{id}")
    public Category get(@PathVariable Long id) {
        log.info("Buscando categoria " + id);
        return getCategory(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando categoria " + id);
        repository.remove(getCategory(id));
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria " + id + " " + category);	

        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);

        return category;
    }

    private Category getCategory(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria " + id + " não encontrada")
                );
    }
}
