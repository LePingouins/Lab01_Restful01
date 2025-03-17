package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Option {

    private String name;
    private String description;
    private BigDecimal cost;

    // Constructor with non-null fields
    public Option(@NotNull String name, @NotNull String description, @NotNull BigDecimal cost) {
        Objects.requireNonNull(this.name = name);
        Objects.requireNonNull(this.description = description);
        Objects.requireNonNull(this.cost = cost);
    }
}
