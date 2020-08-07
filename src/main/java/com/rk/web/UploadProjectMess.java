package com.rk.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.rk.util.MusicUtil;


public class UploadProjectMess {
	
	  public static String upload(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
	        /* 定义文件的存储路径,如下，是在linux和mac上定义的文件路径
	        /private/var/folders/8x/4zvnbqmj1w33cqmzrpygzbth0000gn/T/tomcat-docbase.5206733816001100271.8080/uploadFile
	         */
	    	File directory = new File("");// 参数为空
	        String workspacePath = directory.getCanonicalPath(); //获取工作空间的绝对路径
	        String uploadDicPath = "\\src\\main\\webapp\\WEB-INF\\uploadProject\\"; //手动添加上传文件夹的路径
	        String uploadPath = workspacePath + uploadDicPath; //最终图片上传的路径
	        File dir = new File(uploadPath);
	        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
	            dir.mkdirs();
	        }
	 
	        try {
	            String filename = uploadFile.getOriginalFilename();
	            //服务端保存的文件对象
	            File fileServer = new File(dir, filename);
	            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
	            //2，实现上传
	            uploadFile.transferTo(fileServer);
	            String filePaths=uploadPath+filename;
	            //3，返回可供访问的网络路径
	            return filePaths;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "上传失败";
	    }

    public static String downloadFile(String src_file, String dest_file) throws Throwable {
        String[] sourceStrArray=src_file.split("/");
        String fileName = sourceStrArray[5];
        int downloadTimeout=200;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(src_file);
            httpget.setConfig(RequestConfig.custom() //
                    .setConnectionRequestTimeout(downloadTimeout) //
                    .setConnectTimeout(downloadTimeout) //
                    .setSocketTimeout(downloadTimeout) //
                    .build());
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                org.apache.http.HttpEntity entity = response.getEntity();
                File desc = new File(dest_file+File.separator+fileName);
                File folder = desc.getParentFile();
                folder.mkdirs();
                try (InputStream is = entity.getContent(); //
                        OutputStream os = new FileOutputStream(desc)) {
                    StreamUtils.copy(is, os);
                }
            }catch(Throwable e){
                throw new Throwable("文件下载失败......", e);
            }
        }
        return dest_file+File.separator+fileName;
    }
}

