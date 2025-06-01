package io.github.saulmmbp.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.saulmmbp.dtos.ContactRequestDto;
import io.github.saulmmbp.dtos.ContactResponseDto;
import io.github.saulmmbp.entities.Contact;
import io.github.saulmmbp.entities.Phone;
import io.github.saulmmbp.representationmodels.ContactModel;

@Mapper(componentModel = "spring")
public interface IContactMapper {

	ContactResponseDto toResponseDto(Contact entity);
	
	List<String> toPhoneNumberList(List<Phone> phones);
	
	default String toPhoneNumber(Phone phone) {
		return phone.getPhoneNumber();
	}
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "auditMetadata", ignore = true)
	Contact toEntity(ContactRequestDto request);
	
	List<Phone> toPhoneList(List<String> phones);
	
	default Phone toPhone(String phoneNumber) {
		return new Phone(phoneNumber);
	}
	
//	@Mapping(target = "add", ignore = true)
	ContactModel toModel(ContactResponseDto dto);
	
}
