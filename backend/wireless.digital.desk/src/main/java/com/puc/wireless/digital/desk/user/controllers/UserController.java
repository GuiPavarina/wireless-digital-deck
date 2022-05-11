package com.puc.wireless.digital.desk.user.controllers;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.puc.wireless.digital.desk.hash.services.HashService;
import com.puc.wireless.digital.desk.hash.services.output.HashDto;
import com.puc.wireless.digital.desk.user.controllers.dto.UserInfo;
import com.puc.wireless.digital.desk.user.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HashService hashService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfo> getUserinfo(@RequestHeader Map<String, String> headers) {
        LOG.info("Getting user info");

        Optional<String> usernameOpt = userService.getUsernameFromHeaders(headers);

        if (usernameOpt.isEmpty()) {
            LOG.info("Username not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final UserInfo userInfo = new UserInfo(usernameOpt.get());
        final String username = userInfo.getUsername();
        LOG.info("Getting hashes infos -> " + username);

        final HashDto hash = hashService.findHashFor(username);
        userInfo.setHash(hash.getHash());

        return ResponseEntity.ok(userInfo);
    }

}
