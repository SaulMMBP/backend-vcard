package io.github.saulmmbp.utils.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import io.github.saulmmbp.controllers.ContactController;
import io.github.saulmmbp.dtos.ContactResponseDto;
import io.github.saulmmbp.representationmodels.ContactModel;
import io.github.saulmmbp.utils.mappers.IContactMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ContactModelAssembler implements RepresentationModelAssembler<ContactResponseDto, ContactModel> {

	private IContactMapper contactMapper;

	@Override
	public ContactModel toModel(ContactResponseDto entity) {
		ContactModel model = contactMapper.toModel(entity);
		model.add(linkTo(methodOn(ContactController.class).getContactByIdAndUserId(entity.id(), entity.userId()))
				.withSelfRel());
		return model;
	}

}
