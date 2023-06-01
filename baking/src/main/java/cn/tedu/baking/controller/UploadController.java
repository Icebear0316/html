package cn.tedu.baking.controller;


import cn.tedu.baking.response.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/upload")
public class UploadController {
    @RequestMapping("")
    //file要与personal.html中的el-upload下的name相同
    public ResultVO upload(MultipartFile file) throws IOException {
        //得到上传文件的名称
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //得到文件的后缀名  从最后一个.的位置截取到最后
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //得到唯一文件名   UUID.randomUUID()得到一个唯一标识符
        fileName = UUID.randomUUID()+suffix;
        System.out.println(fileName);
        //准备保存文件的文件夹路径
        String dirPath = "c:/files";
        //准备日期路径(用来解决数据太大都存到一个路径下)
        //  yyyy年 MM月  dd日      HH小时 mm分 ss秒
        SimpleDateFormat f = new SimpleDateFormat("/yyyy/MM/dd/");
        //new Data()当前的时间
        String dataPath = f.format(new Date());
        File dirFile = new File(dirPath+dataPath);
        //如果文件夹不存在  则创建
        if (!dirFile.exists()){
            dirFile.mkdirs();//创建文件夹
        }
        //把图片保存进文件夹  c:/files/2023/06/1/xxxx.jpg  异常抛出
        file.transferTo(new File(dirPath+dataPath+fileName));
        //把图片路径     /2023/06/1/xxxx.jpg   响应给客户端
        return ResultVO.ok(dataPath+fileName);
    }
    @RequestMapping("remove")
    public void remove(String url){
        // url =  /2023/06/1/xxxx.jpg
        //完整路径   c:/files/2023/06/1/xxxx.jpg
        //删除和路径对应的图片文件
        new File("c:/files"+url).delete();
    }
}
