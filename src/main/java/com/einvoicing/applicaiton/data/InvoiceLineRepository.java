package com.einvoicing.applicaiton.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long>, JpaSpecificationExecutor<InvoiceLine> {

}
