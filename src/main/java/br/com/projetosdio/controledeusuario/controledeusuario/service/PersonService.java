package br.com.projetosdio.controledeusuario.controledeusuario.service;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.request.PersonDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.dto.response.MessageResponseDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import br.com.projetosdio.controledeusuario.controledeusuario.exception.PersonNotFoundException;
import br.com.projetosdio.controledeusuario.controledeusuario.mapper.PersonMapper;
import br.com.projetosdio.controledeusuario.controledeusuario.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

        return createMessageResponse(savedPerson.getId(), "Created person with ID: ");
    }


    public List<PersonDTO> listAllPeople() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }


    public PersonDTO getPerson(Long id) throws PersonNotFoundException {
        Person gotPerson = verifyIfExistsPerson(id);

        return personMapper.toDTO(gotPerson);
    }

    public void deletePersonById(Long id) throws PersonNotFoundException {
        verifyIfExistsPerson(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updatePersonById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExistsPerson(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatedPerson.getId(), "Updated person with ID: ");
    }

    private Person verifyIfExistsPerson(Long id) throws PersonNotFoundException {

        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
