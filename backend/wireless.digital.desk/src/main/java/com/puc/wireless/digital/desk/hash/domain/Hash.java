package com.puc.wireless.digital.desk.hash.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hashes")
public class Hash {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HASH_ID_SEQ")
    @SequenceGenerator(name = "HASH_ID_SEQ", sequenceName = "HASH_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "hash_user")
    private String user;

    @Column(name = "unique_hash")
    private String hash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Hash [id=" + id + ", user=" + user + ", hash=" + hash + "]";
    }

}
