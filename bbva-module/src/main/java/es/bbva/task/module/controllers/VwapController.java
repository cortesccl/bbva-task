package es.bbva.task.module.controllers;

import es.bbva.task.module.entities.Vwap;
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

@RestController
@RequestMapping("/api")
public class VwapController {

    @Autowired//(required=true)
    private VwapService service;

    @GetMapping("/instruments/{instrumentId}/vwaps")
    public ResponseEntity<?> listAllVwapsByInstrument (@PathVariable Long instrumentId) {
        List<Vwap> vwaps = service.findAllByInstrumentId(instrumentId);
        if (vwaps.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vwaps);
    }

    @GetMapping("/markets/{marketId}/vwaps")
    public ResponseEntity<?> listAllVwapsByMarket (@PathVariable Long marketId) {
        List<Vwap> vwaps = service.findAllByMarketId(marketId);
        if (vwaps.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vwaps);
    }

    @GetMapping("/instruments/{instrumentId}/markets/{marketId}/vwaps")
    public ResponseEntity<?> listAllVwapsByInstrumentAndMarket (@PathVariable Long instrumentId, @PathVariable Long marketId) {
        Vwap vwap = service.findAllByInstrumentIdAndMarketId(instrumentId, marketId);
        if (vwap == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vwap);
    }

    @GetMapping("/vwaps/{id}")
    public ResponseEntity<?> getVwap(@PathVariable("id") Long id) {
        Vwap vwap = service.findById(id);
        if (null == vwap) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vwap);
    }

    @PostMapping("/vwaps")
    public ResponseEntity<?> createVwap(@Valid @RequestBody Vwap vwap, BindingResult result) {

            if (result.hasErrors()) {
                return validate(result);
            }
            Vwap vwapBBdd = service.save(vwap);
            if (vwapBBdd != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(vwapBBdd);
            }

            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  crear el market"));

    }

    @PutMapping("/vwaps/{id}")
    public ResponseEntity<?> updateVwap(@PathVariable("id") Long id, @Valid @RequestBody Vwap vwap, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }
        vwap.setId(id);
        Vwap vwapResult = service.update(vwap);
        if (vwapResult != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(vwapResult);
        }
//
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  actualizar el vwap"));
    }

    @DeleteMapping("/vwaps/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long  id) {
        service.delete(id);
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

