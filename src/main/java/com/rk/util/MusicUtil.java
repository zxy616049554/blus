package com.rk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.lf5.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.baidu.aip.speech.AipSpeech;
import com.rk.dto.ChatRoomMembersDto;
import com.rk.entity.Music;
import com.rk.entity.MusicMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import net.sf.json.JSONObject;

public class MusicUtil {
	
	static final Logger logger = LoggerFactory.getLogger(MusicUtil.class);
	
	public static String musicMessageToXml(MusicMessage musicMessage ) {
		XStream xStream =  new XStream(new StaxDriver());
		xStream.alias("xml", musicMessage.getClass());
	    return xStream.toXML(musicMessage);
	}

	public static String initMusicMessage(String toUserName,String fromUserName) {
		String message = "";
        String access_token = null;
        String path = "E:/timg.jpg";
        try{
            Music music = new Music();
            //String mediaId = WeixinUtil.upload(path, access_token, "thumb");
            music.setTitle("好听的Music");
            music.setDescription("感觉非常哇塞的歌曲了。。");
            music.setMusicUrl("https://y.music.163.com/m/song?id=16343632");
            music.setHQMusicUrl("https://y.music.163.com/m/song?id=16343632");
            music.setThumbMediaId(null);
            MusicMessage musicMessage = new MusicMessage();
            musicMessage.setToUserName(fromUserName);
            musicMessage.setFromUserName(toUserName);
            musicMessage.setMsgType("music");
            musicMessage.setCreateTime(DateUtil.getCurrentDate());
            musicMessage.setMusic(music);
            message = musicMessageToXml(musicMessage);
            System.err.println(message);
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
	}
	

	/**
	    * 音频文件频率8k转16k。必须要转，因为不转百度识别不出来，错误信息是音质太差
	 * @param sourceFile
	 * @return
	 */
	public static String cover(File file) {
		String targetPath = null;
	    try {
	        File ffmpegPath = new File("C:\\ffmpeg\\ffmpeg-3.2.4-win64-static\\ffmpeg-3.2.4-win64-static\\bin\\ffmpeg.exe"); //存放ffmpeg程序的目录
	        targetPath = file.getAbsolutePath().replaceAll(".wav" , "_16x.wav");
	        // ffmpeg.exe -i source.wav -ar 16000 target.wav
	        List<String> wavToPcm = new ArrayList<String>();
	        wavToPcm.add(ffmpegPath.getAbsolutePath());
	        wavToPcm.add("-i");
	        wavToPcm.add(file.getAbsolutePath());
	        wavToPcm.add("-ar");
	        wavToPcm.add("16000");
	        wavToPcm.add(targetPath);
	        ProcessBuilder builder = new ProcessBuilder();
	        builder.command(wavToPcm);
	        builder.redirectErrorStream(true);
	        Process process = builder.start();
            try {
	              File fileMkdir=new File("C:\\Users\\Administrator\\Desktop\\music\\");
	              if(!fileMkdir.exists()) {
	                  fileMkdir.mkdir();
	              }
	              //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
	              FileOutputStream os = new FileOutputStream(targetPath);
	              os.close();
	          } catch (Exception e) {
	        	  logger.error("上传失败！"+e.getMessage());
	        	  e.printStackTrace();
	              return null;
	          }
            if (StringUtil.isNotEmpty(targetPath)) {
		        return targetPath;
		    }
	        process.waitFor();	 
	    } catch (Exception e) {
	        logger.error("录音文件8k转化16k失败"+e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	   
	    logger.error("传入的文件路径有误");
		return null;
	}   
    /**
               * 转换mp3
     * @param file
     * @return
     */
	public static String coverMp(File file) {
		String targetPath = null;
		String n=null;
	    try {
	        File ffmpegPath = new File("C:\\ffmpeg\\ffmpeg-3.2.4-win64-static\\ffmpeg-3.2.4-win64-static\\bin\\ffmpeg.exe"); //存放ffmpeg程序的目录
	        targetPath = file.getAbsolutePath().replaceAll(".pcm" , ".mp3");
	        n =targetPath.replaceAll(".mp3", ".wav");
	        // ffmpeg.exe -i source.wav -ar 16000 target.wav
	        List<String> wavToPcm = new ArrayList<String>();
	        wavToPcm.add(ffmpegPath.getAbsolutePath());
	        wavToPcm.add("-y");  
	        wavToPcm.add("-f");  
	        wavToPcm.add("s16le");  
	        wavToPcm.add("-ar");  
	        wavToPcm.add("24000");  
	        wavToPcm.add("-ac");  
	        wavToPcm.add("1");  
	        wavToPcm.add("-i");  
	        wavToPcm.add(file.getAbsolutePath());
	        wavToPcm.add(n);
	        ProcessBuilder builder = new ProcessBuilder();
	        builder.command(wavToPcm);
	        builder.redirectErrorStream(true);
	        try {
	              File fileMkdir=new File("C:\\Users\\Administrator\\Desktop\\music\\");
	              if(!fileMkdir.exists()) {
	                  fileMkdir.mkdir();
	              }
	              //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
	              FileOutputStream os = new FileOutputStream(n);
	              os.close();
	          } catch (Exception e) {
	        	  logger.error("上传失败！"+e.getMessage());
	        	  e.printStackTrace();
	              return null;
	          }
	        Process process = builder.start();
	        if (StringUtil.isNotEmpty(n)) {
		        return n;
		    }
	        process.waitFor();
	    } catch (Exception e) {
	        logger.error("pcm转换MP3失败"+e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	    
	    logger.error("传入的文件路径有误");
		return null;
	}
	
	// 调用sile_v3_decoder.exe，转成pcm格式
	public static String processPcm(File file){
		String targetPath = null;
	    try {
	        File ffmpegPath = new File("C:\\ffmpeg\\silk_v3_decoder.exe"); //存放ffmpeg程序的目录
	        targetPath = file.getAbsolutePath().replaceAll(".silk" , ".pcm");
	        // ffmpeg.exe -i source.wav -ar 16000 target.wav
	        List<String> wavToPcm = new ArrayList<String>();
	        wavToPcm.add(ffmpegPath.getAbsolutePath());
	        wavToPcm.add(file.getAbsolutePath());
	        wavToPcm.add(targetPath);
	        ProcessBuilder builder = new ProcessBuilder();
	        builder.command(wavToPcm);
	        builder.redirectErrorStream(true);
	          try {
	              File fileMkdir=new File("C:\\Users\\Administrator\\Desktop\\music\\");
	              if(!fileMkdir.exists()) {
	                  fileMkdir.mkdir();
	              }
	              //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
	              FileOutputStream os = new FileOutputStream(targetPath);
	              os.close();
	          } catch (Exception e) {
	        	  logger.error("上传失败！"+e.getMessage());
	        	  e.printStackTrace();
	              return null;
	          }
	        Process process = builder.start();
	        if (StringUtil.isNotEmpty(targetPath)) {
		        return targetPath;
		    }
	        process.waitFor();
	    } catch (Exception e) {
	        logger.error("silk转换pcm失败"+e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	    
	    System.out.println(targetPath);
	    logger.error("传入的文件路径有误");
		return null;
    }
}
