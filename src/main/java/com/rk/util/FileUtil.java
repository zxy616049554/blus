/**
 * @Copyright ®2018 gjmctp Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金金属商品交易平台
 * 创建日期 : 2018年5月25日
 * 修改历史 : 
 *     1. [2018年5月25日]创建文件 by gjmctp
 */
package com.rk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rk.exception.BaseException;


public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
	public ArrayList<String> arFiles = new ArrayList<String>();

	public static final int BUFFER_SIZE = 1024 * 1024;

	public static boolean isDirectory(File file) {
		if (null == file || !file.exists() || !file.isDirectory()) {
			return false;
		}
		return true;
	}

	public static boolean isFile(File file) {
		if (null == file || !file.exists() || !file.isFile()) {
			return false;
		}
		return true;
	}

	public static String getFileSuffix(String path) {
		File file = new File(path);
		return getFileSuffix(file);
	}

	public static String getFileNameSuffix(String fileName) {
		String suffix = "";
		if ("".equals(fileName) || fileName == null) {
			return suffix;
		}
		int index = fileName.lastIndexOf('.');
		int start = index + 1;
		if (index > 0 && start < fileName.length()) {
			suffix = fileName.substring(start);
		}
		return suffix;
	}

	public static InputStream getFileInByClsPth(String path) throws IOException {
		InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(path);
		return in;
	}

	public static String getFileSuffix(File file) {
		String suffix = "";
		if (isFile(file)) {
			String fileName = file.getName();
			int index = fileName.lastIndexOf('.');
			int start = index + 1;
			if (index > 0 && start < fileName.length()) {
				suffix = fileName.substring(start);
			}
		}
		return suffix;
	}

	/**
	 * 创建目录
	 * 
	 * @param path 创建目录的绝对路径
	 */
	public static void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 删除指定文件夹内的所有文件
	 * 
	 * @param dir 指定要删除的文件夹对象
	 * @return true or false
	 */
	public static boolean deleteDir(File dir) {
		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return false; // 检查参数
		}
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				file.delete(); // 删除所有文件
			} else if (file.isDirectory()) {
				deleteDir(file); // 递规的方式删除文件夹
			}
		}
		dir.delete();// 删除目录本身
		return true;
	}

	public static void createNewFile(String dirPath, String fileName) throws IOException {
		File dir = new File(dirPath);
		File file = new File(dir, fileName);
		if (dir != null) {
			if (!dir.exists()) {
				log.info("目录不存在,创建目录");
				dir.mkdirs();
			}
			if (!file.exists()) {
				log.info("文件不存在，创建文件");
				file.createNewFile();
			} else {
				log.info("文件已存在");
			}

		}
	}
//        public static void main(String[] args) throws IOException, Exception {
//            for(int i=0;i<1;i++) {
//            String timeNo = ""+i+"";
//            String filePath = "d:/test/86162017101600000564/";
//            String zipFilePath = "d:/test/861620171016000005641/";
//            zip(timeNo,filePath,zipFilePath);}
//        }

	/**
	 * 压缩文件,需引入ant-1.7.0.jar包
	 * 
	 * @param timeNo      压缩文件加后的文件名，以导出批次表中的时间序号命名
	 * @param filePath    需要压缩文件夹对象的路径
	 * @param zipFilePath 压缩后压缩文件存放的目录
	 */
	public static void zip(String orderno, String filePath, String zipFilePath) throws IOException, Exception {
		log.info("开始创建压缩文件");
		// 压缩文件总大小
		long size = 0;
		createDir(zipFilePath); // 创建压缩包的存放目录
		log.info("影像文件路径：" + filePath);
		zipFilePath = zipFilePath + "/" + orderno;
		log.info("压缩文件名称及路径为：" + zipFilePath + ".zip");
		File file = new File(filePath);
		File[] files = file.listFiles();
		byte[] buf = new byte[1024];
		CheckedOutputStream ch = new CheckedOutputStream(new FileOutputStream(zipFilePath + ".zip"), new CRC32());
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(ch));
		FileInputStream in = null;
		try {
			for (int i = 0; i < files.length; i++) {
				in = new FileInputStream(files[i]);
				out.putNextEntry(new ZipEntry(files[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.closeEntry();
				size = size + files[i].length();
				log.info("压缩文件创建成功");
			}
			log.info("压缩文件大小" + size);
		} catch (Exception e) {
			log.info(e.getMessage());
			log.error("时间序号为：\" + timeNo + \"的导出文件压缩失败");
			throw new Exception("时间序号为：" + orderno + "的导出文件压缩失败");
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (ch != null) {
				ch.close();
			}
		}
	}

	/**
	 * 
	 * 压缩文件
	 * 
	 * @param orderno     压缩后文件名
	 * @param filePath    要压缩文件的绝对路径
	 * @param zipFilePath 压缩后文件存放路径
	 * @param suffix      压缩包后缀名 .zip \.rar
	 * @throws IOException
	 * @throws Exception
	 */
	public static void compressFile(String orderno, String filePath, String zipFilePath, String suffix)
			throws IOException, Exception {
		log.info("开始创建压缩文件");
		// 压缩文件总大小
		long size = 0;
		createDir(zipFilePath); // 创建压缩包的存放目录
		log.info("影像文件路径：" + filePath);
		zipFilePath = zipFilePath + "/" + orderno;
		log.info("压缩文件名称及路径为：" + zipFilePath + suffix);
		File file = new File(filePath);
		File[] files = file.listFiles();
		byte[] buf = new byte[1024];
		CheckedOutputStream ch = new CheckedOutputStream(new FileOutputStream(zipFilePath + suffix), new CRC32());
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(ch));
		FileInputStream in = null;
		try {
			for (int i = 0; i < files.length; i++) {
				in = new FileInputStream(files[i]);
				out.putNextEntry(new ZipEntry(files[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.closeEntry();
				size = size + files[i].length();
				log.info("压缩文件创建成功");
			}
			log.info("压缩文件大小" + size);
		} catch (Exception e) {
			log.info(e.getMessage());
			log.error("时间序号为：\" + timeNo + \"的导出文件压缩失败");
			throw new Exception("时间序号为：" + orderno + "的导出文件压缩失败");
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (ch != null) {
				ch.close();
			}
		}
	}

	/**
	 * 
	 * 压缩文件
	 * 
	 * @param orderno     压缩后文件名
	 * @param filePath    要压缩文件的绝对路径
	 * @param zipFilePath 压缩后文件存放路径
	 * @param suffix      压缩包后缀名 .zip \.rar
	 * @throws IOException
	 * @throws Exception
	 */
	public static void toZip(String filePath, String zipFilePath, String suffix,
			boolean KeepDirStructure) throws RuntimeException {
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(zipFilePath + suffix));
			File sourceFile = new File(filePath);
			compressFileNew(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 递归压缩方法
	 * 
	 * @param sourceFile       源文件
	 * @param zos              zip输出流
	 * @param name             压缩后的名称
	 * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws Exception
	 * 
	 */
	private static void compressFileNew(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure)
			throws Exception {
		byte[] buf = new byte[BUFFER_SIZE];
		if (sourceFile.isFile()) {
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1) {
				zos.write(buf, 0, len);
			}
			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if (listFiles == null || listFiles.length == 0) {
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if (KeepDirStructure) {
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
					// 没有文件，不需要文件的copy
					zos.closeEntry();
				}
			} else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (KeepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compressFileNew(file, zos, name + "/" + file.getName(), KeepDirStructure);
					} else {
						compressFileNew(file, zos, file.getName(), KeepDirStructure);
					}
				}
			}
		}
	}

	/**
	 * 压缩文件,需引入ant-1.7.0.jar包
	 * 
	 * @param timeNo      压缩文件加后的文件名，以导出批次表中的时间序号命名
	 * @param filePath    需要压缩文件夹对象的路径
	 * @param zipFilePath 压缩后压缩文件存放的目录
	 */
	public static void totzip(String timeNo, String filePath, String zipFilePath) throws IOException, Exception {
		log.info("开始创建压缩文件");
		// 压缩文件总大小
		long size = 0;
		createDir(zipFilePath); // 创建压缩包的存放目录
		log.info("影像文件路径：" + filePath);
		zipFilePath = zipFilePath + timeNo;
		log.info("压缩文件名称及路径为：" + zipFilePath + ".zip");
		File file = new File(filePath);
		File[] files = file.listFiles();
		byte[] buf = new byte[1024];
		CheckedOutputStream ch = new CheckedOutputStream(new FileOutputStream(zipFilePath + ".zip"), new CRC32());
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(ch));
		FileInputStream in = null;
		try {
			for (int i = 0; i < files.length; i++) {
				in = new FileInputStream(files[i]);
				out.putNextEntry(new ZipEntry(files[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.closeEntry();
				size = size + files[i].length();
				log.info("压缩文件创建成功");
			}
			log.info("压缩文件大小" + size);
		} catch (Exception e) {
			log.error("时间序号为：" + timeNo + "的导出文件压缩失败,失败原因" + e);
			throw new Exception("时间序号为：" + timeNo + "的导出文件压缩失败");
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (ch != null) {
				ch.close();
			}
		}
	}

	/**
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {
			// 得到输入流
			inputStream = conn.getInputStream();
			// 获取自己数组
			byte[] getData = readInputStream(inputStream);

			// 文件保存位置
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			fos = new FileOutputStream(file);
			fos.write(getData);
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception ex) {
			throw new BaseException(ex);
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}

		log.info("info:" + url + " download success");

	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	@SuppressWarnings("rawtypes")
	public static void uuZipFile(String fileAddress, String unZipAddress) throws IOException {

		File file = new File(fileAddress);
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file, "GBK");// 设置编码格式
		} catch (IOException ex) {
			log.error("系统异常，异常信息为：{}]", ex.getMessage());
			log.error("解压文件不存在!");
		}
		Enumeration e = zipFile.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) e.nextElement();
			if (zipEntry.isDirectory()) {
				String name = zipEntry.getName();
				name = name.substring(0, name.length() - 1);
				File f = new File(unZipAddress + name);
				f.mkdirs();
			} else {
				File f = new File(unZipAddress + zipEntry.getName());
				f.getParentFile().mkdirs();
				f.createNewFile();

				InputStream is = null;
				FileOutputStream fos = null;
				try {
					is = zipFile.getInputStream(zipEntry);
					fos = new FileOutputStream(f);
					int length = 0;
					byte[] b = new byte[1024];
					while ((length = is.read(b, 0, 1024)) != -1) {
						fos.write(b, 0, length);
					}
					is.close();
					fos.close();
				} catch (Exception ex) {
					throw new BaseException(ex);
				} finally {
					if (fos != null) {
						fos.close();
					}
					if (is != null) {
						is.close();
					}
				}
			}
		}
		if (zipFile != null) {
			zipFile.close();
		}
		file.delete();// 解压完以后将压缩包删除
	}

	/**
	 * 将压缩包解压到目录下
	 * 
	 * @param filePath
	 * @param UNZIPPATH
	 */
	public static void unzip(String filePath, String UNZIPPATH) {
		long startTime = System.currentTimeMillis();
		String path = filePath;
		String unzipPATH = UNZIPPATH;
		try {
			ZipInputStream Zin = new ZipInputStream(new FileInputStream(path));// 输入源zip路径
			BufferedInputStream Bin = new BufferedInputStream(Zin);
			String Parent = unzipPATH; // 输出路径（文件夹目录）
			File Fout = null;
			java.util.zip.ZipEntry entry;
			try {
				while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
					Fout = new File(Parent, entry.getName());
					if (!Fout.exists()) {
						(new File(Fout.getParent())).mkdirs();
					}

					FileOutputStream out = null;
					BufferedOutputStream Bout = null;
					try {
						out = new FileOutputStream(Fout);
						Bout = new BufferedOutputStream(out);
						int b;
						while ((b = Bin.read()) != -1) {
							Bout.write(b);
						}
						Bout.close();
						out.close();
					} catch (Exception ex) {
						throw new BaseException(ex);
					} finally {
						if (Bout != null) {
							Bout.close();
						}
						if (out != null) {
							out.close();
						}
					}
					log.info("\t 文件" + Fout + "解压成功");
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				log.error("系统异常，异常信息为：{}]", e.getMessage());
				throw new BaseException(e);
			} finally {
				try {
					if (Bin != null) {
						Bin.close();
					}
					if (Zin != null) {
						Zin.close();
					}
				} catch (IOException e) {
					log.error("系统异常，异常信息为：{}]", e.getMessage());
					throw new BaseException(e);
				}

			}
		} catch (FileNotFoundException e) {
			log.error("系统异常，异常信息为：{}]", e.getMessage());
			throw new BaseException(e);
		}
		long endTime = System.currentTimeMillis();
		log.info("解压缩结束，耗费时间： " + (endTime - startTime) + " ms");
	}

	public void getFiles(String string) {
		File file = new File(string);

		if (file.isDirectory()) {
			log.info(file.getPath());

			File[] files = file.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					getFiles(files[i].getPath());
				} else {
					log.info(files[i].getPath());
					arFiles.add(files[i].getPath());
				}
			}
		} else {
			log.info(file.getPath());
			arFiles.add(string);
		}
	}

	/**
	 * 解析电子保单报文 方法功能描述
	 * 
	 * @param path
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<Integer, String> analysisXml(String path) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		// Date time = null;
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		try {
			File f = new File(path);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			Element foo1;
			for (Iterator i = root.elementIterator("PAGES"); i.hasNext();) {
				foo = (Element) i.next();
				int key = 0;
				for (Iterator j = foo.elementIterator("PAGE"); j.hasNext();) {
					foo1 = (Element) j.next();
					List<Attribute> listAttr = foo1.attributes();
					String pageUrl = "";
					// String upTime = "";
					for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
						String name = attr.getName();// 属性名称
						if ("PAGE_URL".equals(name)) {
							pageUrl = attr.getValue();
							if (StringUtil.isNotEmpty(pageUrl)) {
								map.put(key, pageUrl);
								key++;
							}
						}

						// else if("UP_TIME".equals(name)){
						// upTime = attr.getValue();
						// if(time==null){
						// time = simpleDateFormat.parse(upTime);
						// }else {
						// if(time.getTime()<simpleDateFormat.parse(upTime).getTime()){
						// time = simpleDateFormat.parse(upTime);
						// }
						// }
						// }
						// if(!StringUtil.isEmpty(pageUrl)&&!StringUtil.isEmpty(upTime)) {
						// map.put(upTime,pageUrl);
						// }
					}
				}
			}
		} catch (Exception e) {
			log.error("系统异常，异常信息为：{}]", e.getMessage());
			throw new BaseException(e);
		}
		// for (String value:map.keySet()){
		// log.info("key: "+ value + " value: "+ map.get(value));
		// }
		// log.info(time);
		// String pdfPath = map.get(simpleDateFormat.format(time));
		return map;
	}

	/**
	 * 
	 * 文件转移
	 * 
	 * @param oldFile 原文件目录
	 * @param newFile 新文件目录
	 */
	public static void copyFile(File oldFile, File newFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fis = new FileInputStream(oldFile);
			fos = new FileOutputStream(newFile);
			in = fis.getChannel();// 得到对应的文件通道
			out = fos.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通
		} catch (IOException e) {
			log.error("系统异常，异常信息为：{}]", e.getMessage());
			throw new BaseException(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (in != null) {
					in.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				log.error("系统异常，异常信息为：{}]", e.getMessage());
				throw new BaseException(e);
			}
		}
	}

	/**
	 * 
	 * 递归删除文件夹
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				file.delete();// 删除文件
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath String 原文件路径 如：c:/fqf
	 * @param newPath String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {

					FileInputStream input = null;
					FileOutputStream output = null;
					try {
						input = new FileInputStream(temp);
						output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
						byte[] b = new byte[1024 * 5];
						int len;
						while ((len = input.read(b)) != -1) {
							output.write(b, 0, len);
						}
						output.flush();
						output.close();
						input.close();
					} catch (Exception ex) {
						throw new BaseException(ex);
					} finally {
						if (output != null) {
							output.close();
						}
						if (input != null) {
							input.close();
						}
					}
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			log.error("系统异常，异常信息为：{}]", e.getMessage());
			throw new BaseException(e);

		}

	}

	/**
	 * 文件返回字节流
	 * 
	 * @param fileName（url路径+文件名）
	 * @return 有问题或没有文件为null；其余为byte
	 */
	public static byte[] getByteArray(String fileName) {
		log.info("文件返回字节流开始");
		File file = new File(fileName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				baos = new ByteArrayOutputStream(fis.available());
				byte[] bytes = new byte[fis.available()];
				int temp;
				while ((temp = fis.read(bytes)) != -1) {
					baos.write(bytes, 0, temp);
				}
				log.info("文件返回字节流结束");
				fis.close();
				baos.close();
				return baos.toByteArray();
			} catch (Exception e) {
				log.error("[FileUtil.getByteArray]-[异常信息：{}]", e);
				return null;
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						log.error("[FileUtil.getByteArray]-[FileInputStream关闭异常信息：{}]", e);
					}
				}
				if (baos != null) {
					try {
						baos.close();
					} catch (IOException e) {
						log.error("[FileUtil.getByteArray]-[ByteArrayOutputStream关闭异常信息：{}]", e);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * byte[] bfile => File
	 * 
	 * @param bfile
	 * @param filePath
	 * @param fileName
	 */
	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
//            bos.flush();
		} catch (Exception e) {
			log.error("[FileUtil.getFile-异常信息:{}]", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					log.error("系统异常，异常信息为：{}]", e1.getMessage());
					throw new BaseException(e1);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					log.error("系统异常，异常信息为：{}]", e1.getMessage());
					throw new BaseException(e1);
				}
			}
		}
	}

	/**
	 * 
	 * 删除指定文件，或指定文件夹中所有文件
	 * 
	 * @param path
	 */
	public static void deleteDirectory(File file) {
		if (file.isFile()) {// 表示该文件不是文件夹
			file.delete();
		} else {
			// 首先得到当前的路径
			String[] childFilePaths = file.list();
			for (String childFilePath : childFilePaths) {
				File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
				deleteDirectory(childFile);
			}
			file.delete();
		}
	}

	/**
	 * 根据路径删除指定文件或文件夹
	 * 
	 * @param path
	 */
	public static void deleteDirectory(String path) {
		log.info("[ConvertUtil.deleteDirectory]-[]删除文件路径为：{}", path);
		if (StringUtil.isNotEmpty(path)) {
			File file = new File(path);
			deleteDirectory(file);
		}
	}

	public static void main(String[] args) {
		try {
			compressFile("hello", "E:\\var\\gjmctp\\gjmctp-usercenter-service\\logs",
					"E:\\var\\gjmctp\\gjmctp-usercenter-service\\", ".rar");
		} catch (Exception e) {
			log.error("系统异常，异常信息为：{}]", e.getMessage());
			throw new BaseException(e);
		}
		getFile(getByteArray("E:\\var\\gjmctp\\gjmctp-usercenter-service\\hello.rar"),
				"E:\\var\\gjmctp\\gjmctp-usercenter-service\\logs", "newFile.rar");
	}
}
