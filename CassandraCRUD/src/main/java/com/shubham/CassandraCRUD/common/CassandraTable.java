package com.shubham.CassandraCRUD.common;

import lombok.Data;

@Data
public class CassandraTable {

  private String id;

  private String data;

  private boolean active;

  private long ts;

}
