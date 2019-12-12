/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ExemploService {

    private static final Logger logger = LoggerFactory.getLogger(ExemploService.class);

    private final NotificacaoService service;

    public ExemploService(NotificacaoService gitHubLookupService) {
        this.service = gitHubLookupService;
    }

    public Integer run() throws Exception {


        logger.info("PREPARAÇÂO DADOS PARA TESTE");
        List<Integer> lista = getMockList();

        logger.info("INICIO");
        List<CompletableFuture<String>> completableFutures
                = lista.stream().map(num -> service.notificar(num)).collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        CompletableFuture<List<String>> allCompletableFuture = allFutures.thenApply(future -> {
            return completableFutures.stream()
                    .map(completableFuture -> completableFuture.join())
                    .collect(Collectors.toList());
        });
        logger.info("Executando...");
        // Aguardando todos finalizar
        List<String> resultAll = allCompletableFuture.get(5, TimeUnit.MINUTES);
        logger.info("Total Processados: " + resultAll.size());

        // Obtendo falhas
        CompletableFuture<Long> countFalha = allCompletableFuture.thenApply(pageContents -> {
            return pageContents.stream()
                    .filter(Objects::isNull)
                    .count();
        });

        logger.info("Total Falha: " + countFalha.get());

        return resultAll.size();

    }

    private List<Integer> getMockList() {
        List<Integer> mockList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            mockList.add(i);
        }

        return mockList;
    }

}
