package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Objects;


@Embeddable
@NoArgsConstructor
@Getter

public class Option {


   private String name;

   private BigDecimal cost;
   private String description;

   public Option(@NotNull String name, @NotNull String description, @NotNull BigDecimal cost) {
      Objects.requireNonNull(this.name = name);
      Objects.requireNonNull(this.description = description);
      Objects.requireNonNull(this.cost = cost);
   }

}
