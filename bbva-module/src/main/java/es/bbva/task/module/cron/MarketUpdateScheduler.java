package es.bbva.task.module.cron;

import es.bbva.task.module.entities.Vwap;
import es.bbva.task.module.service.CalculatorService;
import es.bbva.task.module.service.InstrumentService;
import es.bbva.task.module.service.MarketService;
import es.bbva.task.module.utils.RandomUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MarketUpdateScheduler {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private InstrumentService instrumentService;

    @Scheduled(fixedDelay = 40000)
    public void scheduleMarketUpdatesTask() {
        try {
            createCompletableFuture();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createCompletableFuture () throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        int marketSize = Long.valueOf(marketService.countAllMarkets()).intValue();
        int instrumentsSize = Long.valueOf(instrumentService.countAllInstruments()).intValue();
        for (int i = 1; i  <= marketSize; i++) {
            for (int j = 1; j  <= instrumentsSize; j++) {
                Long marketId = Long.valueOf(Integer.valueOf(i).longValue());
                Long instrumentId = Long.valueOf(Integer.valueOf(j).longValue());
                Vwap vwap = RandomUtilities.createRandomVwap();
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> calculatorService.applyMarketUpdate(instrumentId, marketId, vwap));
                futures.add(future);
            }
        }
        log.info("\n");
        String result = futures.stream().map(CompletableFuture::join).collect(Collectors.joining("\n"));
        log.info(result);
        log.info("\n");
    }
}
