package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Players;

public interface PlayersCrudRepository extends CrudRepository<Players, Integer> {

}
