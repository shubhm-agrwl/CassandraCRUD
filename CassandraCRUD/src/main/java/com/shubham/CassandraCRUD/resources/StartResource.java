package com.shubham.CassandraCRUD.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.shubham.CassandraCRUD.common.CassandraTable;
import com.shubham.CassandraCRUD.common.JsonUtils;
import com.shubham.CassandraCRUD.store.StoreFactory;

@Path("/shubhamp")
public class StartResource {

  @POST
  @Path("/insert")
  @Produces(MediaType.APPLICATION_JSON)
  public Response insertData(CassandraTable cassandraTable) {

    StoreFactory.getCqlClient().insertData(cassandraTable);
    return Response.ok("Success").build();

  }

  @POST
  @Path("/delete")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteData(String id) {

    StoreFactory.getCqlClient().deleteData(id);
    return Response.ok("Success").build();

  }

  @POST
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getData(String id) {

    CassandraTable cassandraTable = StoreFactory.getCqlClient().getData(id);
    return Response.ok(JsonUtils.toJson(cassandraTable)).build();

  }

  @GET
  @Path("/getall")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllData() {

    List<CassandraTable> cassandraTables = StoreFactory.getCqlClient().getAllData();
    return Response.ok(JsonUtils.toJson(cassandraTables)).build();

  }

}
