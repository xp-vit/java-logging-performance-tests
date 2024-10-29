package com.patotski.performance.lombok;

import java.math.BigDecimal;

public record BigDecimalTuple(BigDecimal first, BigDecimal second) {
  public static BigDecimalTupleBuilder builder() {
    return new BigDecimalTupleBuilder();
  }

  public static class BigDecimalTupleBuilder {
    private BigDecimal first;
    private BigDecimal second;

    BigDecimalTupleBuilder() {
    }

    public BigDecimalTupleBuilder first(BigDecimal first) {
      this.first = first;
      return this;
    }

    public BigDecimalTupleBuilder second(BigDecimal second) {
      this.second = second;
      return this;
    }

    public BigDecimalTuple build() {
      return new BigDecimalTuple(this.first, this.second);
    }

    public String toString() {
      return "BigDecimalTuple.BigDecimalTupleBuilder(first=" + this.first + ", second=" + this.second + ")";
    }
  }
}
