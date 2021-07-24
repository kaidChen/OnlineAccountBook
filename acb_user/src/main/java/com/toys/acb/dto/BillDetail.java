package com.toys.acb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(value = {"handler"})
@EqualsAndHashCode(callSuper = true)
@Data
public class BillDetail extends Bill {
    Type type;

    @Override
    public String toString() {
        String sup_str = super.toString();
        return getClass().getSimpleName() +
                "[" + sup_str +
                ", type=" + type +
                "]";
    }
}

