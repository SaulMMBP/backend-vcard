package io.github.saulmmbp.services.impl;

import org.springframework.core.io.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import io.github.saulmmbp.dtos.*;
import io.github.saulmmbp.entities.*;
import io.github.saulmmbp.exceptions.*;
import io.github.saulmmbp.repositories.*;
import io.github.saulmmbp.services.IVCardService;
import io.github.saulmmbp.utils.*;
import io.github.saulmmbp.utils.mappers.IVCardMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VCardService implements IVCardService {

    private IVCardRepository vcardRepository;
    private IContactRepository contactRepository;
    private IUserRepository userRepository;
    private IVCardMapper vcardMapper;
    private QRCodeUtils qrCodeUtils;

    @Override
    public VCardResponseDto createVCardForUser(String userId, VCardRequestDto vcardRequest) {
        if (!userRepository.existsById(userId)) throw new ResourceNotFoundException("User", userId);
        VCard vcard = vcardMapper.toEntity(vcardRequest);
        vcard.setUserId(userId);

        if (!contactRepository.existsById(vcardRequest.contactId()))
            throw new ResourceNotFoundException("Contact", vcardRequest.contactId());
        Contact contact = contactRepository.findByIdAndUserId(vcardRequest.contactId(), userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("Contact", vcardRequest.contactId(), userId));
        vcard.setContact(contact);

        String vcardStr = VCardUtils.convertToPlainTextVCard(vcard);
        byte[] qr = qrCodeUtils.generateQRCode(vcardStr);
        vcard.setQr(qr);

        VCard savedVCard = vcardRepository.save(vcard);
        return vcardMapper.toResponseDto(savedVCard);
    }

    @Override
    public Page<VCardResponseDto> getVCardsByUserId(String userId, Pageable pageable) {
        Page<VCard> vcards = vcardRepository.findByUserId(userId, pageable);
        Page<VCardResponseDto> vcardsResponse = vcards.map(vcard -> vcardMapper.toResponseDto(vcard));
        return vcardsResponse;
    }

    @Override
    public VCardResponseDto getVCardByIdAndUserId(Long vcardId, String userId) {
        if (!vcardRepository.existsById(vcardId))
            throw new ResourceNotFoundException("VCard", vcardId);
        VCard vcard = vcardRepository.findByIdAndUserId(vcardId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("VCard", vcardId, userId));
        return vcardMapper.toResponseDto(vcard);
    }

    @Override
    public VCardResponseDto updateVCardForUser(Long vcardId, String userId, VCardRequestDto vcardRequest) {
        if (!vcardRepository.existsById(vcardId))
            throw new ResourceNotFoundException("VCard", vcardId);
        VCard vcard = vcardRepository.findByIdAndUserId(vcardId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("VCard", vcardId, userId));
        vcard.setColor(vcardRequest.color());
        vcard.setName(vcardRequest.name());

        if (vcardRequest.contactId() != vcard.getContact().getId()) {
            if (!contactRepository.existsById(vcardRequest.contactId()))
                throw new ResourceNotFoundException("Contact", vcardRequest.contactId());
            Contact newContact = contactRepository.findByIdAndUserId(vcardRequest.contactId(), userId).orElseThrow(
                    () -> new ResourceNotBelongsUserException("Contact", vcardRequest.contactId(), userId));
            vcard.setContact(newContact);

            String vcardStr = VCardUtils.convertToPlainTextVCard(vcard);
            byte[] qr = qrCodeUtils.generateQRCode(vcardStr);
            vcard.setQr(qr);
        }

        VCard savedVCard = vcardRepository.save(vcard);
        return vcardMapper.toResponseDto(savedVCard);
    }

    @Override
    public void deleteVCardForUser(Long vcardId, String userId) {
        if (!vcardRepository.existsById(vcardId))
            throw new ResourceNotFoundException("VCard", vcardId);
        VCard vcard = vcardRepository.findByIdAndUserId(vcardId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("VCard", vcardId, userId));
        vcardRepository.delete(vcard);
    }

    @Override
    public Resource downloadQRImage(Long vcardId, String userId) {
        if (!vcardRepository.existsById(vcardId))
            throw new ResourceNotFoundException("VCard", vcardId);
        VCard vcard = vcardRepository.findByIdAndUserId(vcardId, userId)
                .orElseThrow(() -> new ResourceNotBelongsUserException("VCard", vcardId, userId));
        byte[] qr = vcard.getQr();
        ByteArrayResource resource = new ByteArrayResource(qr);
        return resource;
    }

}
