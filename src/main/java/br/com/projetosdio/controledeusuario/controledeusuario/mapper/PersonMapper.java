package br.com.projetosdio.controledeusuario.controledeusuario.mapper;

import br.com.projetosdio.controledeusuario.controledeusuario.dto.request.PersonDTO;
import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source ="birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
