package br.com.projetosdio.controledeusuario.controledeusuario.controller;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.request.PersonDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.dto.response.MessageResponseDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.exception.PersonNotFoundException;
import br.com.projetosdio.controledeusuario.controledeusuario.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAllPeople() {
        return personService.listAllPeople();
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.getPerson(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updatePersonById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updatePersonById(id,personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deletePersonById(id);
    }

}
