package com.ronrytest.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    public static void nestDeleteFile(File path, List<String> deleteFileNames) {
        if (path.isFile()) {
            if (deleteFileNames.contains(path.getName())) {
                System.out.println(path.getAbsolutePath());
                path.delete();
            }
        } else {
            File[] subDics = path.listFiles();
            if (subDics != null) {
                for (File subDic : subDics) {
                    if (deleteFileNames.contains(subDic.getName())) {
                        System.out.println(subDic.getAbsolutePath());
                        System.out.println(subDic.delete());
                        System.out.println(subDic.canWrite());
                    } else {
                        nestDeleteFile(subDic, deleteFileNames);
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        nestDeleteFile(new File("/home/ronry/softs/discuz_bk2/upload"), Arrays.asList(new String[] { ".svn" }));
    }

}
