package com.fgsantana.datacaching.repository;

import com.fgsantana.datacaching.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public class GameRepository {

    final GameDBRepository dbRepository;
    final GameCacheRepository cacheRepository;

    public GameRepository(GameDBRepository dbRepository, GameCacheRepository cacheRepository) {
        this.dbRepository = dbRepository;
        this.cacheRepository = cacheRepository;
    }

    public Game save(final Game game){
        
        final Game savedGame = dbRepository.save(game);

        cacheRepository.save(savedGame);

        return savedGame;

    }

    public Optional<Game> findById(final Integer id){
        final Optional<Game> gameFromCache = Optional.ofNullable(cacheRepository.findById(id));


        return Optional.ofNullable(gameFromCache.orElseGet(() -> {

            Game game = dbRepository.findById(id).orElseGet(() -> null);
            cacheRepository.save(game);
            return game;

        }));

        }

    public Game update(Game game){
       Game updatedGame =  dbRepository.save(game);
        cacheRepository.update(updatedGame);
        return updatedGame;
    }

    public Long deleteById(Integer id){
        dbRepository.deleteById(id);
        return cacheRepository.deleteById(id);
    }



}
