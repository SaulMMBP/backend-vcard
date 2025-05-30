package io.github.saulmmbp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private String identifier;
	private String name;
	private String email;
	private String position;
	private String company;
	private String web;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id", nullable = false)
	private List<Phone> phones = new ArrayList<>();
	
	private AuditMetadata auditMetadata;

	@Override
	public int hashCode() {
		return Objects.hash(company, email, id, identifier, name, phones, position, userId, web);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		
		boolean isEqualPhones = other.phones.containsAll(phones);
		isEqualPhones = phones.size() == other.getPhones().size();
		
		return Objects.equals(company, other.company) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(name, other.name) && isEqualPhones
				&& Objects.equals(position, other.position) && Objects.equals(userId, other.userId)
				&& Objects.equals(web, other.web);
	}
	
}
