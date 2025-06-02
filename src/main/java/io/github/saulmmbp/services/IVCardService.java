package io.github.saulmmbp.services;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.saulmmbp.dtos.VCardRequestDto;
import io.github.saulmmbp.dtos.VCardResponseDto;

public interface IVCardService {

    VCardResponseDto createVCardForUser(String userId, VCardRequestDto vcardRequest);

    Page<VCardResponseDto> getVCardsByUserId(String userId, Pageable pageable);

    VCardResponseDto getVCardByIdAndUserId(Long vcardId, String userId);

    VCardResponseDto updateVCardForUser(Long vcardId, String userId, VCardRequestDto vcardRequest);

    void deleteVCardForUser(Long vcardId, String userId);

    Resource downloadQRImage(Long vcardId, String userId);

}
