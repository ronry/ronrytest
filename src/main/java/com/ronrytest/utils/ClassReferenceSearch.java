/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类ClassReferenceSearch.java的实现描述：TODO 类实现描述
 * 
 * @author ronry 2011-3-9 下午10:40:04
 */
public class ClassReferenceSearch {

    private String      path;
    private Set<String> classNameList       = new LinkedHashSet<String>();
    private Set<String> unUsedClassNameList = new LinkedHashSet<String>();
    private String      basePackage;
    private File        rootDir;

    public ClassReferenceSearch(String path, String basePackage){
        this.path = path;
        rootDir = new File(path);
        this.basePackage = basePackage;
    }

    public void search() throws Exception {
        searchClass(new File(this.path));
        // removeUsedClass(new File(this.path));
        System.out.println(classNameList.size());
        for (String className : classNameList) {
            if (checkClassUsedByDir(rootDir, className)) {
                System.out.println(className + " is used");
            } else {
                unUsedClassNameList.add(className);
                System.out.println(className + " is not used");
            }
        }
        System.out.println(unUsedClassNameList.size());
        for (String unUsedClass : unUsedClassNameList) {
            System.out.println(unUsedClass);
        }
    }

    private void searchClass(File subDirectory) {
        if (subDirectory.exists() && subDirectory.isDirectory()) {
            File[] subFiles = subDirectory.listFiles();
            if (subFiles != null && subFiles.length > 0) {
                for (File subFile : subFiles) {
                    if (subFile.isDirectory()) {
                        searchClass(subFile);
                    } else if (subFile.getName().endsWith(".java") && !subFile.getPath().contains("java.test")
                               && !subFile.getPath().contains(File.separator + "module" + File.separator)
                               && !subFile.getPath().contains(File.separator + "task" + File.separator)) {

                        try {
                            String clazz = subFile.getPath().split("java" + File.separator)[1].split(".java")[0].replace(File.separator,
                                                                                                                         ".");
                            if (clazz.contains(basePackage)) {
                                classNameList.add(clazz);
                            }
                        } catch (Exception e) {
                            System.out.println(subFile.getPath());
                        }

                    }
                }
            }
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassReferenceSearch search = new ClassReferenceSearch("/home/ronry/projects/exodus2", "com.alibaba.exodus2");
        search.search();
    }

    private boolean checkClassUsedByDir(File dir, String clazzName) throws Exception {
        boolean result = false;
        if (dir.exists() && dir.isDirectory()) {
            File[] subFiles = dir.listFiles();
            if (subFiles != null && subFiles.length > 0) {
                for (File subFile : subFiles) {
                    if (subFile.isDirectory()) {
                        result = checkClassUsedByDir(subFile, clazzName);
                        if (result) {
                            break;
                        }
                    } else if (!subFile.getPath().contains("java.test")
                               && !subFile.getPath().contains("conf.test")
                               && !subFile.getPath().contains(File.separator + "target" + File.separator)
                               && !subFile.getPath().contains(File.separator + "deploy" + File.separator)
                               && (subFile.getPath().endsWith(".java") || subFile.getPath().endsWith(".xml") || subFile.getPath().endsWith(".xml.vm"))) {
                        result = checkClassUsedByFile(subFile, clazzName);
                        if (result) {
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean checkClassUsedByFile(File file, String clazzName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String context = reader.readLine();
        while (context != null) {
            if (context.contains(clazzName)) {
                return true;
            }
            context = reader.readLine();
        }

        return false;
    }

    private void removeUsedClass(File subDirectory) throws Exception {
        if (subDirectory.exists() && subDirectory.isDirectory()) {
            File[] subFiles = subDirectory.listFiles();
            if (subFiles != null && subFiles.length > 0) {
                for (File subFile : subFiles) {
                    if (subFile.isDirectory()) {
                        removeUsedClass(subFile);
                    } else if (!subFile.getPath().contains("java.test")
                               && !subFile.getPath().contains("conf.test")
                               && (subFile.getPath().endsWith(".java") || subFile.getPath().endsWith(".xml") || subFile.getPath().endsWith(".xml.vm"))) {
                        romoveUsedClassByFile(subFile);
                    }
                }

            }

        }
    }

    private void romoveUsedClassByFile(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String context = reader.readLine();
        while (context != null) {
            if (context.contains(basePackage)) {
                classNameList.remove(context.replace("import ", "").replace(";", ""));
            }
            context = reader.readLine();
        }
    }
}
