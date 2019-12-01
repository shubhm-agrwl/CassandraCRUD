CREATE KEYSPACE shubham WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;


CREATE TABLE shubham.test_table (
	id text,
	data text,
	active boolean,
	create_ts bigint,
    PRIMARY KEY (id)
);