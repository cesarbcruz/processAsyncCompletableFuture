package com.example.demo;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author cesar
 */
@Service
public class NotificacaoService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    public CompletableFuture<NotificacaoResultado> notificar(Integer num) {
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
            
            return new NotificacaoResultado(results, true);
        }).exceptionally(ex -> {
            logger.error("Error: ", ex);
            return new NotificacaoResultado(ex.getMessage(), false);
        });
    }

}
