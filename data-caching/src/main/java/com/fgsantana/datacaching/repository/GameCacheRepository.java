package com.fgsantana.datacaching.repository;

import com.fgsantana.datacaching.domain.Game;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@AllArgsConstructor
public class GameCacheRepository {

    final RedisTemplate<String, Game> redisTemplate;

    final String hashKey = "games";


    public boolean save(final Game game){
       return redisTemplate.opsForHash().putIfAbsent(hashKey, game.getId(), game);
    }

    public Game findGameById(final Integer id){
        return (Game) redisTemplate.opsForHash().get(hashKey, id);
    }

    public void updateGame(final Game game){
        redisTemplate.opsForHash().put(hashKey, game.getId(), game);
    }

    public void deleteGameById(final Integer id){
        redisTemplate.opsForHash().delete(hashKey,id);
    }

    public void saveGames(final Map<Integer, Game> games){
        redisTemplate.opsForHash().putAll(hashKey,games);
    }




}
