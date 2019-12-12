package com.example.demo;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author cesar
 */
@Service
public class NotificacaoService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    public CompletableFuture<String> notificar(Integer num) {
        return CompletableFuture.supplyAsync(() -> {
            String results = "<" + num + ">";

            // faz o que deve ser feito !!!
            // ....
            
            if (num == 10 || num == 500) {
                throw new RuntimeException("Fodeu: " + num);
            }
            
            if (num == 11) {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }
             if (num == 12) {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }

            logger.info("OK: " + results);
            
            return results;
        }).exceptionally(ex -> {
            logger.error("Something went wrong : ", ex);
            return null;
        });
    }

}
