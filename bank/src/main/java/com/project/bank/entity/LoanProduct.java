package com.project.bank.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Table(name="loan_product")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanProduct {


    @Id
    @Column(name="product_no")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productNo;             // 대출 번호
    private String productName;         // 대출 상품 이름
    private String loanMoney;           // 대출 금액
    private String interest;            // 이자율
    private String repaymentDate;       // 상환 날짜
//    private String loanDitail;          // 대출상품 설명


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_no")
    private Bank bank;                  // 은행 아이디



}
