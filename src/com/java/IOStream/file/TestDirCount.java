package com.java.IOStream.file;

import java.io.File;

/**
 * 测试文件夹的数量和大小
 */
public class TestDirCount {

    // 文件路径
    private String path;

    // 文件对象
    private File file;

    // 文件个数
    private int fileSize;

    // 文件夹个数
    private int dirSize;

    // 文件夹大小
    private long len;

    public TestDirCount(String path) {
        this.path = path;
        this.file = new File(path);
        count(this.file);
    }

    private void count(File file) {

        if (null != file && file.exists()) {
            if (file.isFile()) {
                len += file.length();
                this.fileSize++;
            } else {
                dirSize++;
                for (File f: file.listFiles()) {
                    count(f);
                }
            }
        }
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public int getFileSize() {
        return fileSize;
    }

    public long getLen() {
        return len;
    }

    public static void main(String[] args) {
        String path = "F:\\ideaWorkspace\\java300\\src\\com\\java";
        TestDirCount testDirCount = new TestDirCount(path);
        System.out.println(testDirCount.len + "-->" + testDirCount.fileSize + "-->" + testDirCount.dirSize);
    }
}
