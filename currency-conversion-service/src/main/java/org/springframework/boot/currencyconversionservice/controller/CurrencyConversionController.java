package org.springframework.boot.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.currencyconversionservice.beans.CurrencyConversionBean;
import org.springframework.boot.currencyconversionservice.proxies.CurrencyExchangeServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import feign.FeignException;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("to") String to, @PathVariable("from") String from,
			@PathVariable("quantity") BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("to", to);
		uriVariables.put("from", from);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
		CurrencyConversionBean ccb = responseEntity.getBody();
		return new CurrencyConversionBean(ccb.getId(), from, to, quantity, ccb.getConversionMultiple(),
				quantity.multiply(ccb.getConversionMultiple()), ccb.getPort());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyWithFeignProxy(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
		CurrencyConversionBean bean = null;
		try {
			// call the proxy to get CurrencyConversionBean
			bean = currencyExchangeServiceProxy.retrieveExchangeValue(to, from);
			bean.setQuantity(quantity);
			// we will get conversion multiple from proxy returned object.
			bean.setTotalCalculatedAmount(bean.getConversionMultiple().multiply(bean.getQuantity()));
		}catch (FeignException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bean;
	}

}
