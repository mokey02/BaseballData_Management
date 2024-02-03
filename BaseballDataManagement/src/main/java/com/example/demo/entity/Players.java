package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {

	/** player_id */
	@Id
	private Integer player_id;
	
	/** sei */
	private String sei;
	
	/** mei */
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
}
