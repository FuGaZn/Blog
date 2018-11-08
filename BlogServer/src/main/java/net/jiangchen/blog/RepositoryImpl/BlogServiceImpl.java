package net.jiangchen.blog.RepositoryImpl;

import net.jiangchen.blog.Repository.BlogService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;

public class BlogServiceImpl implements BlogService {
    String pathPrefix = "./data/";
    @Override
    public File read(String email, String bid) {
        String path = pathPrefix+email+"/blog/"+bid+".md";
        File file = new File("path");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;

    }

    @Override
    public boolean create(String email,String bid, File file) {
        String path = pathPrefix+email+"/blog/"+bid+".md";
        File file1 = new File(path);
        try {
            if(!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            if(!file1.exists())
                file1.createNewFile();

            FileInputStream is = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(file1);
            byte b[] = new byte[1024];
            int len;
            try {
                len = is.read(b);
                while (len!=-1){
                    os.write(b,0,len);
                    len = is.read(b);
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally{
                try {
                    if(is != null) is.close();
                    if(os != null) os.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void uploadImg(String email, MultipartFile[] files,String[][] res) {
        int i = 0;
        try {
            for (MultipartFile file: files){
                this.uploadFileUtil(file.getBytes(),pathPrefix+email+"/img/",file.getOriginalFilename());
                res[i][0] = file.getOriginalFilename();
                res[i][1] = "/data/"+email+"/img/"+file.getOriginalFilename();
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void uploadFileUtil(byte[] file, String imgPath, String imgName) throws Exception {
        File targetFile = new File(imgPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(imgPath + imgName);
        out.write(file);
        out.flush();
        out.close();
    }

}
