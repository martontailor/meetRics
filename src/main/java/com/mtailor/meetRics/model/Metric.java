package com.mtailor.meetRics.model;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.math.BigDecimal;

/**
 * @Todo Replace this with Java records
 * Dataholder object for passing metric to Python code.
 */
@Builder
@Data
public class Metric {
    public String name;
    public double[] values;
    public long earliest;
    public long latest;

}
