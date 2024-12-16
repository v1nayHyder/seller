package com.tech.vinay.seller.services;

import com.tech.vinay.seller.entities.OndcCategoryMaster;
import com.tech.vinay.seller.models.OndcCategoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OndcCategoryMasterService {

    private static final Logger logger = LoggerFactory.getLogger(OndcCategoryMasterService.class);

    private final OndcCategoryMasterRepository ondcCategoryMasterRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OndcCategoryMasterService(OndcCategoryMasterRepository ondcCategoryMasterRepository, JdbcTemplate jdbcTemplate) {
        this.ondcCategoryMasterRepository = ondcCategoryMasterRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new category in the database.
     *
     * @param ondcCategoryMaster The category entity to be created.
     * @return The saved category entity.
     * @throws RuntimeException if the category is not saved correctly.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public OndcCategoryMaster createCategory(OndcCategoryMaster ondcCategoryMaster) {
        logger.info("Starting createCategory method with input: {}", ondcCategoryMaster);

        // Preconditions and validation can be added here
        if (ondcCategoryMaster == null) {
            logger.error("Input category is null, cannot proceed with creation.");
            throw new IllegalArgumentException("Category information cannot be null.");
        }

        try {
            OndcCategoryMaster savedCategory = ondcCategoryMasterRepository.save(ondcCategoryMaster);
            logger.info("Category created successfully: {}", savedCategory);

            // Simulating an error for demonstration purposes
            if (ondcCategoryMaster != null) { // This condition should be based on some real validation or business logic
                throw new RuntimeException("Something went wrong during category creation.");
            }

            return savedCategory;
        } catch (Exception e) {
            logger.error("Error while creating category: {}", e.getMessage());
            throw e; // Re-throw the exception to trigger rollback
        }
    }
}





//package com.tech.vinay.seller.services;
//
//
//import com.tech.vinay.seller.entities.OndcCategoryMaster;
//import com.tech.vinay.seller.models.OndcCategoryMasterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Service
//public class OndcCategoryMasterService {
//
//    private final OndcCategoryMasterRepository ondcCategoryMasterRepository;
//
//    private @Autowired JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public OndcCategoryMasterService(OndcCategoryMasterRepository ondcCategoryMasterRepository){
//        this.ondcCategoryMasterRepository=ondcCategoryMasterRepository;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public OndcCategoryMaster createCategory(OndcCategoryMaster ondcCategoryMaster){
//        System.out.println(ondcCategoryMaster);
//        OndcCategoryMaster ondcCategoryMaster1=ondcCategoryMasterRepository.save(ondcCategoryMaster);
//        if (ondcCategoryMaster!=null){
//            throw  new RuntimeException("something went wrong......");
//        }
//        return ondcCategoryMaster1;
//    }
//}
