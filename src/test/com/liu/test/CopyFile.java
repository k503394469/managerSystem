package com.liu.test;

import java.io.*;

public class CopyFile {
    public static void main(String[] args)throws Exception {
        File src=new File("C:\\Users\\Mr.k\\Desktop\\ttt1");
        File dest=new File("F:\\");
        copyFolder(src,dest);
    }
    public static void copyFolder(File srcFile, File destFile) throws Exception{
        if (srcFile.isDirectory()){
            File newFolder=new File(destFile,srcFile.getName());
            if (!newFolder.exists()){
                newFolder.mkdir();
            }
            File[] srcFiles = srcFile.listFiles();
            for (File f:srcFiles){
                copyFolder(f,newFolder);
            }
        }else {
            File newFile=new File(destFile,srcFile.getName());
            copyFile(srcFile,newFile);
        }
    }
    public static void copyFile(File srcFile, File destFile)throws Exception{
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));
        byte[] bys=new byte[4096];
        int length;
        while ((length=bis.read(bys))!=-1){
            bos.write(bys,0,length);
        }
        bos.close();
        bis.close();
    }
}
