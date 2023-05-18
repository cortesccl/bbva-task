package es.bbva.task.module.repositories;

import es.bbva.task.module.entities.Market;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarketRepository extends CrudRepository<Market, Long> {

    Optional<Market> findByName(String name);

    boolean existsByName(String name);

//    @Query("select m from Market m, Vwap v where m.id = v.marketId and v.instrumentId = :instrumentId order by m.id asc")
//    List<Market> lookUpAllByInstrumentId(Long instrumentId);
}
