package es.bbva.task.module.service.impl;

import es.bbva.task.module.entities.Instrument;
import es.bbva.task.module.entities.Vwap;
import es.bbva.task.module.repositories.VwapRepository;
import es.bbva.task.module.service.InstrumentService;
import es.bbva.task.module.service.MarketService;
import es.bbva.task.module.service.VwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VwapServiceImpl implements VwapService {

    @Autowired
    private MarketService marketService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private VwapRepository vwapRepository;

    @Override
    public Vwap create(Vwap vwap) {
        Optional.ofNullable(vwap.getMarket()).ifPresent(market ->
                vwap.setMarket(marketService.findById(market.getId())));
        return vwap;
    }

    @Override
    public Vwap save(Vwap vwap) {
        Instrument instrument = instrumentService.findById(vwap.getInstrument().getId());

        Optional.ofNullable(vwap.getMarket()).ifPresent(marketDto ->
                vwap.setMarket(marketService.findById(marketDto.getId())));
        instrument.addVwap(vwap);
        vwapRepository.save(vwap);
        instrumentService.saveInstrument(instrument);
        return vwapRepository.save(vwap);
    }

    @Override
    public Vwap update(Vwap vwap) {
        Instrument instrument = instrumentService.findById(vwap.getInstrument().getId());
        Vwap vwapBBdd = findById(vwap.getId());
        vwapBBdd.setBidAmount(vwap.getBidAmount());
        vwapBBdd.setBidPrice(vwap.getBidPrice());
        vwapBBdd.setOfferAmount(vwap.getOfferAmount());
        vwapBBdd.setOfferPrice(vwap.getOfferPrice());

        Optional.ofNullable(vwap.getMarket()).ifPresent(marketDto ->
                vwap.setMarket(marketService.findById(marketDto.getId())));
        instrument.addVwap(vwap);
        vwapRepository.save(vwap);
        instrumentService.saveInstrument(instrument);
        return vwapRepository.save(vwap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vwap> findAllByInstrumentId(Long instrumentId) {
        return vwapRepository.findAllByInstrumentId(instrumentId);
    }

    @Override
    public List<Vwap> findAllByMarketId(Long marketId) {
        return vwapRepository.findAllByMarketId(marketId);
    }

    @Override
    public Vwap findAllByInstrumentIdAndMarketId(Long instrumentId, Long marketId) {
        return vwapRepository.findByInstrumentIdAndMarketId(instrumentId, marketId);
    }

    @Override
    public Optional<Vwap> findOne(Long id) {
        return vwapRepository.findById(id);
    }

    @Override
    public Vwap findById(Long id) {
        return vwapRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Vwap vwap = findById(id);
        Instrument instrument = instrumentService.findById(vwap.getInstrument().getId());
        instrument.removeVwap(findById(id));
        instrumentService.saveInstrument(instrument);
        vwapRepository.deleteById(id);
    }
}
