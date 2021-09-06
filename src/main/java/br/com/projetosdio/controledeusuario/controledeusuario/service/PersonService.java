package br.com.projetosdio.controledeusuario.controledeusuario.service;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.MessageResponseDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.dto.request.PersonDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import br.com.projetosdio.controledeusuario.controledeusuario.mapper.PersonMapper;
import br.com.projetosdio.controledeusuario.controledeusuario.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Person savedPerson= personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created personDTO with ID: " + savedPerson.getId())
                .build();
    }

}
