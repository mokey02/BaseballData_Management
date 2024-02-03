package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * form
 * @author keimo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersForm {

	/** player_id */
	private Integer player_id;
	
	/** sei */
	@NotBlank
	private String sei;
	
	/** mei */
	@NotBlank
	private String mei;
	
	/** nickname_flg */
	private Integer nickname_flg;
	
	/** nickname */
	private String nickname;
	
	/** sei_sename */
	private String sei_sename;
	
	/** mei_sename */
	private String mei_sename;
	
	/** nickname_sename */
	private String nickname_sename;
	
	/** position */
	private Integer position;
	
	/** sebango */
	private String sebango;
	
	/** 「登録」or「変更」判定用 */
	private Boolean newPlayer;
}
