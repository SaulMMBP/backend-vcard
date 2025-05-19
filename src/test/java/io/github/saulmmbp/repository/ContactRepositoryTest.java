package io.github.saulmmbp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.saulmmbp.entities.Contact;
import io.github.saulmmbp.entities.Phone;
import io.github.saulmmbp.repositories.IContactRepository;

@SpringBootTest
class ContactRepositoryTest {

	@Autowired
	private IContactRepository contactRepository;
	private Contact savedContact;
	
	@BeforeEach
	void beforeEach() {
		Contact contact = new Contact();
		contact.setIdentifier("MiKnton");
		contact.setUserId("10");
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
	}
	
	@AfterEach
	void afterEach() {
		contactRepository.deleteById(savedContact.getId());
	}
	
	@Test
	void findByIdTest() {
		Contact actual = contactRepository.findById(savedContact.getId()).orElseGet(null);
		Contact expected = savedContact;
		assertEquals(expected, actual, "\n"+ expected.equals(actual) + "\nExpected: " + expected.toString() + "\nbut was: " + actual.toString());
	}
	
	@Test
	void updateTest() {
		savedContact.setName("Updated");
		contactRepository.save(savedContact);
		String actual = contactRepository.findById(savedContact.getId()).orElseGet(null).getName();
		String expected = "Updated";
		assertEquals(expected, actual, "\nExpected: " + expected + "\nbut was: " + actual);
	}

}
