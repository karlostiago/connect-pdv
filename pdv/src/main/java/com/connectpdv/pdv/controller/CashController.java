package com.connectpdv.pdv.controller;

import com.connectpdv.pdv.mapper.CashMapper;
import com.connectpdv.pdv.service.CashService;
import com.util.commons.dto.CashDTO;
import com.util.commons.entity.Cash;
import com.util.commons.filter.CashFilter;
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
        Cash cash = cashMapper.toEntity(cashDTO);
        Cash openedCash = cashService.openCash(cash);

        CashDTO openedCashDTO = cashMapper.toDto(openedCash);
        return ResponseEntity.ok(openedCashDTO);
    }

    @GetMapping("/filter-cash")
    public ResponseEntity<List<CashDTO>> filterCash(@RequestBody CashFilter filter) {
        List<Cash> filterCash = cashService.getOpeningCashBy(filter);

        if (filterCash == null || filterCash.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CashDTO> openingCashDTO = cashMapper.toList(filterCash);
        return ResponseEntity.ok(openingCashDTO);
    }

    @GetMapping("/all-cash")
    public ResponseEntity<List<CashDTO>> allOpeningCash() {
        List<Cash> filterCash = cashService.findAllOpeningCash();

        if (filterCash == null || filterCash.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CashDTO> openingCashDTO = cashMapper.toList(filterCash);
        return ResponseEntity.ok(openingCashDTO);
    }
}
