package com.fgsantana.datacaching.repository;

import com.fgsantana.datacaching.domain.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDBRepository extends CrudRepository<Game, Integer> {
}
