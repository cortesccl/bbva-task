package es.bbva.task.module.service;

import es.bbva.task.module.entities.Vwap;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los objetos Vwap
 */
public interface VwapService {
    Vwap create(Vwap vwap);
    List<Vwap> findAllByInstrumentId(Long instrumentId);
    List<Vwap> findAllByMarketId(Long marketId);
    Vwap findAllByInstrumentIdAndMarketId(Long instrumentId, Long marketId);
    Optional<Vwap> findOne(Long id);
    Vwap save(Vwap vwap);
    Vwap update(Vwap vwap);
    Vwap findById(Long id);
    void delete(Long id);
}
