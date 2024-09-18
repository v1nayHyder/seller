package com.tech.vinay.seller.entities;

import com.tech.vinay.seller.entities.enums.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seller_bank_details")
public class SellerBankDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ifsc_id", referencedColumnName = "id")
    private IfscCodeMaster ifscCodeMaster;

    @Column(name = "ifsc_code", nullable = false)
    private String ifscCode;

    @Column(name = "passbook_canceled_cheque_url")
    private String passbookCanceledChequeUrl;

    @Column(name = "id_proof_url")
    private String idProofUrl;

    @Column(name = "account_type")
    private String accountType;

    // Add any necessary constructors, methods, or relationships here

}
