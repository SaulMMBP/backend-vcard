package io.github.saulmmbp.utils;

import io.github.saulmmbp.entities.VCard;

public class VCardUtils {

	public static String convertToPlainTextVCard(VCard vcard) {
		StringBuilder plainTextVCard = new StringBuilder();
		plainTextVCard.append("BEGIN:VCARD\n");
		plainTextVCard.append("VERSION:4.0\n");
		// TODO Convert VCARD
		
		plainTextVCard.append("END:VCARD");
		
		return plainTextVCard.toString();
	}
	
}
