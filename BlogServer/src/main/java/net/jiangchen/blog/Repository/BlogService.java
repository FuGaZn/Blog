package net.jiangchen.blog.Repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface BlogService {

    public File read(String email, String bid);

    public boolean create(String email,String bid,File file);

    public void uploadImg(String email, MultipartFile[] files,String[][] res);
}
