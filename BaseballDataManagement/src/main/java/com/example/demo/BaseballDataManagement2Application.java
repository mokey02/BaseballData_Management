package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Players;
import com.example.demo.service.PlayersService;

@SpringBootApplication
public class BaseballDataManagement2Application {

	public static void main(String[] args) {
		SpringApplication.run(BaseballDataManagement2Application.class, args)
				.getBean(BaseballDataManagement2Application.class).execute();
	}

	@Autowired
	PlayersService playersService;

	/**
	 * 「登録」と「全件取得」を実行
	 */
	private void execute() {

		// 登録
		//setUp();

		// 全件取得
		showList();

		// １件取得
		showOne();

		// 更新処理
		//updatePlayer();

		// 削除処理
		//deletePlayer();

	}

	/**
	 * 登録
	 */
	private void setUp() {
		System.out.println("---登録処理開始---");

		// エンティティの作成（idは自動連番で設定するためnullを設定）
		Players player1 = new Players(null, "菅野", "智之", 0, null, "Sugano", "Tomoyuki", null, 1, "18");

		Players player2 = new Players(null, "大城", "卓三", 0, null, "Ohshiro", "Takumi", null, 2, "24");

		Players player3 = new Players(null, "坂本", "勇人", 0, null, "Sakamoto", "Hayato", null, 3, "6");

		Players player4 = new Players(null, "岡本", "和真", 0, null, "Okamoto", "Kazuma", null, 3, "25");

		Players player5 = new Players(null, "丸", "佳浩", 0, null, "Maru", "Yoshihiro", null, 4, "8");

		Players player6 = new Players(null, "翁田", "大勢", 1, "大勢", "Ota", "Taisei", "Taisei", 1, "15");

		// リストにエンティティを格納
		List<Players> playersList = new ArrayList<>();

		// 第１引数に格納先、第２引数は可変長引数なのでエンティティを記述
		Collections.addAll(playersList, player1, player2, player3, player4, player5, player6);

		// 登録実行
		for (Players player : playersList) {
			playersService.insertPlayer(player);
		}

		System.out.println("---登録処理完了---");
	}

	/**
	 * 全件取得
	 */
	private void showList() {

		System.out.println("---全件取得開始---");

		// リポジトリを使用して全件取得を実施、結果を取得
		Iterable<Players> players = playersService.selectAll();
		for (Players player : players) {
			System.out.println(player);
		}
		System.out.println("---全件取得完了");
	}

	/**
	 * 1件取得
	 */
	private void showOne() {
		System.out.println("---１件取得開始---");

		// リポジトリを使用して１件取得を実施、結果を取得（戻り値はOptional）
		Optional<Players> playerOpt = playersService.selectOneById(1);

		// 値存在チェック
		if (playerOpt.isPresent()) {
			System.out.println(playerOpt.get());
		} else {
			System.out.println("該当する選手が存在しません");
		}

		System.out.println("---１件取得完了---");
	}

	/**
	 * 更新処理
	 */
	private void updatePlayer() {
		System.out.println("---更新処理開始---");

		// 変更したいエンティティを生成する
		Players player1 = new Players(6, "翁田", "大勢", 1, "大勢", "Ota", "Taisei", "Taisei", 1, "15");

		// 更新実行
		playersService.updatePlayer(player1);
		System.out.println("---更新処理完了---");
	}

	/**
	 * 削除処理
	 */
	private void deletePlayer() {
		System.out.println("---削除処理開始---");

		// 削除実行
		playersService.deletePlayerById(8);
		System.out.println("---削除処理完了---");
	}
}
