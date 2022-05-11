package com.puc.wireless.digital.desk.hash.services.output;

import java.util.List;
import java.util.stream.Collectors;

import com.puc.wireless.digital.desk.hash.domain.Hash;

public class HashDto {

    private final Long id;
    private final String user;
    private final String hash;

    public HashDto(final Long id, final String user, final String hash) {
        this.id = id;
        this.user = user;
        this.hash = hash;
    }

    public String getUser() {
        return user;
    }

    public String getHash() {
        return hash;
    }

    public Long getId() {
        return id;
    }

    public Hash fromDto() {
        final Hash hash = new Hash();
        hash.setId(this.id);
        hash.setUser(this.user);
        hash.setHash(this.hash);
        return hash;
    }

    public static HashDto toDto(Hash hash) {
        return new HashDto(hash.getId(), hash.getUser(), hash.getHash());
    }

    public static List<HashDto> toListOfDto(List<Hash> hashes) {
        return hashes.stream().map(hash -> toDto(hash)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "HashDto [id=" + id + ", user=" + user + ", hash=" + hash + "]";
    }

}
