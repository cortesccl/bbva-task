package es.bbva.task.module.service;

import es.bbva.task.module.entities.Market;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los mercados
 */
public interface MarketService {
    Optional<Market> findOne(Long id);
    Market findById(Long id);
    Optional<Market> findOneByName(String name);
    Market findByName(String name);
    List<Market> findAllMarkets();
    Market saveMarket(Market market);
    Market updateMarket(Long id, Market market);
    long countAllMarkets();
    void deleteMarket(Long id);
}
