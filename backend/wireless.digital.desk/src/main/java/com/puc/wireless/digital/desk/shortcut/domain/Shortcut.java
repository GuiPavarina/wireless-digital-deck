package com.puc.wireless.digital.desk.shortcut.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.puc.wireless.digital.desk.hash.domain.Hash;

@Entity
@Table(name = "shortcuts", uniqueConstraints = {
        @UniqueConstraint(name = "shortcuts_shortcut_order_shortcut_user_key", columnNames = { "shortcut_order", "shortcut_user" }) })
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORTCUT_ID_SEQ")
    @SequenceGenerator(name = "SHORTCUT_ID_SEQ", sequenceName = "SHORTCUT_ID_SEQ", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "modifiers")
    private String modifiers;

    @Column(name = "shortcut_order")
    private int order;

    @Column(name = "shortcut_key")
    private String key;

    @Column(name = "shortcut_name")
    private String shortcutName;

    @Column(name = "application_name")
    private String applicationName;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shortcut_user", referencedColumnName = "hash_user")
    private Hash user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShortcutName() {
        return shortcutName;
    }

    public void setShortcutName(String shortcutName) {
        this.shortcutName = shortcutName;
    }

    public Hash getUser() {
        return user;
    }

    public void setUser(Hash user) {
        this.user = user;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public String toString() {
        return "Shortcut [id=" + id + ", modifiers=" + modifiers + ", order=" + order + ", key=" + key
                + ", shortcutName=" + shortcutName + ", user=" + user + ", applicationName=" + applicationName + "]";
    }

}
