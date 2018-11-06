package net.jiangchen.blog.Repository;

import java.io.File;

public interface BlogService {

    public File read(String email, int fid);

    public boolean create(File file);
}
