package com.fgsantana.datacaching.repository;

import com.fgsantana.datacaching.domain.Game;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class GameCacheRepository {

    final RedisTemplate<String, Game> redisTemplate;

    public GameCacheRepository(RedisTemplate<String, Game> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    final String hashKey = "games";


    public boolean save(final Game game){
       return redisTemplate.opsForHash().putIfAbsent(hashKey, game.getId(), game);
    }

    public Game findById(final Integer id){
        return (Game) redisTemplate.opsForHash().get(hashKey, id);
    }

    public void update(final Game game){
        redisTemplate.opsForHash().put(hashKey, game.getId(), game);
    }

    public Long deleteById(final Integer id){
       return redisTemplate.opsForHash().delete(hashKey,id);
    }

    public void saveGames(final Map<Integer, Game> games){
        redisTemplate.opsForHash().putAll(hashKey,games);
    }




}
