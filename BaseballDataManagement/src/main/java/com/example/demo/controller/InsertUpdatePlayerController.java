package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Players;
import com.example.demo.form.PlayersForm;
import com.example.demo.service.PlayersService;

/**
 * playersコントローラ
 * @author keimo
 *
 */
@Controller
@RequestMapping("/insertUpdatePlayer")
public class InsertUpdatePlayerController {

	/** DI対象 */
	@Autowired
	PlayersService playersService;

	/** 「form-backing bean」の初期化 */
	@ModelAttribute
	public PlayersForm setUpForm() {

		PlayersForm playerForm = new PlayersForm();
		return playerForm;
	}

	/** playerの一覧を表示 */
	@GetMapping
	public String showPlayerList(PlayersForm playerForm, Model model) {

		// 新規登録設定
		playerForm.setNewPlayer(true);

		// 掲示板の一覧を取得する
		Iterable<Players> playersList = playersService.selectAll();

		// 表示用「Model」への格納
		model.addAttribute("playersList", playersList);
		model.addAttribute("title", "登録用フォーム");
		return "insertUpdatePlayer";
	}

	@PostMapping("/insert")
	public String insert(@Validated PlayersForm playersForm, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		//playersForm.setNewPlayer(true);
		// formからEntityへの詰め替え
		Players player = new Players();
		player.setSei(playersForm.getSei());
		player.setMei(playersForm.getMei());
		player.setNickname_flg(playersForm.getNickname_flg());
		player.setNickname(playersForm.getNickname());
		player.setSei_sename(playersForm.getSei_sename());
		player.setMei_sename(playersForm.getMei_sename());
		player.setNickname_sename(playersForm.getNickname_sename());
		player.setPosition(playersForm.getPosition());
		player.setSebango(playersForm.getSebango());

		// 入力チェック
		if (!bindingResult.hasErrors()) {
			playersService.insertPlayer(player);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/insertUpdatePlayer";
		} else {

			// エラーがある場合は、一覧表示処理を呼びます
			return "insertUpdatePlayer";
		}
	}

	@GetMapping("/{player_id}")
	public String showUpdate(PlayersForm playersForm, @PathVariable Integer player_id, Model model) {

		Optional<Players> playersOpt = playersService.selectOneById(player_id);
		Optional<PlayersForm> playersFormOpt = playersOpt.map(t -> makePlayersForm(t));

		if (playersFormOpt.isPresent()) {
			playersForm = playersFormOpt.get();
		}

		makeUpdateModel(playersForm, model);
		return "insertUpdatePlayer";
	}

	private void makeUpdateModel(PlayersForm playersForm, Model model) {
		model.addAttribute("player_id", playersForm.getPlayer_id());
		playersForm.setNewPlayer(false);
		model.addAttribute("playersForm", playersForm);
		model.addAttribute("title", "更新用フォーム");
	}

	@PostMapping("/update")
	public String update(@Validated PlayersForm playersForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		Players players = makePlayers(playersForm);

		// 入力チェック
		if (!result.hasErrors()) {

			playersService.updatePlayer(players);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました");

			return "redirect:/insertUpdatePlayer/" + players.getPlayer_id();
		} else {

			makeUpdateModel(playersForm, model);
			return "insertUpdatePlayer";
		}
	}

	private Players makePlayers(PlayersForm playersForm) {
		Players players = new Players();
		players.setPlayer_id(playersForm.getPlayer_id());
		players.setSei(playersForm.getSei());
		players.setMei(playersForm.getMei());
		players.setNickname_flg(playersForm.getNickname_flg());
		players.setNickname(playersForm.getNickname());
		players.setSei_sename(playersForm.getSei_sename());
		players.setMei_sename(playersForm.getMei_sename());
		players.setNickname_sename(playersForm.getNickname_sename());
		players.setPosition(playersForm.getPosition());
		players.setSebango(playersForm.getSebango());
		return players;
	}

	private PlayersForm makePlayersForm(Players players) {

		PlayersForm playersForm = new PlayersForm();
		playersForm.setPlayer_id(players.getPlayer_id());
		playersForm.setSei(players.getSei());
		playersForm.setMei(players.getMei());
		playersForm.setNickname_flg(players.getNickname_flg());
		playersForm.setNickname(players.getNickname());
		playersForm.setSei_sename(players.getSei_sename());
		playersForm.setMei_sename(players.getMei_sename());
		playersForm.setNickname_sename(players.getNickname_sename());
		playersForm.setPosition(players.getPosition());
		playersForm.setSebango(players.getSebango());
		return playersForm;
	}

}
