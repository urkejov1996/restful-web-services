package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FilteringController {



    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return getMappingJacksonValue(new MappingJacksonValue(someBean), Set.of("field1","field3"));
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3")
                ,new SomeBean("value4", "value5", "value6"));

        return getMappingJacksonValue(new MappingJacksonValue(someBeans),Set.of("field2","field3"));

    }

    private static MappingJacksonValue getMappingJacksonValue(MappingJacksonValue someBean, Set<String> fields) {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        someBean.setFilters(filters);
        return someBean;
    }
}
