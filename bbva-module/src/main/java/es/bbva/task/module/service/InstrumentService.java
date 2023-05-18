package es.bbva.task.module.service;

import es.bbva.task.module.entities.Instrument;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los instrumentos
 */
public interface InstrumentService {
    Optional<Instrument> findOne(Long id);
    Instrument findById(Long id);
    Optional<Instrument> findOneByName(String name);
    Instrument findByName(String name);
    List<Instrument> findAllInstruments();
    Instrument saveInstrument(Instrument instrument);
    Instrument updateInstrument(Long id, Instrument instrument);
    long countAllInstruments();
    void deleteInstrument(Long id);
}
