package com.ronrytest.filereplace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileInster {

    public void insert(File inFile, int lineno, String[] lineToBeInserted) throws Exception {
        File outFile = new File(inFile.getPath() + ".tmp");

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));

        PrintWriter out = new PrintWriter(new FileOutputStream(outFile));

        String thisLine;
        int i = 1;
        while ((thisLine = in.readLine()) != null) {
            if (i == lineno) {
                for (String line : lineToBeInserted) {
                    out.println(line);
                }
            }
            out.println(thisLine);
            i++;
        }
        out.flush();
        out.close();
        in.close();

        inFile.delete();
        outFile.renameTo(inFile);
    }
}
