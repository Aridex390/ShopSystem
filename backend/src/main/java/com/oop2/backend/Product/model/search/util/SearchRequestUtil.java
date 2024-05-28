package com.oop2.backend.Product.model.search.util;

import com.oop2.backend.Product.model.search.PageRequest;
import com.oop2.backend.Product.model.search.SearchRequest;

public class SearchRequestUtil {
    private static final int DEFAULT_PAGE_SIZE = 12;

    public static PageRequest toPageRequest(SearchRequest searchRequest) {
        PageRequest page;
        if(searchRequest == null) {
            page = new PageRequest(0, DEFAULT_PAGE_SIZE);
        } else {
            final int requestedSize = searchRequest.getSize();
            page = new PageRequest(searchRequest.getPage(), requestedSize == 0 ? DEFAULT_PAGE_SIZE : requestedSize);
        }

        return page;
    }
}
