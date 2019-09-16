package com.simba.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-09-16
 */
@Getter
@Setter
@ToString
public class Technologie {
    private String name;
    @JsonProperty("years_of_experience")
    private int yearsOfExperience;
}
