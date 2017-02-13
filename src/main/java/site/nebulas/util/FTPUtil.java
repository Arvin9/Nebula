package site.nebulas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	private  FTPClient ftp;
	/**  
     *   
     * @param path 上传到ftp服务器哪个路径下     
     * @param addr 地址  
     * @param port 端口号  
     * @param username 用户名  
     * @param password 密码  
     * @return  
     * @throws Exception  
     */
	private String path = "/var/www/html/file";
	private String addr = "115.29.52.104";
	private int port = 21;
	private String username = "file";
	private String password = "314159";
	
	private  boolean uploadFile(File file){      
        boolean result = false;      
        ftp = new FTPClient();
        int reply;      
        try {
			ftp.connect(addr,port);
			
			ftp.enterLocalPassiveMode();
	        ftp.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
	        ftp.setControlEncoding("UTF-8");
			
	        ftp.login(username,password);
	        
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        reply = ftp.getReplyCode();
	        System.out.println("reply:" + reply);
	        if (!FTPReply.isPositiveCompletion(reply)) {      
	            ftp.disconnect();      
	            return result;      
	        }      
	        ftp.changeWorkingDirectory(path);
	        System.out.println("上传目录：" + path);
	        result = true;
	        FileInputStream input = new FileInputStream(file);      
			ftp.storeFile(file.getName(), input);      
			input.close();
			System.out.println("退出登录:" + ftp.logout());
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    }      
        return result;      
    }
	 
	 public static void main(String[] args) throws Exception{    
		 FTPUtil t = new FTPUtil();    
		 //t.connect("/home/file", "115.29.52.104", 21, "file", "314159");    
		 File file = new File("C:/Users/Administrator/Desktop/Pager.zip");
//		 File file2 = new File("D:/Pager.java");
//		 FileOutputStream fs = new FileOutputStream(file2);
//		 FileInputStream input = new FileInputStream(file);
//		 int in = input.read();
//		 while(in!=-1){
//			 fs.write(in);
//			 in = input.read();
//		 }
		 t.uploadFile(file);    
	 }  
}
