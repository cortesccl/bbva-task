package es.bbva.task.module.service.impl;

import es.bbva.task.module.entities.Market;
import es.bbva.task.module.repositories.MarketRepository;
import es.bbva.task.module.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository repository;

    @Override
    public Optional<Market> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Market findById(Long id) {
        Optional<Market> marketOptional = repository.findById(id);
        return marketOptional.orElse(null);
    }

    @Override
    public Optional<Market> findOneByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Market findByName(String name) {
        Optional<Market> marketOptional = repository.findByName(name);
        return marketOptional.orElse(null);
    }

    @Override
    public List<Market> findAllMarkets() {
        return (List<Market>)repository.findAll();
    }

    @Override
    public Market saveMarket(Market market) {
        Optional<Market> marketOptional = repository.findByName(market.getName());
        if (marketOptional.isEmpty()) {
            return repository.save(market);
        }
        return null;
    }

    @Override
    public Market updateMarket(Long id, Market market) {
        Market marketBBdd = findById(id);
        if (marketBBdd != null && !market.getName().equals(marketBBdd.getName()) && !repository.existsByName(market.getName())) {
            marketBBdd.setName(market.getName());
            return repository.save(marketBBdd);
        }
        return null;
    }

    @Override
    public long countAllMarkets() {
        return repository.count();
    }

    @Override
    public void deleteMarket(Long id) {
        repository.deleteById(id);
    }
}
