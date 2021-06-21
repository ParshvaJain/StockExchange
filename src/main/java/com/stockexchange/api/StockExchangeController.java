package com.stockexchange.api;

import com.stockexchange.message.ResponseMessage;
import com.stockexchange.model.StockExchange;
import com.stockexchange.repository.StockExchangeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins ="https://reactapp--service.herokuapp.com")
@RestController
@RequestMapping(value = "/stockexchange")
public class StockExchangeController {
	
	@Autowired
	private StockExchangeRepository repository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<StockExchange>> getAllExchanges() {
		List<StockExchange> tempList = repository.findAll();
		return new ResponseEntity<>(tempList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNames", method = RequestMethod.GET)
	public List<String> getAllExchangesNames() {
		List<StockExchange> tempList = repository.findAll();
		List<String> exchangeNames = new ArrayList<>();
		int length = tempList.size();
		
		for(int i = 0; i < length; i++) {
			exchangeNames.add(tempList.get(i).getName());
		}
		
		return exchangeNames;
	}
	
	@RequestMapping(value ="/addCompanyToExchange/{exchange}/{company}", method = RequestMethod.GET)
	public ResponseEntity<?> addCompanyToExchange(@PathVariable("exchange") String exchange,@PathVariable("company") String company) {
		
			if(repository.findByname(exchange) == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} 
			
			System.out.println("inside api ");
			
			StockExchange tempExchange = repository.findByname(exchange);
			ArrayList<String> companiesList = tempExchange.getCompanies();
			companiesList.add(company);
			tempExchange.setCompanies(companiesList);
			repository.save(tempExchange);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Updated"));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<StockExchange> getExchangeById(@PathVariable("id") String id) {
		return repository.findById(id);
	}
	
	@RequestMapping(value = "/createExchange", method = RequestMethod.POST)
	public ResponseEntity<StockExchange> addNewExchange(@RequestBody StockExchange exchange) {
		exchange.setId(ObjectId.get().toString());
		repository.save(exchange);
		return new ResponseEntity<>(exchange,HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/deleteCompany/{company}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompanyFromExchanges(@PathVariable("company") String company) {
		List<StockExchange> exchangesList = repository.findAll();
		int length = exchangesList.size();
		StockExchange temp;
		ArrayList<String> companies;
		
		for(int i = 0; i < length; i++) {
			temp = exchangesList.get(i);
			companies = temp.getCompanies();
			companies.removeIf( (String x) -> (x.toLowerCase()).equals(company.toLowerCase()));
			temp.setCompanies(companies);
			repository.save(temp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Deleted Messages"));	
	}
	
}