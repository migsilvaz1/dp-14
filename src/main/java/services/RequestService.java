package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Request;

import repositories.RequestRepository;

@Service
@Transactional
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	public RequestService(){
		super();
	}
	
	public Request create(){
		Request request= new Request();
		return request;
	}
	
	public Request save(Request r){
		Assert.isTrue(r.getRequestedStart().before(r.getRequestedEnd()));
		Assert.isTrue(r.getItem()!=null);
		r.setConsumer(consumerService.findByPrincipal());
		return requestRepository.save(r);
	}
	
	public void delete(Request r){
		Assert.isTrue(r.getContracts().isEmpty());
		Assert.isTrue(r.getConsumer().equals(consumerService.findByPrincipal()));
		requestRepository.delete(r);
	}
	
	public Collection<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public Request findOne(int id){
		return requestRepository.findOne(id);
	}
	
	public Collection<Request> requestByConsumerUAId(int id){
		return requestRepository.requestByConsumerUAId(id);
	}

}
