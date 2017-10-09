package org.xun.loan.controller.file;

import org.xun.loan.entity.account.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by liwenlong on 2017/9/12.
 */
@RestController
@RequestMapping("/element/file")
public class ElementUploadController {

    @RequestMapping(value="/uploadFile")
    public void updateUserLogo(@RequestParam(value = "file") MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response){
        Account account=(Account) request.getSession().getAttribute("user");
        String resourcePath="/Users/liwenlong/images/logo/";
        if(imageFile!=null){
            try {
                File dir=new File(resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                //文件名
                String name= imageFile.getOriginalFilename();
                File file=new File(dir,name);
                imageFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

