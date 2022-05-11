package com.puc.wireless.digital.desk.shortcut.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.wireless.digital.desk.hash.services.output.HashDto;
import com.puc.wireless.digital.desk.shortcut.controllers.input.ShortcutInput;
import com.puc.wireless.digital.desk.shortcut.domain.Shortcut;
import com.puc.wireless.digital.desk.shortcut.repositories.ShortcutRepository;
import com.puc.wireless.digital.desk.shortcut.services.output.ShortcutDto;

@Service
public class ShortcutService {

    @Autowired
    private ShortcutRepository repository;

    public List<ShortcutDto> findAllShortcutsFor(final String user) {
        final Optional<List<Shortcut>> shortcut = repository.findByUser(user);
        if (shortcut.isPresent()) {
            return ShortcutDto.toListOfDto(shortcut.get());
        }
        return new ArrayList<>();
    }

    public ShortcutDto createShortcut(final HashDto userHash, final ShortcutInput input) {
        final Shortcut shortcut = input.fromDto();
        shortcut.setUser(userHash.fromDto());
        return ShortcutDto.toDto(repository.save(shortcut));
    }

    public List<ShortcutDto> createAllShortcuts(final HashDto userHash, final List<ShortcutInput> inputs) {
        List<Shortcut> shortcuts = inputs.stream().map(input -> {
            final Shortcut shortcut = input.fromDto();
            shortcut.setUser(userHash.fromDto());
            return shortcut;
        }).collect(Collectors.toList());
        return ShortcutDto.toListOfDto(repository.saveAll(shortcuts));
    }

    public void removeShortcut(final String userName, final Long order) {
        repository.deleteByUserAndOrder(userName, order);
    }

    public void removeAllShortcuts(final String userName) {
        repository.deleteByUser(userName);
    }
}
