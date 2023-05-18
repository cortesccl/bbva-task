package es.bbva.task.module.controllers;

import es.bbva.task.module.entities.Instrument;
import es.bbva.task.module.entities.Vwap;
import es.bbva.task.module.service.InstrumentService;
import es.bbva.task.module.service.MarketService;
import es.bbva.task.module.service.VwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private VwapService vwapService;

    @GetMapping
    public ResponseEntity<?> listInstruments () {
        List instruments = instrumentService.findAllInstruments();
        if (instruments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(instruments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstrument(@PathVariable("id") Long id) {
        Instrument instrument = instrumentService.findById(id);
        if (null == instrument) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(instrument);
    }

    @PostMapping
    public ResponseEntity<?> createInstrument(@Valid @RequestBody Instrument instrument, BindingResult result) {

            if (result.hasErrors()) {
                return validate(result);
            }
            Optional.ofNullable(marketService.findAllMarkets()).ifPresent(markets ->
                markets.forEach(market -> {
                    Vwap vwap = Vwap.builder()
                            .offerPrice(0d)
                            .offerAmount(0d)
                            .bidPrice(0d)
                            .bidAmount(0d)
                            .market(market)
                            .build();
                    instrument.addVwap(vwap);
                })
            );
        Instrument instrumentCreate = instrumentService.saveInstrument(instrument);
        if (instrumentCreate != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(instrumentCreate);
            }

            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  crear el instrument"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody Instrument instrument, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }
        instrument.setId(id);
        Instrument instrumentCreate = instrumentService.updateInstrument(id, instrument);
        if (instrumentCreate != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(instrumentCreate);
        }

        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  actualizar el instrument"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long  id) {
        instrumentService.deleteInstrument(id);
        return ResponseEntity.noContent().build();
    }
    
    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}

