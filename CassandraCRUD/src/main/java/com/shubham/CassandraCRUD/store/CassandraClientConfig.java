package com.shubham.CassandraCRUD.store;

public class CassandraClientConfig {

  private String clusterName = "Shubham training";

  private String hostName = "127.0.0.1";

  private int port = 9160;

  private String keySpace = "shubham";

  private int replicationFactor = 2;

  public int TimeToLive = 720000;

  private boolean useServerSideCompression = false;

  private boolean useClientSideCompression = false;

  private boolean cacheAllKeys = false;

  public int getTimeToLive() {
    return TimeToLive;
  }

  public void setTimeToLive(int timeToLive) {
    TimeToLive = timeToLive;
  }

  public String getKeySpace() {
    return keySpace;
  }

  public void setKeySpace(String keySpace) {
    this.keySpace = keySpace;
  }

  public String getClusterName() {
    return clusterName;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHostPortPair() {
    return hostName + ":" + port;
  }

  public String toJsonString() {
    return null;
  }

  public int getReplicationFactor() {
    return replicationFactor;
  }

  public void setReplicationFactor(int replicationFactor) {
    this.replicationFactor = replicationFactor;
  }

  public boolean isUseServerSideCompression() {
    return useServerSideCompression;
  }

  public void setUseServerSideCompression(boolean useServerSideCompression) {
    this.useServerSideCompression = useServerSideCompression;
  }

  public boolean isCacheAllKeys() {
    return cacheAllKeys;
  }

  public void setCacheAllKeys(boolean cacheAllKeys) {
    this.cacheAllKeys = cacheAllKeys;
  }

  public boolean isUseClientSideCompression() {
    return useClientSideCompression;
  }

  public void setUseClientSideCompression(boolean useClientSideCompression) {
    this.useClientSideCompression = useClientSideCompression;
  }
}
