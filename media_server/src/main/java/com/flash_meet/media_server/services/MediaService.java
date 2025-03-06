package com.flash_meet.media_server.services;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public String uploadFile(MultipartFile file, String userId, int index) {
        try {
            String fileName = userId + "-" + "profileImage" + "-" + index;
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error al subir el archivo a MinIO", e);
        }
    }

    public byte[] getFile(String filename) throws Exception {
        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucketName).object(filename).build())) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (MinioException | IOException e) {
            throw new Exception("Error al obtener el archivo desde MinIO", e);
        }
    }
}
