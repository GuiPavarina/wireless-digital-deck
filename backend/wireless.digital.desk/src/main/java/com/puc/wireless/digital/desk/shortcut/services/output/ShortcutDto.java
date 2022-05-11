package com.puc.wireless.digital.desk.shortcut.services.output;

import java.util.List;
import java.util.stream.Collectors;

import com.puc.wireless.digital.desk.shortcut.domain.Shortcut;

public class ShortcutDto {
    private final String modifiers;
    private final int order;
    private final String key;
    private final String shortcutName;
    private final String applicationName;

    private ShortcutDto(Builder builder) {
        this.modifiers = builder.modifiers;
        this.order = builder.order;
        this.key = builder.key;
        this.shortcutName = builder.shortcutName;
        this.applicationName = builder.applicationName;
    }

    public String getModifiers() {
        return modifiers;
    }

    public int getOrder() {
        return order;
    }

    public String getKey() {
        return key;
    }

    public String getShortcutName() {
        return shortcutName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public static ShortcutDto toDto(final Shortcut shortcut) {
        return new Builder().order(shortcut.getOrder()).modifiers(shortcut.getModifiers()).key(shortcut.getKey())
                .shortcutName(shortcut.getShortcutName()).applicationName(shortcut.getApplicationName()).build();
    }

    public static List<ShortcutDto> toListOfDto(final List<Shortcut> shortcuts) {
        return shortcuts.stream().map(shortcut -> toDto(shortcut)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ShortcutDto [modifiers=" + modifiers + ", order=" + order + ", key=" + key + ", shortcutName="
                + shortcutName + ", applicationName=" + applicationName + "]";
    }

    public static class Builder {

        private int order;
        public String userName;
        private String modifiers;
        private String key;
        private String shortcutName;
        private String applicationName;

        public Builder order(final int order) {
            this.order = order;
            return this;
        }

        public Builder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public Builder modifiers(final String modifiers) {
            this.modifiers = modifiers;
            return this;
        }

        public Builder key(final String key) {
            this.key = key;
            return this;
        }

        public Builder shortcutName(final String shortcutName) {
            this.shortcutName = shortcutName;
            return this;
        }

        public Builder applicationName(final String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public ShortcutDto build() {
            return new ShortcutDto(this);
        }

    }
}
