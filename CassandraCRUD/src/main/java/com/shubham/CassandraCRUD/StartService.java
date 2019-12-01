package com.shubham.CassandraCRUD;

import com.shubham.CassandraCRUD.config.TrainingConfiguration;
import com.shubham.CassandraCRUD.resources.StartResource;
import com.shubham.CassandraCRUD.store.StoreFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartService extends Application<TrainingConfiguration> {

  @Override
  protected void bootstrapLogging() {}

  public static void main(String[] args) throws Exception {
    new StartService().run(args);
  }

  @Override
  public void run(TrainingConfiguration configuration, Environment environment) throws Exception {

    // Registering StartResource
    environment.jersey().register(new StartResource());

    // Intitialising Cassandra Factory
    StoreFactory.initialize(configuration);

    log.info("Service Started Successfully");

  }
}
