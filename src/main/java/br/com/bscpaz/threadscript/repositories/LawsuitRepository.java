package br.com.bscpaz.threadscript.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.threadscript.entities.Lawsuit;
import br.com.bscpaz.threadscript.entities.Plaintiff;

@Repository
public interface LawsuitRepository extends JpaRepository<Lawsuit, Integer> {
    
    public List<Lawsuit> findByPlaintiff(Plaintiff plaintiff);

    @Query(value = 
        "SELECT l " +
        "FROM Lawsuit l " +
        "WHERE l.isActive = true AND l.plaintiff = ?1 " +
        "ORDER BY l.numberLawsuit", nativeQuery = false)
    public List<Lawsuit> findActivedLawsuitByPlaintiff(Plaintiff plaintiff);

}
