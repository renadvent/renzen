//package com.ren.renzen.additional;
//
//import com.microsoft.azure.storage.blob.StorageException;
//import com.microsoft.rest.v2.protocol.Environment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.URISyntaxException;
//import java.security.InvalidKeyException;
//
//@Configuration
//public class BeanConfig {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public CloudBlobClient cloudBlobClient() throws URISyntaxException, StorageException, InvalidKeyException {
//        CloudStorageAccount storageAccount = CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
//        return storageAccount.createCloudBlobClient();
//    }
//
//    @Bean
//    public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
//        return cloudBlobClient().getContainerReference(environment.getProperty("azure.storage.container.name"));
//    }
//
//}