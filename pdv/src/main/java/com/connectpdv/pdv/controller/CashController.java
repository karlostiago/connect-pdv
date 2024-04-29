package com.connectpdv.pdv.controller;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.mapper.CashMapper;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.dto.CashDTO;
import com.util.commons.entity.Cash;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cash")
public class CashController {

    private final CashService cashService;
    private final CashMapper cashMapper;

    public CashController(CashService cashService, CashMapper cashMapper) {
        this.cashService = cashService;
        this.cashMapper = cashMapper;
    }

    @PostMapping("/openCash")
    public ResponseEntity<?> openCash(@RequestBody CashDTO cashDTO) {
        try {
            Cash cash = cashMapper.toEntity(cashDTO);
            Cash openedCash = cashService.openCash(cash);

            CashDTO openedCashDTO = cashMapper.toDto(openedCash);
            return ResponseEntity.ok(openedCashDTO);

        } catch (NotificationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/supply/{cashId}")
    public ResponseEntity<?> supply(@PathVariable Long cashId, @RequestBody Cash cash) {
        return null;
    }
}
