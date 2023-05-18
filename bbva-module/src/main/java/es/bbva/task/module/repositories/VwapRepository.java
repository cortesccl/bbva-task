package es.bbva.task.module.repositories;

import es.bbva.task.module.entities.Vwap;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VwapRepository extends CrudRepository<Vwap, Long> {

    List<Vwap> findAllByInstrumentId(Long instrumentId);
    List<Vwap> findAllByMarketId(Long marketId);
    Vwap findByInstrumentIdAndMarketId(Long instrumentId, Long marketId);
}
