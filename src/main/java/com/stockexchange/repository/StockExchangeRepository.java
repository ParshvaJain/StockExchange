package com.stockexchange.repository;

import com.stockexchange.model.StockExchange;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends MongoRepository<StockExchange,String>{

	List<StockExchange> findAll();

	StockExchange findByname(String exchange);
	
}
