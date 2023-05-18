package es.bbva.task.module.utils;

import es.bbva.task.module.entities.Vwap;

import java.util.Random;

/**
 * Clase pde utilidades para crear objetos de prueba
 */
public class RandomUtilities {

    public static Vwap createRandomVwap () {
        double bidAmount = createRandomDouble(Constants.MIN_RANGE_BID, Constants.MAX_RANGE_BID);
        double bidPrice = createRandomDouble(Constants.MIN_RANGE_BID, Constants.MAX_RANGE_BID);
        double offerAmount = createRandomDouble(Constants.MIN_RANGE_DEFAULT, Constants.MAX_RANGE_DEFAULT);
        double offerPrice = createRandomDouble(Constants.MIN_RANGE_OFFER, Constants.MAX_RANGE_OFFER);

        return Vwap.builder()
                .bidAmount(bidAmount)
                .bidPrice(bidPrice)
                .offerAmount(offerAmount)
                .offerPrice(offerPrice)
                .build();
    }

    /**
     * Crea un número tipo Long de forma aleatoria
     * @param rangeMin, rango mínimo
     * @param rangeMax, rango máximo
     * @return
     */
    public static Long createRandomLong(int rangeMin, int rangeMax) {
        return Integer.valueOf(new Random().nextInt(rangeMax-rangeMin) + 1).longValue();
    }

    /**
     * Crea un número tipo double de forma aleatoria
     * @param rangeMin, rango mínimo
     * @param rangeMax, rango máximo
     * @return
     */
    public static double createRandomDouble(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * new Random().nextDouble();
    }
}
