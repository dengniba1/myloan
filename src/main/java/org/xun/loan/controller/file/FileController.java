package org.xun.loan.controller.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.dao.account.OfficerInformationRepository;
import org.xun.loan.entity.account.Account;
import org.xun.loan.entity.account.OfficerInformation;
import org.xun.loan.entity.image.ImageDto;
import org.xun.loan.service.file.FileService;
import org.xun.loan.service.file.UploadFile;
import org.xun.loan.util.UUIDGenerator;
import org.xun.loan.util.image.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * Created by liwenlong on 2017/6/1.
 */
@RestController
@RequestMapping("/file")
public class FileController{
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OfficerInformationRepository officerInformationRepository;

    @RequestMapping("")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("获取上传文件...");
        try {
            UploadFile uploadFiles = fileService.saveFile(request);

//            renderJsonDone(response, uploadFiles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getMessage(), e);
//            renderJsonError(response, "文件上传失败");
        }
    }


    @RequestMapping(value="/updateuserlogo")
    public ImageDto updateUserLogo(@RequestParam Map<String, Object> avatar_data,
                                   @RequestParam(value = "avatar_file") MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String size = (String)avatar_data.get("avatar_data");
        ObjectMapper mapper = new ObjectMapper();
        Map data = mapper.readValue(size, Map.class);
        Integer x = ((Double)data.get("x")).intValue();
        Integer y = ((Double)data.get("y")).intValue();
        Integer height = ((Double)data.get("height")).intValue();
        Integer width = ((Double)data.get("width")).intValue();
        Integer rotate = (Integer)data.get("rotate");

        ImageDto imageDto=new ImageDto();
        //获取服务器的实际路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("x:"+x+"y:"+y+"width:"+width+"height:"+height+"degree:"+rotate);
        System.out.println(realPath);
        //需要上传的路径，我的路径根据用户的和当前日期划分路径
        String resourcePath="/upload/image";
        Account account=(Account) request.getSession().getAttribute("user");
        account = accountRepository.findOne("15601840101");
        resourcePath+="/"+account.getPhoneNumber();

        if(imageFile!=null){
            try{
                //文件名
                String name= imageFile.getOriginalFilename();
                //获取时间的路径
                Date date=new Date();

                int year=date.getYear();
                int month=date.getMonth();
                int day=date.getDay();
                resourcePath+="/"+year+"/"+month+"/"+day+"/";
                File dir=new File(realPath+resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                //先把用户上传到原图保存到服务器上
                File file=new File(dir,date.getTime()+".jpg");
                imageFile.transferTo(file);
                if(file.exists()){
                    String src=realPath+resourcePath+date.getTime();

                    boolean[] flag=new boolean[6];
                    //旋转后剪裁图片
                    flag[0]= ImageUtils.cutAndRotateImage(src+".jpg", src+"_s.jpg", x, y, width, height, rotate);
                    //缩放图片,生成不同大小的图片，应用于不同的大小的头像显示
                    flag[1]= ImageUtils.scale2(src+"_s.jpg", src+"_s_200.jpg", 200, 200, true);
                    flag[2]= ImageUtils.scale2(src+"_s.jpg", src+"_s_100.jpg", 100, 100, true);
                    flag[3]= ImageUtils.scale2(src+"_s.jpg", src+"_s_50.jpg", 50, 50, true);
                    flag[4]= ImageUtils.scale2(src+"_s.jpg", src+"_s_30.jpg", 30, 30, true);
                    flag[5]= ImageUtils.scale2(file.getPath(), src+"_200.jpg", 200, 200, true);

                    if(flag[0]&&flag[1]&&flag[2]&&flag[3]&&flag[4]&&flag[5]){
                        //图像处理完成，将数据写入数据库中
                        imageDto.setYear((short) year);
                        imageDto.setMount((short)month);
                        imageDto.setDay((short)day);
                        imageDto.setName(date.getTime()+".jpg");
                        imageDto.setFileName(name);
                        imageDto.setUrl(resourcePath + date.getTime() +"_s.jpg");
                        imageDto.setPreviewUrl(resourcePath+"/"+date.getTime()+"_200.jpg");
                        imageDto.setTime(new Timestamp(date.getTime()));
                        imageDto.setWidth((short)ImageUtils.getImageWidth(file.getPath()));
                        imageDto.setHeight((short)ImageUtils.getImageHeight(file.getPath()));
                        imageDto.setStatus((short)1);
                        imageDto.setExt3(resourcePath+date.getTime()+"_s_200.jpg");
                        imageDto.setExt4(resourcePath+date.getTime()+"_s_100.jpg");
                        //设置相册，头像设置进入默认相册
                        OfficerInformation officerInformation = account.getOfficerInformation();
                        if(officerInformation==null){
                            officerInformation = new OfficerInformation();
                            officerInformation.setId(UUIDGenerator.getUUID());
                        }
                        officerInformation.setPhoto(imageDto.getUrl());
                        officerInformation = officerInformationRepository.save(officerInformation);
                        account.setOfficerInformation(officerInformation);
                        accountRepository.save(account);
                    }
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return imageDto;
            }

        }
        return imageDto;
    }
}
