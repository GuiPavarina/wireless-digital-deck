package com.puc.wireless.digital.desk.shortcut.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.wireless.digital.desk.hash.services.HashService;
import com.puc.wireless.digital.desk.hash.services.output.HashDto;
import com.puc.wireless.digital.desk.shortcut.controllers.input.ShortcutInput;
import com.puc.wireless.digital.desk.shortcut.services.ShortcutService;
import com.puc.wireless.digital.desk.shortcut.services.output.ShortcutDto;
import com.puc.wireless.digital.desk.user.service.UserService;

@RestController
public class ShortcutController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShortcutService service;

    @Autowired
    private HashService hashService;

    private static final Logger LOG = LoggerFactory.getLogger(ShortcutController.class);

    private String getAuthenticatedUserName(Map<String, String> headers) {
        Optional<String> usernameOpt = userService.getUsernameFromHeaders(headers);
        if (usernameOpt.isEmpty()) {
            return "";
        }
        return usernameOpt.get();
    }

    @GetMapping("/shortcuts")
    public ResponseEntity<List<ShortcutDto>> getAllShortcuts(@RequestHeader Map<String, String> headers) {
        final String userName = getAuthenticatedUserName(headers);
        if (userName.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.findAllShortcutsFor(userName));
    }

    @PostMapping("/shortcuts")
    public ResponseEntity<List<ShortcutDto>> createAllShortcuts(@RequestHeader Map<String, String> headers,
            @RequestBody List<ShortcutInput> inputs) {
        final String userName = getAuthenticatedUserName(headers);
        if (userName.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.badRequest().build();
        }
        final HashDto userHash = hashService.findHashFor(userName);
        return ResponseEntity.ok(service.createAllShortcuts(userHash, inputs));
    }

    @DeleteMapping("/shortcuts")
    public ResponseEntity<String> deleteAllShortcuts(@RequestHeader Map<String, String> headers) {
        final String userName = getAuthenticatedUserName(headers);
        if (userName.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.badRequest().build();
        }
        service.removeAllShortcuts(userName);
        return ResponseEntity.ok("Removed");
    }

    @PostMapping("/shortcut")
    public ResponseEntity<ShortcutDto> createShortcut(@RequestHeader Map<String, String> headers,
            @RequestBody ShortcutInput input) {
        final String userName = getAuthenticatedUserName(headers);
        if (userName.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.badRequest().build();
        }
        final HashDto userHash = hashService.findHashFor(userName);
        return ResponseEntity.ok(service.createShortcut(userHash, input));
    }

    @DeleteMapping("/shortcut")
    public ResponseEntity<?> deleteShortcut(@RequestHeader Map<String, String> headers, @RequestParam Long order) {
        final String userName = getAuthenticatedUserName(headers);
        if (userName.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.badRequest().build();
        }
        service.removeShortcut(userName, order);
        return ResponseEntity.noContent().build();
    }

}
