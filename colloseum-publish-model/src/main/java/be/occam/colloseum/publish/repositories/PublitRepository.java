package be.occam.colloseum.publit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.occam.colloseum.publit.Publit;

public interface IPublitRepository extends JpaRepository<Publit, String> {

}
