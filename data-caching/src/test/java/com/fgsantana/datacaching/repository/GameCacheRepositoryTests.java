package com.fgsantana.datacaching.repository;

import com.fgsantana.datacaching.domain.Game;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameCacheRepositoryTests {


	@Autowired
	GameCacheRepository gameCacheRepository;


	@Test
	void contextLoads() {
	}

	static Game game;

	@BeforeAll
	static void setGame() {
		game = new Game(453,"Game Test", 4.5);

	}



	@Test
	@Order(1)
	void testSaveGameCache() {

		gameCacheRepository.save(game);

	}

	@Test
	@Order(2)
	void testFindGameByIdCache() {
		Game gameFromCache = gameCacheRepository.findById(game.getId());
		assertEquals(game, gameFromCache);
	}


	@Test
	@Order(3)
	void testUpdateGameCache() {
		Game gameFromCache = gameCacheRepository.findById(game.getId());

		Game gameUpdated = new Game(gameFromCache.getId(), "Sound Mix Journey", 4.85);



		gameCacheRepository.update(gameUpdated);

		Game gameUpdatedFromCache = gameCacheRepository.findById(gameFromCache.getId());
		assertNotEquals(game, gameUpdatedFromCache);

	}

	@Test
	@Order(4)
	void testDeleteGameCache() {
		gameCacheRepository.deleteById(game.getId());
		Game gameFromCache = gameCacheRepository.findById(game.getId());
		assertNull(gameFromCache);
	}

	@Test
	void testSaveGamesCache(){
		Game game1 = new Game(123,"Game1", 3.85);
		Game game2 = new Game(124,"Game2", 2.80);
		Game game3 = new Game(125,"Game3", 3.51);
		Game game4 = new Game(126,"Game4", 4.25);

		Map<Integer, Game> map = new HashMap<>();

		map.put(game1.getId(),game1);
		map.put(game2.getId(),game2);
		map.put(game3.getId(),game3);
		map.put(game4.getId(),game4);
		gameCacheRepository.saveGames(map);
	}


}


