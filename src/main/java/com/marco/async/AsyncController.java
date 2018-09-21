package com.marco.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AsyncController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity<String> sendMessages() {

        messageService.processMessages();

        return new ResponseEntity<>("Successful sending", HttpStatus.ACCEPTED);
    }
}
