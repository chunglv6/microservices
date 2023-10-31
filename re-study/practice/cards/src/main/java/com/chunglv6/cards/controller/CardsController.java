package com.chunglv6.cards.controller;

import com.chunglv6.cards.config.ApplicationProperties;
import com.chunglv6.cards.model.Cards;
import com.chunglv6.cards.service.CardsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CardsController {
    public static final Logger log = LoggerFactory.getLogger(CardsController.class);
    @Autowired
    private CardsService cardsService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @GetMapping("/{customerId}/cards")
    public List<Cards> getCardsByCustomerId(@PathVariable Long customerId,@RequestHeader(value = "chunglv6-correlation-id",required = false) String correlationid)  {
        log.info("findByCustomerId() started");
        List<Cards> byCustomerId = cardsService.findByCustomerId(customerId);
        log.info("findByCustomerId() end");
        return byCustomerId;
    }

    @GetMapping("/cards/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ApplicationProperties properties = new ApplicationProperties(applicationProperties.getMsg(),applicationProperties.getBuildVersion(),applicationProperties.getMailDetails(),applicationProperties.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
