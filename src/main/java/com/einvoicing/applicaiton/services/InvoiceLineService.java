package com.einvoicing.applicaiton.services;

import com.einvoicing.applicaiton.data.InvoiceLine;
import com.einvoicing.applicaiton.data.InvoiceLineRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class InvoiceLineService {

    private final InvoiceLineRepository repository;

    public InvoiceLineService(InvoiceLineRepository repository) {
        this.repository = repository;
    }

    public Optional<InvoiceLine> get(Long id) {
        return repository.findById(id);
    }

    public InvoiceLine update(InvoiceLine entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<InvoiceLine> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<InvoiceLine> list(Pageable pageable, Specification<InvoiceLine> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
