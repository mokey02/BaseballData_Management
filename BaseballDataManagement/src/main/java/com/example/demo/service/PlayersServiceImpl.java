package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Players;
import com.example.demo.repository.PlayersCrudRepository;

@Service
@Transactional
public class PlayersServiceImpl implements PlayersService {

	/** Repository：注入 */
	@Autowired
	PlayersCrudRepository playersRepository;
	
	@Override
	public Iterable<Players> selectAll() {

		return playersRepository.findAll();
	}

	@Override
	public Optional<Players> selectOneById(Integer id) {

		return playersRepository.findById(id);
	}

	@Override
	public void insertPlayer(Players player) {

		playersRepository.save(player);
	}

	@Override
	public void updatePlayer(Players player) {

		playersRepository.save(player);
	}

	@Override
	public void deletePlayerById(Integer id) {

		playersRepository.deleteById(id);
	}

}
