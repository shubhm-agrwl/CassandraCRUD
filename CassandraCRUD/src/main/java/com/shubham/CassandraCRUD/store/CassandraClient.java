package com.shubham.CassandraCRUD.store;

import java.io.Closeable;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;

public class CassandraClient implements Closeable {

  private static final Logger logger = LoggerFactory.getLogger(CassandraClient.class);

  private static volatile CassandraClient client = null;

  private Cluster cluster = null;
  private CassandraClientConfig config = null;
  private Session session = null;

  private CassandraClient(CassandraClientConfig config) {
    this.config = config;
    setup();
  }

  public static CassandraClient getInstance(CassandraClientConfig config) {
    if (client == null) {
      synchronized (CassandraClient.class) {
        if (client == null) {
          logger.info("Instantiating Cassandra Client...");
          client = new CassandraClient(config);
        }
      }
    }
    return client;
  }

  private void setup() {

    PoolingOptions poolingOptions = new PoolingOptions();

    poolingOptions.setPoolTimeoutMillis(30000).setCoreConnectionsPerHost(HostDistance.LOCAL, 5)
        .setMaxConnectionsPerHost(HostDistance.LOCAL, 5)
        .setCoreConnectionsPerHost(HostDistance.REMOTE, 5)
        .setMaxConnectionsPerHost(HostDistance.REMOTE, 5).setIdleTimeoutSeconds(20);

    cluster = Cluster.builder().withClusterName(config.getClusterName())
        .addContactPoints(config.getHostName().split(","))
        .withSocketOptions(
            new SocketOptions().setConnectTimeoutMillis(30000).setReadTimeoutMillis(30000))
        .withPoolingOptions(poolingOptions).build();
    session = cluster.connect(config.getKeySpace());
  }

  public Session getSession() {
    return session;
  }

  public CassandraClientConfig getConfig() {
    return config;
  }

  public void close() throws IOException {
    try {
      session.close();
    } catch (Exception e) {
      logger.error("Exception closing cassandra client Session...", e);
    }
    try {
      cluster.close();
    } catch (Exception e) {
      logger.error("Exception closing cassandra cluster...", e);
    }
    try {
      client.close();
    } catch (Exception e) {
      logger.error("Exception closing cassandra client connection...", e);
    }
  }

}
