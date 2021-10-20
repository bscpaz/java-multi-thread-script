package br.com.bscpaz.threadscript.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.threadscript.entities.Plaintiff;

@Repository
public interface PlaintiffRepository extends JpaRepository<Plaintiff, Integer> {
    
}
