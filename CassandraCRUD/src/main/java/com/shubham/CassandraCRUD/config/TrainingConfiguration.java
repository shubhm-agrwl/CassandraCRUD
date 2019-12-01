package com.shubham.CassandraCRUD.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shubham.CassandraCRUD.store.CassandraClientConfig;
import io.dropwizard.Configuration;
import lombok.Data;

public @Data class TrainingConfiguration extends Configuration {

  @Valid
  @NotNull
  @JsonProperty
  private CassandraClientConfig cassandraClientConfig;

}
