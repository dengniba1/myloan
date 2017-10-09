package org.xun.loan.service.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xun.loan.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by liwenlong on 2017/6/1.
 */
@Service
public class FileService {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    public UploadFile saveFile(HttpServletRequest request) throws IOException {
        logger.debug("??????...");

        // ????????request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // ????file??
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator<String> fileIterator = multipartRequest.getFileNames();

        // ??????????http://localhost:8080/file?
        String requestURL = request.getRequestURL().toString();
        String prePath = requestURL.substring(0, requestURL.indexOf(Constants.ctx));

        while (fileIterator.hasNext()) {
            String fileKey = fileIterator.next();
            logger.debug("?????" + fileKey);

            // ??????
            MultipartFile multipartFile = fileMap.get(fileKey);

            if (multipartFile.getSize() != 0L) {

                validateImage(multipartFile);

                // ??saveImage????
                UploadFile file = saveImage(multipartFile);
                file.setPrePath(prePath);

                return file;
            }
        }

        return null;
    }

    private UploadFile saveImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        logger.debug("???????:" + originalFilename);

        String contentType = image.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);
        String fileName = new Random().nextInt(100) + "." + type;

        // ????????file??????????
        UploadFile file = new UploadFile(Constants.save_directory, fileName);
        file.setContentType(contentType);
        logger.debug("???????" + file.getSaveDirectory());

        // ??org.apache.commons.io.FileUtils?writeByteArrayToFile???????
        FileUtils.writeByteArrayToFile(file.getFile(), image.getBytes());

        return file;
    }

    private void validateImage(MultipartFile image) {
    }
}
