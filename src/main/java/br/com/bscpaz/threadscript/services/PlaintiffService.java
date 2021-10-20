package br.com.bscpaz.threadscript.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bscpaz.threadscript.entities.Plaintiff;
import br.com.bscpaz.threadscript.repositories.PlaintiffRepository;

@Service
public class PlaintiffService {
    
    @Autowired
    private PlaintiffRepository repository;

    public List<Plaintiff> findAll() {
        return this.repository.findAll();
    }

}
