package com.mtailor.meetrics.converter;

import com.mtailor.meetrics.model.filter.BasicFilter;
import com.mtailor.meetrics.model.request.BasicMetricRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicRequestMapper {
    BasicFilter mapRequestToInnerObject(BasicMetricRequest filter);
}
