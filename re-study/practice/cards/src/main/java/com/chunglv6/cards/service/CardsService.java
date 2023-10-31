package com.chunglv6.cards.service;

import com.chunglv6.cards.model.Cards;

import java.util.List;

public interface CardsService {

    List<Cards> findByCustomerId(Long customerId);
}
