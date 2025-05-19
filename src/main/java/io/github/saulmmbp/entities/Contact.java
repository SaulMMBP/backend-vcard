package io.github.saulmmbp.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "contacts_phones", 
		joinColumns = @JoinColumn(name = "contact_id"),
		inverseJoinColumns = @JoinColumn(name = "phone_id"),
		uniqueConstraints = @UniqueConstraint(columnNames = {"contact_id", "phone_id"}))
	private List<Phone> phones;
	
	@ToString.Exclude
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
