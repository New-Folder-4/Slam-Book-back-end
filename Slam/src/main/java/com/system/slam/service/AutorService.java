package com.system.slam.service;

import com.system.slam.entity.Autor;
import com.system.slam.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public Autor createAuthor(String firstName, String lastName) {
        Autor autor = new Autor();
        autor.setFirstName(firstName);
        autor.setLastName(lastName);
        return autorRepository.save(autor);
    }

    public Autor getAuthor(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Author not found, id=" + autorId));
    }

    public Autor updateAuthor(Long autorId, String newFirstName, String newLastName) {
        Autor autor = getAuthor(autorId);
        autor.setFirstName(newFirstName);
        autor.setLastName(newLastName);
        return autorRepository.save(autor);
    }

    public void deleteAuthor(Long autorId) {
        autorRepository.deleteById(autorId);
    }

    public Optional<Autor> findByFirstNameAndLastName(String firstName, String lastName) {
        return autorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}

