package es.bbva.task.module.service.impl;

import es.bbva.task.module.entities.Instrument;
import es.bbva.task.module.repositories.InstrumentRepository;
import es.bbva.task.module.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentRepository repository;

    @Override
    public Optional<Instrument> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Instrument findById(Long id) {
        Optional<Instrument> instrumentOptional = repository.findById(id);
        return instrumentOptional.orElse(null);
    }

    @Override
    public Optional<Instrument> findOneByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Instrument findByName(String name) {
        Optional<Instrument> instrumentOptional = repository.findByName(name);
        return instrumentOptional.orElse(null);
    }

    @Override
    public List<Instrument> findAllInstruments() {
        return (List<Instrument>)repository.findAll();
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        Optional<Instrument> instrumentOptional = repository.findByName(instrument.getName());
        if (instrumentOptional.isEmpty()) {
            return repository.save(instrument);
        }
        return null;
    }

    @Override
    public Instrument updateInstrument(Long id, Instrument instrument) {
        Instrument instrumentBBdd = findById(id);
        if (instrumentBBdd != null && !instrument.getName().equals(instrumentBBdd.getName()) && !repository.existsByName(instrument.getName())) {
            instrumentBBdd.setName(instrument.getName());
            Optional.ofNullable(instrument.getVwaps()).ifPresent(vwaps -> {
                instrument.clearVwap();
                vwaps.forEach(vwap ->
                    instrument.addVwap(vwap)
                );
            });
            return repository.save(instrumentBBdd);
        }
        return null;
    }

    @Override
    public long countAllInstruments() {
        return repository.count();
    }

    @Override
    public void deleteInstrument(Long id) {
        repository.deleteById(id);
    }
}
