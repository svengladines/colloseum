package be.occam.colloseum.credential.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.occam.colloseum.credential.Credential;

public interface ICredentialRepository extends JpaRepository<Credential, String> {
	
	public Credential findByReference( String reference );

}
