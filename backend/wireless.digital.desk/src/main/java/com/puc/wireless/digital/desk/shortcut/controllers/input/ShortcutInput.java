package com.puc.wireless.digital.desk.shortcut.controllers.input;

import com.puc.wireless.digital.desk.shortcut.domain.Shortcut;

public class ShortcutInput {

    private String modifiers;
    private int order;
    private String key;
    private String shortcutName;
    private String applicationName;

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

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Shortcut fromDto() {
        final Shortcut shortcut = new Shortcut();
        shortcut.setModifiers(modifiers);
        shortcut.setOrder(order);
        shortcut.setKey(key);
        shortcut.setShortcutName(shortcutName);
        shortcut.setApplicationName(applicationName);
        return shortcut;
    }

    @Override
    public String toString() {
        return "ShortcutInput [modifiers=" + modifiers + ", order=" + order + ", key=" + key + ", shortcutName="
                + shortcutName + ", applicationName=" + applicationName + "]";
    }

}
