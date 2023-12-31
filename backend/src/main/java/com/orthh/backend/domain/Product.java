package com.orthh.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {
  Long productId;
  String name;
  String status;
  int price;
  Long userid;

  @Builder
  public Product(String name, String status, int price, Long userid) {
    this.name = name;
    this.status = status;
    this.price = price;
    this.userid = userid;
  }
}
