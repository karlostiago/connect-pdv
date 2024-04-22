package com.connectpdv.pdv.controller;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.entity.cash.Cash;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash")
public class CashController {

    private final CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @PostMapping("/openCash")
    public ResponseEntity<?> openCash(@RequestBody Cash cash) {
        try {
            Cash openedCash = cashService.openCash(cash);
            return ResponseEntity.ok(openedCash);
        } catch (NotificationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
