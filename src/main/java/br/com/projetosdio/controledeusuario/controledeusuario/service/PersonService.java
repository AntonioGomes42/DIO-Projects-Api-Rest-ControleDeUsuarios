package br.com.projetosdio.controledeusuario.controledeusuario.service;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.MessageResponseDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import br.com.projetosdio.controledeusuario.controledeusuario.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID: " +savedPerson.getId())
                .build();
    }

}
