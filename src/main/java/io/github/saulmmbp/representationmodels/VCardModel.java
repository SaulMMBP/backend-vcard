package io.github.saulmmbp.representationmodels;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.github.saulmmbp.dtos.ContactResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "vcards")
public class VCardModel extends RepresentationModel<VCardModel> {

    private Long id;
    private String name;
    private String color;
    private ContactResponseDto contact;
    private byte[] qr;

}
