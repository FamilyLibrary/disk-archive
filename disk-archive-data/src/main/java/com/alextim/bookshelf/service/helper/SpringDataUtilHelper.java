package com.alextim.bookshelf.service.helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@Component
public class SpringDataUtilHelper {
    public Pageable createPageable(int page, int size, String sort, Direction dir) {
        if (StringUtils.isNotEmpty(sort) && dir != null) {
            return new PageRequest(page, size, new Sort(dir, sort));
        }
        return new PageRequest(page, size);
    }
}
