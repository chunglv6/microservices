package com.chunglv6.accounts.service.client;

import com.chunglv6.accounts.model.Cards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cards")
public interface CardsClient {
    @RequestMapping(method = RequestMethod.GET,value = "/api/v1/{customerId}/cards",consumes = "application/json")
    List<Cards> getCardsBycustomerId(@PathVariable Long customerId,@RequestHeader("chunglv6-correlation-id") String correlationid);
}
