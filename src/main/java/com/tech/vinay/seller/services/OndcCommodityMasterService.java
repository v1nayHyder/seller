package com.tech.vinay.seller.services;

import com.tech.vinay.seller.commons.PersistenceService;
import com.tech.vinay.seller.entities.OndcCommodityMaster;
import com.tech.vinay.seller.models.OndcCategoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OndcCommodityMasterService {

    private final PersistenceService persistenceService;
    private final OndcCategoryMasterRepository ondcCategoryMasterRepository;

    @Autowired
    public OndcCommodityMasterService(PersistenceService persistenceService, OndcCategoryMasterRepository ondcCategoryMasterRepository, OndcCategoryMasterRepository ondcCategoryMasterRepository1) {
        this.persistenceService = persistenceService;
        this.ondcCategoryMasterRepository = ondcCategoryMasterRepository1;
    }

    public OndcCommodityMaster createCategory(OndcCommodityMaster ondcCommodityMaster) {
        System.out.println(ondcCommodityMaster.getOndcCategoryMaster());
        return persistenceService.save(ondcCommodityMaster);
    }
}
