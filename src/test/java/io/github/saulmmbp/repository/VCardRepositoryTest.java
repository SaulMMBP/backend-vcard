package io.github.saulmmbp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.saulmmbp.entities.Contact;
import io.github.saulmmbp.entities.Phone;
import io.github.saulmmbp.entities.VCard;
import io.github.saulmmbp.repositories.IContactRepository;
import io.github.saulmmbp.repositories.IVCardRepository;

@SpringBootTest
class VCardRepositoryTest {
	
	@Autowired
	private IContactRepository contactRepository;
	private Contact savedContact;
	private String userId = "10";
	
	@Autowired
	private IVCardRepository vCardRepository;
	private List<VCard> expected = new ArrayList<>();
	
	@BeforeEach
	void beforeEach() {
		Contact contact = new Contact();
		contact.setIdentifier("MiKnton");
		contact.setUserId(userId);
		contact.setName("Saul Malagon Martinez");
		contact.setEmail("smalagonmtz@gmail.com");
		contact.setPosition("Developer");
		contact.setCompany("Cinthiattek");
		contact.setWeb("myfokinweb.com");
		
		Phone phone1 = new Phone();
		phone1.setPhoneNumber("+525514423147");
		Phone phone2 = new Phone();
		phone2.setPhoneNumber("+525513425334");
		
		contact.setPhones(List.of(phone1, phone2));
		
		savedContact = contactRepository.save(contact);
		
		VCard vcard = new VCard();
		vcard.setColor("#ffffff");
		vcard.setContact(savedContact);
		vcard.setName("Mi tarjeta de contacto");
		vcard.setUserId(userId);
		
		VCard savedVCard = vCardRepository.save(vcard);
		expected.add(savedVCard);
	}
	
	@AfterEach
	void afterEach() {
		expected.forEach(vcard -> vCardRepository.deleteById(vcard.getId()));
		contactRepository.deleteById(savedContact.getId());
	}
	
	@Test
	void findByUserId() {
		List<VCard> actual = vCardRepository.findByUserId(userId);
		assertEquals(expected, actual);
	}

}
