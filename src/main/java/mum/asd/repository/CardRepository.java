package mum.asd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.asd.domain.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
