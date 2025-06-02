package io.github.saulmmbp.utils;

import io.github.saulmmbp.entities.VCard;

public class VCardUtils {

    public static String convertToPlainTextVCard(VCard vcard) {
        StringBuilder plainTextVCard = new StringBuilder();
        plainTextVCard.append("BEGIN:VCARD\n");
        plainTextVCard.append("VERSION:4.0\n");
        plainTextVCard.append("FN:" + vcard.getContact().getName() + "\n");

        if (vcard.getContact().getCompany() != null)
            plainTextVCard.append("ORG:" + vcard.getContact().getCompany() + "\n");

        if (vcard.getContact().getEmail() != null)
            plainTextVCard.append("EMAIL:" + vcard.getContact().getEmail() + "\n");

        if (vcard.getContact().getPosition() != null)
            plainTextVCard.append("ROLE:" + vcard.getContact().getPosition() + "\n");

        if (vcard.getContact().getWeb() != null)
            plainTextVCard.append("URL:" + vcard.getContact().getWeb() + "\n");

        vcard.getContact().getPhones().forEach(phone -> {
            plainTextVCard.append("TEL:" + phone.getPhoneNumber() + "\n");
        });

        plainTextVCard.append("END:VCARD");

        return plainTextVCard.toString();
    }

}
