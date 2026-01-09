package com.llj.usercenter.utils.qrcode;

public class VideoConverter {
    public static void convertVideo(String inputPath, String outputPath) {
        try {
            // 构建FFmpeg命令
            String command = "ffmpeg -i " + inputPath + " -c:v libx264 -c:a aac " + outputPath;
            // 执行命令
            Process process = Runtime.getRuntime().exec(command);
            // 等待命令执行完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("视频转换成功！");
            } else {
                System.out.println("视频转换失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputPath = "path/to/your/input/video.avi"; // 输入文件路径
        String outputPath = "path/to/your/output/video.mp4"; // 输出文件路径
        convertVideo(inputPath, outputPath);
    }
}
