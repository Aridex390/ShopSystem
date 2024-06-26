package com.oop2.backend.Product.model.search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageRequest extends AbstractPageRequest {
    public PageRequest(int page, int size) {
        super(page, size);
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable previous() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable first() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable withPage(int pageNumber) {
        throw new UnsupportedOperationException();
    }
}
