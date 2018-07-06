package com.mediheroes.mediheroes.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private final String database;
    private final String host;
    private final Integer port;

    public MongoConfig(
        @Value("${spring.data.mongodb.database}") String database,
        @Value("${spring.data.mongodb.host}") String host,
        @Value("${spring.data.mongodb.port}") Integer port
    ) {
        this.database = database;
        this.host = host;
        this.port = port;
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    // TODO Remove with https://jira.spring.io/browse/DATAMONGO-2020
    // This is a Ugly hack, due to no accessing InputStream on GridFSFile <-> GridFSDBFile
    @Bean public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory().getDb();
        return GridFSBuckets.create(db);
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

}
