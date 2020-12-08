package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public PersonVO create(PersonVO person) {
		
		//converte o PersonVO para Person para enviar para o banco de dados
		var entity = DozerConverter.parseObject(person, Person.class);
		// salva registro na base de dados e converte o Person para PersonVo
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);

		return vo;
	}

	
	public List<PersonVO> findAll() {
		// busca por todos
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class) ;
	}
	
	public PersonVO findById(Long id) {

		//busca o banco pelo id, receberá Person e entao converte para PersonVO no return
		var entity= repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	
	}
	
	
	
	
	public PersonVO update(PersonVO person) {

		// busca pelo ID, se encontrar continua, se nao estoura a exception
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		// seta os valores novos, o ID nao pode mudar
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		// salva com os novos valores
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
		
		
	}

	public void delete(Long id) {
		// busca pelo ID, se encontrar continua, se nao estoura a exception
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);

	}


}