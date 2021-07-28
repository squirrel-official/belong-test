package com.test.belong.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public abstract class AbstractController {
    public Pageable defaultPageRequest(Integer pageNumber, Integer size, Optional<Sort.Direction> direction, String... properties) {
        Integer page = (pageNumber == null) ? 0 : pageNumber;
        Integer pageSize = (size == null) ? 50 : size;
        if (direction.isEmpty()) {
            return PageRequest.of(page, pageSize);
        } else {
            Sort.Direction sortingDir = (direction.isEmpty()) ? Sort.Direction.ASC : direction.get();
            String[] sortingProperties = properties == null || properties.length == 0 ? new String[]{"id"} : properties;
            return PageRequest.of(page, pageSize, sortingDir, sortingProperties);
        }
    }
}
