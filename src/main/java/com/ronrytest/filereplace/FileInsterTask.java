package com.ronrytest.filereplace;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class FileInsterTask {

    private static FileInster fileInster = new FileInster();
    private static String[]   newLines   = new String[] { "/*", " * Copyright 2010 Alibaba.com Croporation Limited.",
            " * All rights reserved.", " *", " * Licensed under the Apache License, Version 2.0 (the \"License\");",
            " * you may not use this file except in compliance with the License.",
            " * You may obtain a copy of the License at", " *", " *    http://www.apache.org/licenses/LICENSE-2.0",
            " *", " * Unless required by applicable law or agreed to in writing, software",
            " * distributed under the License is distributed on an \"AS IS\" BASIS,",
            " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.",
            " * See the License for the specific language governing permissions and",
            " * limitations under the License.", " *", " */" };

    public static void cycleInster(File file, String suffix) throws Exception {
        String[] filePaths = StringUtils.split(file.getName(), ".");
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    cycleInster(childFile, suffix);
                }
            }
        } else if (suffix.equals(filePaths[filePaths.length - 1])) {
            fileInster.insert(file, 1, newLines);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // File rootFile = new File("/home/ronry/projects/citrus/tools");
        // cycleInster(rootFile, "java");
        // System.out.println("bye bye!!");
        String s = "12";
        System.out.println(s.getBytes("GBK").length);
    }

}
