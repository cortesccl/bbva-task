package es.bbva.task.module.service.impl;

import es.bbva.task.module.entities.Instrument;
import es.bbva.task.module.entities.Market;
import es.bbva.task.module.entities.Vwap;
import es.bbva.task.module.service.CalculatorService;
import es.bbva.task.module.service.InstrumentService;
import es.bbva.task.module.service.MarketService;
import es.bbva.task.module.service.VwapService;
import es.bbva.task.module.service.dtos.VwapAsFraction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private VwapService vwapService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private MarketService marketService;

    @Override
    public String applyMarketUpdate(Long instrumentId, Long marketId, Vwap vwapMarketPrice) {

        Instrument instrument = instrumentService.findById(instrumentId);
        Market market = marketService.findById(marketId);

        Vwap vwap = vwapService.findAllByInstrumentIdAndMarketId(instrumentId, marketId);

        VwapAsFraction vwapAsFraction = VwapAsFraction.builder().build();

        vwapAsFraction.initProperties(vwap.getBidAmount(), vwap.getBidPrice(), vwap.getOfferAmount(), vwap.getOfferPrice());
        vwapAsFraction.loadProperties(vwapMarketPrice.getBidAmount(), vwapMarketPrice.getBidPrice(), vwapMarketPrice.getOfferAmount(), vwapMarketPrice.getOfferPrice());

        vwap.setBidPrice(vwapAsFraction.computeVwapForBid());
        vwap.setBidAmount(vwapAsFraction.getTotalBidAmount());
        vwap.setOfferPrice(vwapAsFraction.computeVwapForOffer());
        vwap.setOfferAmount(vwapAsFraction.getTotalOfferAmount());
        vwapService.update(vwap);

        return String.format("[%s - %s]-> %s", instrument.getName(), market.getName(), vwapAsFraction.toString());
    }
}
