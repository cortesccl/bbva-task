package es.bbva.task.module.controllers;

import es.bbva.task.module.entities.Market;
import es.bbva.task.module.service.MarketService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/markets")
public class MarketController {

    @Autowired
    private MarketService service;

    @GetMapping
    public ResponseEntity<?> listMarkets (@RequestParam Optional<Long> instrumentId) {
        List markets = service.findAllMarkets();
        if (markets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(markets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMarket(@PathVariable("id") Long id) {
        Market market = service.findById(id);
        if (null == market) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(market);
    }

    @PostMapping
    public ResponseEntity<?> createMarket(@Valid @RequestBody Market market, BindingResult result) {

            if (result.hasErrors()) {
                return validate(result);
            }
            Market marketCreate = service.saveMarket(market);
            if (marketCreate != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(marketCreate);
            }

            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  crear el market"));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody Market market, BindingResult result) {
//        if (userService.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Ya  existe un usuario con ese correo o username"));
//        }
        if (result.hasErrors()) {
            return validate(result);
        }
        market.setId(id);
        Market marketCreate = service.updateMarket(id, market);
        if (marketCreate != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(marketCreate);
        }

        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Imposible  actualizar el market"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long  id) {
        service.deleteMarket(id);
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

