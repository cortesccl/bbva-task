package es.bbva.task.module.service;

import es.bbva.task.module.entities.Vwap;

/**
 * Servicio simulado encargado de las simulaciones de mercado
 */
public interface CalculatorService {

    public String applyMarketUpdate(Long instrumentId, Long marketId, Vwap vwapMarketPrice);
}
