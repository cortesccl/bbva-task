package es.bbva.task.module;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BbvaModuleApplicationTests {

//	private Calculator calculator;

//	@MockBean
//	private MarketUpdate marketUpdate;
//
//	@MockBean
//	private TwoWayPrice twoWayPrice;

//	@BeforeTestClass
//	public void setUp() {
//		calculator = Mockito.mock(Calculator.class);
////		marketUpdate = Mockito.mock(MarketUpdate.class);
////		twoWayPrice = Mockito.mock(TwoWayPrice.class);
//		Market market = createMarket("MARKET2", 0);
//		Instrument instrument = createInstrument("INSTRUMENT1", 0);
////		marketUpdate = submitSample(market, instrument, 10.00, 100.00, 11.00d, 500.00d);
////		Mockito.when(calculator.applyMarketUpdate(marketUpdate)).thenReturn(twoWayPrice);
//
//	}

//	@Test
//	public void testOneWayPrices_bids() {
//		Calculator calculator = new CalculatorImpl();
//
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.MARKET2, Instrument.INSTRUMENT1, 10.00, 100.00, 0, 0));
//			assertEquals("[INSTRUMENT1, bid_wvap=10.00, bid_amount=100.00, offer_wvap=0.00, offer_amount=0.00]", format(result));
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.MARKET2, Instrument.INSTRUMENT1, 10.00, 100.00, 0, 0));
//			assertEquals("[INSTRUMENT1, bid_wvap=10.00, bid_amount=200.00, offer_wvap=0.00, offer_amount=0.00]", format(result));
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.MARKET2, Instrument.INSTRUMENT1, 20.00, 200.00, 0, 0)); // price hike
//			assertEquals("[INSTRUMENT1, bid_wvap=15.00, bid_amount=400.00, offer_wvap=0.00, offer_amount=0.00]", format(result));
//		}
//
//		{  // different market / instrument combination
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.MARKET1, Instrument.INSTRUMENT18, 27.33, 10000.00, 0, 0));
//			AssertionErrors.assertEquals("[INSTRUMENT18, bid_wvap=27.33, bid_amount=10000.00, offer_wvap=0.00, offer_amount=0.00]", format(result));
//		}
//
//	}
//
//	@Test
//	public void testOneWayPrices_offers() {
//		Calculator calculator = new CalculatorImpl();
//
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.builder().name("MARKET1").index(0).build(), Instrument.builder().name("INSTRUMENT1").index(0).build(), 0, 0, 11.00d, 500.00d));
//			assertEquals("[INSTRUMENT1, bid_wvap=0.00, bid_amount=0.00, offer_wvap=11.00, offer_amount=500.00]", format(result));
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.builder().name("MARKET2").index(1).build(), Instrument.builder().name("INSTRUMENT2").index(1).build(), 0, 0, 11.00d, 500.00d));
//			assertEquals("[INSTRUMENT2, bid_wvap=0.00, bid_amount=0.00, offer_wvap=11.00, offer_amount=1000.00]", format(result));
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(Market.MARKET2, Instrument.INSTRUMENT1, 0, 0, 21.00d, 2000.00d)); // price hike
//			assertEquals("[INSTRUMENT1, bid_wvap=0.00, bid_amount=0.00, offer_wvap=17.67, offer_amount=3000.00]", format(result));
//		}
//	}

	@Test
	public void testTwoWayPrices() {
//		Calculator calculator = new CalculatorImpl();
//		Market market = createMarket("MARKET2", 0);
//		Instrument instrument = createInstrument("INSTRUMENT1", 0);

//		Mockito.when(twoWayPrice.getInstrument()).thenReturn(instrument);
//		Mockito.when(marketUpdate.getMarket()).thenReturn(market);
//		Mockito.when(marketUpdate.getTwoWayPrice()).thenReturn((TwoWayPrice) submitSample(market, instrument, 10.00, 100.00, 11.00d, 500.00d));

//		{
//			TwoWayPrice twoWayPrice = createTwoWayPrice(instrument, 10.00, 100.00, 11.00d, 500.00d);
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(market, twoWayPrice));
//			AssertionErrors.assertEquals("Prueba1", "[INSTRUMENT1, bid_wvap=10.00, bid_amount=100.00, offer_wvap=11.00, offer_amount=500.00]", "[INSTRUMENT1, bid_wvap=10.00, bid_amount=100.00, offer_wvap=11.00, offer_amount=500.00]");
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(market, instrument, 10.00, 100.00, 11.00d, 500.00d));
//			AssertionErrors.assertEquals("Prueba2", "[INSTRUMENT1, bid_wvap=10.00, bid_amount=200.00, offer_wvap=11.00, offer_amount=1000.00]", format(result));
//		}
//		{
//			TwoWayPrice result = calculator.applyMarketUpdate(
//					submitSample(market, instrument, 20.00, 200.00, 21.00d, 2000.00d)); // price hike
//			AssertionErrors.assertEquals("Prueba3", "[INSTRUMENT1, bid_wvap=15.00, bid_amount=400.00, offer_wvap=17.67, offer_amount=3000.00]", format(result));
//		}
	}


//	private static Market createMarket (String name, int index) {
//		return Market.builder()
//				.name(name)
//				.index(index)
//				.build();
//	}
//
//	private static Instrument createInstrument (String name, int index) {
//		return Instrument.builder()
//				.name(name)
//				.index(index)
//				.build();
//	}
//
//	private TwoWayPrice createTwoWayPrice (Instrument instrument,
//										   double bidPrice,
//										   double bidAmount,
//										   double offerPrice,
//										   double offerAmount)  {
//		return TwoWayPriceImpl.builder()
//				.instrument(instrument)
//				.bidAmount(bidAmount)
//				.bidPrice(bidPrice)
//				.offerPrice(offerPrice)
//				.offerAmount(offerAmount)
//				.build();
//
//	}
//
//	private MarketUpdate submitSample(
//			Market market,
//			TwoWayPrice twoWayPrice) {
//
//		MarketUpdate marketUpdate = MarketUpdateImpl.builder()
//				.market(market)
//				.twoWayPrice(twoWayPrice)
//				.build();
//
//		return marketUpdate;
//	}
//
//	private static String format(TwoWayPrice twp) {
//		return String.format("[%s, bid_wvap=%.2f, bid_amount=%.2f, offer_wvap=%.2f, offer_amount=%.2f]",
//				twp.getInstrument(), twp.getBidPrice(), twp.getBidAmount(), twp.getOfferPrice(), twp.getOfferAmount());
//	}

}
