package com.challenge4.repository;

import com.challenge4.model.merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
    @Repository
    public interface MerchantRepository extends JpaRepository<merchants, UUID> {
        @Procedure("edit_merchant_status_byName")
        void editStatusMerchant(@Param("merchant_name") String name, @Param("isOpen") boolean isOpen);

        List<merchants> findByIsOpenTrue();
    }



