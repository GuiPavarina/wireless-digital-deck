package com.puc.wireless.digital.desk.hash.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.puc.wireless.digital.desk.hash.domain.Hash;
import com.puc.wireless.digital.desk.hash.repository.HashRepository;

@Service
public class HashService {

    @Autowired
    private HashRepository repository;

    private static final Random RANDOM = new Random();
    private static final int MAX_HASH_CHAR_LENGTH = 6;

    public List<Hash> listAllHashes() {
        return repository.findAll();
    }

    public Optional<Hash> findHashFor(final String user) {
        Optional<Hash> found = repository.findByUser(user);
        if (found.isPresent()) {
            return found;
        }
        Hash hash = new Hash();
        hash.setUser(user);
        hash.setHash(generateHash(user));
        repository.save(hash);
        return Optional.of(hash);
    }

    private String generateHash(final String user) {
        String generatedHash = null;
        while (generatedHash == null) {
            String generatedId = NanoIdUtils.randomNanoId(RANDOM, user.toCharArray(), MAX_HASH_CHAR_LENGTH);
            Optional<Hash> hashFound = repository.findByHash(generatedId);
            if (hashFound.isEmpty()) {
                generatedHash = generatedId;
            }
        }
        return generatedHash;
    }

}
