package io.github.saulmmbp.services.impl;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import io.github.saulmmbp.dtos.*;
import io.github.saulmmbp.entities.*;
import io.github.saulmmbp.exceptions.*;
import io.github.saulmmbp.repositories.*;
import io.github.saulmmbp.services.IContactService;
import io.github.saulmmbp.utils.mappers.IContactMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ContactService implements IContactService {

    private IContactRepository contactRepository;
    private IUserRepository userRepository;
    private IContactMapper contactMapper;

    @Override
    public Page<ContactResponseDto> getContactsByUserId(String userId, Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAllByUserId(userId, pageable);
        return contacts.map(contact -> contactMapper.toResponseDto(contact));
    }

    @Override
    public ContactResponseDto getContactByIdAndUserId(Long contactId, String userId) {
        if (!contactRepository.existsById(contactId))
            throw new ResourceNotFoundException("Contact", contactId);
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("Contact", contactId, userId));
        return contactMapper.toResponseDto(contact);
    }

    @Override
    public ContactResponseDto createContactForUser(String userId, ContactRequestDto contactRequest) {
        if (!userRepository.existsById(userId)) throw new ResourceNotFoundException("User", userId);
        Contact contact = contactMapper.toEntity(contactRequest);
        log.debug("Adding contact {} for user {}", contact, userId);
        contact.setUserId(userId);
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.toResponseDto(savedContact);
    }

    @Override
    public ContactResponseDto updateContactForUser(Long contactId, String userId, ContactRequestDto contactRequest) {
        if (!contactRepository.existsById(contactId))
            throw new ResourceNotFoundException("Contact", contactId);
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("Contact", contactId, userId));
        contact.setIdentifier(contactRequest.identifier());
        contact.setName(contactRequest.name());
        contact.setCompany(contactRequest.company());
        contact.setEmail(contactRequest.email());
        contact.setPosition(contactRequest.position());
        contact.setWeb(contactRequest.web());

        List<Phone> phones = new ArrayList<>();
        contactRequest.phones().forEach(phoneNumber -> {
            for (Phone phone : contact.getPhones()) {
                if (phoneNumber.equals(phone.getPhoneNumber()))
                    phones.add(phone);
                else
                    phones.add(new Phone(phoneNumber));
            }
        });

        Contact updatedContact = contactRepository.save(contact);

        return contactMapper.toResponseDto(updatedContact);
    }

    @Override
    public void deleteContactForUser(Long contactId, String userId) {
        if (!contactRepository.existsById(contactId))
            throw new ResourceNotFoundException("Contact", contactId);
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("Contact", contactId, userId));
        contactRepository.delete(contact);
    }

}
