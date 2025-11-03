/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projeto.controller;

import com.example.projeto.model.Produto;
import com.example.projeto.service.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author henry
 */

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", service.listarTodos());
        model.addAttribute("cotacao", service.buscarCotacaoDolar());
        return "produtos/listar";
    }

    // Novo produto
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("cotacao", service.buscarCotacaoDolar()); // 👈 adiciona a cotação
        return "produtos/form";
    }

    // Editar produto existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto p = service.buscarPorId(id);
        if (p == null) {
            return "redirect:/produtos";
        }
        model.addAttribute("produto", p);
        return "produtos/form";
    }

    // Salvar (tanto novo quanto edição)
    @PostMapping
    public String salvar(@Valid @ModelAttribute("produto") Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/form";
        }

        // Se tiver ID, faz update. Se não tiver, cria novo.
        if (produto.getId() != null) {
            Produto existente = service.buscarPorId(produto.getId());
            if (existente != null) {
                existente.setNome(produto.getNome());
                existente.setDescricao(produto.getDescricao());
                existente.setPreco(produto.getPreco());
                existente.setQuantidade(produto.getQuantidade());
                service.salvar(existente);
                return "redirect:/produtos";
            }
        }

        // Novo registro
        service.salvar(produto);
        return "redirect:/produtos";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}