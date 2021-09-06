package br.com.projetosdio.controledeusuario.controledeusuario.service;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.request.PersonDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.dto.response.MessageResponseDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import br.com.projetosdio.controledeusuario.controledeusuario.exception.PersonNotFoundException;
import br.com.projetosdio.controledeusuario.controledeusuario.mapper.PersonMapper;
import br.com.projetosdio.controledeusuario.controledeusuario.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created personDTO with ID: " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAllPeople() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO getPerson(Long id) throws PersonNotFoundException {
        Person gotPerson = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(gotPerson);
    }
}
