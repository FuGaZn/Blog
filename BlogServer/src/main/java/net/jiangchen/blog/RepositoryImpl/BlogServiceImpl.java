package net.jiangchen.blog.RepositoryImpl;

import net.jiangchen.blog.Repository.BlogService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class BlogServiceImpl implements BlogService {

    @Override
    public File read(String email, int fid) {
        File file = new File("test.txt");

        try {
            if(file == null)
                file.createNewFile();
            System.out.println((new FileReader(file)).read());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean create(File file) {
        return false;
    }
}
