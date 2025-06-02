package io.github.saulmmbp.representationmodels;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "contacts")
public class ContactModel extends RepresentationModel<ContactModel> {

    private Long id;
    private String userId;
    private String identifier;
    private String name;
    private String email;
    private String position;
    private String company;
    private String web;
    private List<String> phones;

}
