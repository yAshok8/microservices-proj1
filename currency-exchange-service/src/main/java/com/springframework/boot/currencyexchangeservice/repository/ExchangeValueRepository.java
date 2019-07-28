package com.springframework.boot.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springframework.boot.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
