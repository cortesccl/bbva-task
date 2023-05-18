package es.bbva.task.module.repositories;

import es.bbva.task.module.entities.Instrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

    Optional<Instrument> findByName(String name);

    boolean existsByName(String name);

//    @Query("select i from Instrument i, Vwap v where i.id = v.instrumentId and v.marketId = :marketId order by m.id asc")
//    List<Instrument> lookUpAllByMarketId(Long marketId);
}
