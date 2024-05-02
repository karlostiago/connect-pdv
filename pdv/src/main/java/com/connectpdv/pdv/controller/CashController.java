package com.connectpdv.pdv.controller;

import com.connectpdv.pdv.exceptions.notify.NotificationException;
import com.connectpdv.pdv.mapper.CashMapper;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.dto.CashDTO;
import com.util.commons.entity.Cash;
import com.util.commons.filter.CashFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/filter-cash")
    public ResponseEntity<List<CashDTO>> filterCash(@RequestBody CashFilter filter) {
        try {
            List<Cash> filterCash = cashService.getOpeningCashBy(filter);

            if (filterCash == null || filterCash.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<CashDTO> openingCashDTO = cashMapper.toList(filterCash);
            return ResponseEntity.ok(openingCashDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/opening-cash")
    public ResponseEntity<List<CashDTO>> allOpeningCash() {
        try {
            List<Cash> openingCash = cashService.findAllOpeningCash();

            if (openingCash == null || openingCash.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<CashDTO> openingCashDTO = cashMapper.toList(openingCash);
            return ResponseEntity.ok(openingCashDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
