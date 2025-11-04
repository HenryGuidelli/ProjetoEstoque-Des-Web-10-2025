/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projeto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;

/**
 *
 * @author henry
 */


@Service
public class CotacaoService {

    public static Double buscarCotacaoDolar() {
        try {
            String url = UriComponentsBuilder
                    .fromUriString("https://economia.awesomeapi.com.br/json/last/USD-BRL")
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.get("USDBRL") != null) {
                Map<String, String> cotacao = (Map<String, String>) response.get("USDBRL");
                return Double.parseDouble(cotacao.get("bid"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar cotação: " + e.getMessage());
        }

        return null;
    }
}