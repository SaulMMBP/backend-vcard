package io.github.saulmmbp.utils.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import io.github.saulmmbp.controllers.VCardController;
import io.github.saulmmbp.dtos.VCardResponseDto;
import io.github.saulmmbp.representationmodels.VCardModel;
import io.github.saulmmbp.utils.mappers.IVCardMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VCardModelAssembler implements RepresentationModelAssembler<VCardResponseDto, VCardModel> {

    private IVCardMapper vcardMapper;

    @Override
    public VCardModel toModel(VCardResponseDto entity) {
        VCardModel model = vcardMapper.toModel(entity);
        model.add(linkTo(methodOn(VCardController.class).getVCardByIdAndUserId(entity.userId(), entity.id()))
                .withSelfRel());
        return model;
    }

}
