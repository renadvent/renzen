package com.ren.renzen.Services;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.ren.renzen.additional.KEYS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;

@Service

public class ImageServiceImpl{

    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    @Autowired
    public ImageServiceImpl() {

        // Create a BlobServiceClient object which will be used to create a container client
        String connectStr= KEYS.CONNECTSTR;
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        //Create a unique name for the container
        String containerName = "renzen-test";
        // Create the container and return a container client object
        containerClient = blobServiceClient.getBlobContainerClient(containerName);

    }

    public String generateSAS(String name){

        var uri = URI.create(name);
        uri.getFragment();

        BlobClient blobClient = containerClient.getBlobClient(name);

        BlobSasPermission blobPermission = new BlobSasPermission().setReadPermission(true);

        //generate link
        var blobServiceSasSignatureValues = new BlobServiceSasSignatureValues()
                .setProtocol(SasProtocol.HTTPS_ONLY) // Users MUST use HTTPS (not HTTP).
                .setExpiryTime(OffsetDateTime.now().plusDays(2))
                .setPermissions(blobPermission);;

        var SAS = blobClient.generateSas(blobServiceSasSignatureValues);
        return blobClient.getBlobUrl()+"?"+SAS;
    }

}
