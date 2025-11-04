/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projeto.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import com.example.projeto.model.Produto;
import com.example.projeto.repository.ProdutoRepository;

/**
 *
 * @author henry
 */

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Double buscarCotacaoDolar() {
        return CotacaoService.buscarCotacaoDolar();
 
    }
}
