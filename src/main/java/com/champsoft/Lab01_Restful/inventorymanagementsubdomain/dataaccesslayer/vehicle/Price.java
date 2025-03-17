package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
@Embeddable
@Getter
@NoArgsConstructor

public class Price {
    private BigDecimal msrp;
    private BigDecimal cost;
    private BigDecimal totalOptionsCost;
    public Price(@NotNull BigDecimal msrp, @NotNull BigDecimal cost, @NotNull BigDecimal totalOptionsCost) {
        this.msrp = msrp;
        this.cost = cost;
        this.totalOptionsCost = totalOptionsCost;
    }
}
