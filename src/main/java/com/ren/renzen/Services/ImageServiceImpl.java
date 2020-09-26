package com.ren.renzen.Services;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.ren.renzen.DomainObjects.ImageDO;
import com.ren.renzen.Repositories.ImageRepository;
import com.ren.renzen.Services.Interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ImageServiceImpl implements ImageService {

    final ImageRepository imageRepository;
//    BlobServiceClient blobServiceClient;
//    BlobContainerClient containerClient;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {

        this.imageRepository = imageRepository;

//        // Create a BlobServiceClient object which will be used to create a container client
//        String connectStr="DefaultEndpointsProtocol=https;AccountName=renzenblob;AccountKey=75sYRhsaFVIOhqGeOzKkm4YqRMTkFFCavrg2WSuVR64lT/tKJSQM5j/HQUdkYcxuWuyx61BI47u+2VBjYhp4rw==;EndpointSuffix=core.windows.net";
//        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
//
//        //Create a unique name for the container
//        String containerName = "quickstartblobs" + java.util.UUID.randomUUID();
//
//        // Create the container and return a container client object
//        containerClient = blobServiceClient.createBlobContainer(containerName);

    }

    @Override
    public void save(ImageDO imageDO) {
        imageRepository.save(imageDO);
    }

    @Override
    public Optional<ImageDO> getImage(String id) {
        return imageRepository.findById(id);
    }

}
