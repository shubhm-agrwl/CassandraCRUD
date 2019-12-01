package com.shubham.CassandraCRUD.store;

import com.shubham.CassandraCRUD.config.TrainingConfiguration;

public class StoreFactory {

  private static CQLClient cqlClient = null;

  public static void initialize(TrainingConfiguration config) throws Exception {

    CassandraClient client = CassandraClient.getInstance(config.getCassandraClientConfig());
    cqlClient = new CQLClient(client);

  }

  public static CQLClient getCqlClient() {
    return cqlClient;
  }

}
