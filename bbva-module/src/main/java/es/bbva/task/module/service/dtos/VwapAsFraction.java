package es.bbva.task.module.service.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class VwapAsFraction {
    private double bidVwapNumerator;
    private double bidVwapDenominator;
    private double offerVwapNumerator;
    private double offerVwapDenominator;

    public double getTotalBidAmount() {
        return this.bidVwapDenominator;
    }

    public double computeVwapForBid() {
        if (this.bidVwapDenominator == 0) return 0;
        return this.bidVwapNumerator / this.bidVwapDenominator;
    }

    public double getTotalOfferAmount() {
        return this.offerVwapDenominator;
    }

    public double computeVwapForOffer() {
        if (this.offerVwapDenominator == 0) return 0;
        return this.offerVwapNumerator / this.offerVwapDenominator;
    }

    /**
     * Carga nuevos datos de la actualización de mercado vwap
     * @param bidAmount
     * @param bidPrice
     * @param offerAmount
     * @param offerPrice
     */
    public void loadProperties(double bidAmount, double bidPrice, double offerAmount, double offerPrice) {
        this.bidVwapNumerator += bidAmount * bidPrice;
        this.bidVwapDenominator += bidAmount;
        this.offerVwapDenominator += offerAmount;
        this.offerVwapNumerator += offerAmount * offerPrice;
    }

    /**
     * Inicializa datos preexistentes
     * @param bidAmount
     * @param bidPrice
     * @param offerAmount
     * @param offerPrice
     */
    public void initProperties(double bidAmount, double bidPrice, double offerAmount, double offerPrice) {
        this.bidVwapNumerator = bidAmount * bidPrice;
        this.bidVwapDenominator = bidAmount;
        this.offerVwapDenominator = offerAmount;
        this.offerVwapNumerator = offerAmount * offerPrice;
    }

    @Override
    public String toString() {
        return "bidVwap =" + computeVwapForBid() +
                ", bidAmount=" + getTotalBidAmount() +
                ", offerVwap=" + computeVwapForOffer() +
                ", offerAmount=" + getTotalOfferAmount() +
                "}";
    }
}
