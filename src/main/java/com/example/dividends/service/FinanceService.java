package com.example.dividends.service;

import com.example.dividends.model.Company;
import com.example.dividends.model.Dividend;
import com.example.dividends.model.ScrapedResult;
import com.example.dividends.persist.CompanyRepository;
import com.example.dividends.persist.DividendRepository;
import com.example.dividends.persist.entity.CompanyEntity;
import com.example.dividends.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByCompanyName(String companyName) {
        // 회사 명 기준으로 회사 정보를 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명 입니다."));

        // 조회된 회사 아이디로 정보를 조회
        List< DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        // 결과 반환
//        List<Dividend> dividends = new ArrayList<>();
//        for (var entity : dividends) {
//            dividends.add(Dividend.builder()
//                            .date(entity.getDate())
//                            .dividend(entity.getDividend())
//                            .build());
//        }

        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> Dividend.builder()
                        .date(e.getDate())
                        .dividend(e.getDividend())
                        .build())
                .collect(Collectors.toList());

        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(),
                dividends
                );
    }
}
