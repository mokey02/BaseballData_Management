package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Players;

/**
 * Playersサービス処理
 * @author keimo
 *
 */
public interface PlayersService {

	/** 選手情報を全件取得します */
	Iterable<Players> selectAll();
	
	/** 選手情報を、idをキーに１件取得します */
	Optional<Players> selectOneById(Integer id);
	
	/** 選手情報を登録します */
	void insertPlayer(Players player);
	
	/** 選手情報を更新します */
	void updatePlayer(Players player);
	
	/** 選手情報を削除します */
	void deletePlayerById(Integer id);
}
