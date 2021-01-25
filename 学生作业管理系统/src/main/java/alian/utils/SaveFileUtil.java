package alian.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveFileUtil {
    /*存储文件*/
    static public boolean  savefile(String path, String filename, MultipartFile file)
    {
        File f = new File(path,filename);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.transferTo(f);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}

