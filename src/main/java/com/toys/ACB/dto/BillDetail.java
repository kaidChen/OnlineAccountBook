package com.toys.ACB.dto;

import com.toys.ACB.entity.Bill;
import com.toys.ACB.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
