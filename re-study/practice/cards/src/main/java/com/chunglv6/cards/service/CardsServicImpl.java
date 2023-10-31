package com.chunglv6.cards.service;

import com.chunglv6.cards.model.Cards;
import com.chunglv6.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsServicImpl implements CardsService{

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public List<Cards> findByCustomerId(Long customerId) {
        return cardsRepository.findByCustomerId(customerId);
    }
}
