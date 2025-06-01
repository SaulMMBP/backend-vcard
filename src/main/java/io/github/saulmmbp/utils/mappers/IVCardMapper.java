package io.github.saulmmbp.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.saulmmbp.dtos.VCardRequestDto;
import io.github.saulmmbp.dtos.VCardResponseDto;
import io.github.saulmmbp.entities.Contact;
import io.github.saulmmbp.entities.VCard;
import io.github.saulmmbp.representationmodels.VCardModel;

@Mapper(componentModel = "spring", uses = IContactMapper.class)
public interface IVCardMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "auditMetadata", ignore = true)
	@Mapping(target = "qr", ignore = true)
	@Mapping(source = "contactId", target = "contact")
	VCard toEntity(VCardRequestDto request);
	
	default Contact toContact(Long contactId) {
		Contact contact = new Contact();
		contact.setId(contactId);
		return contact;
	}
	
	VCardResponseDto toResponseDto(VCard vcard);
	
//	@Mapping(target = "add", ignore = true)
	VCardModel toModel(VCardResponseDto dto);
	
}
