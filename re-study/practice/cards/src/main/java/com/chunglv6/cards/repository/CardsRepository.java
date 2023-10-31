package com.chunglv6.cards.repository;

import com.chunglv6.cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {

    List<Cards> findByCustomerId(Long customerId);
}
