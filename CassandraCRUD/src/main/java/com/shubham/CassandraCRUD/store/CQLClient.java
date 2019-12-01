package com.shubham.CassandraCRUD.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.shubham.CassandraCRUD.common.CassandraTable;

public class CQLClient {

  private CassandraClient client = null;
  private String keyspace = "shubham";

  private BoundStatement insertStmt = null;

  public CQLClient(CassandraClient client) {
    this.client = client;
    CassandraClientConfig config = client.getConfig();
    keyspace = config.getKeySpace();
    Session session = client.getSession();

    String stmt = null;

    stmt = "INSERT INTO " + keyspace + ".test_table" + "(id, data, active, create_ts)"
        + " VALUES (?,?,?,?);";
    insertStmt = session.prepare(stmt).setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM).bind();
  }

  public void insertData(CassandraTable config) {
    Session session = client.getSession();
    synchronized (insertStmt) {
      prepareInsertData(config);
      session.execute(insertStmt);
    }
  }

  private void prepareInsertData(CassandraTable config) {
    insertStmt.setString("id", config.getId());
    insertStmt.setString("data", config.getData());
    insertStmt.setBool("active", config.isActive());
    insertStmt.setLong("create_ts", config.getTs());
  }

  public void deleteData(String id) {

    Session session = client.getSession();

    session.execute("DELETE FROM  " + keyspace + ".test_table WHERE id='" + id + "' ;");
  }

  public CassandraTable getData(String id) {

    Session session = client.getSession();

    ResultSet result =
        session.execute("SELECT * FROM  " + keyspace + ".test_table WHERE id='" + id + "' ;");
    Row row = result.one();
    if (row == null) {
      return null;
    }
    CassandraTable cassandraTable = getCassandraTableFromRow(row);
    return cassandraTable;
  }

  private CassandraTable getCassandraTableFromRow(Row row) {
    CassandraTable cassandraTable = new CassandraTable();

    cassandraTable.setId(row.getString("id"));
    cassandraTable.setData(row.getString("data"));
    cassandraTable.setActive(row.getBool("active"));
    cassandraTable.setTs(row.getLong("create_ts"));

    return cassandraTable;
  }

  public List<CassandraTable> getAllData() {

    List<CassandraTable> cassandraTables = new ArrayList<CassandraTable>();

    Session session = client.getSession();

    ResultSet result = session.execute("SELECT * FROM  " + keyspace + ".test_table;");
    Iterator<Row> rows = result.iterator();
    while (rows.hasNext()) {
      Row row = rows.next();
      CassandraTable cassandraTable = getCassandraTableFromRow(row);
      cassandraTables.add(cassandraTable);
    }
    return cassandraTables;
  }

}
