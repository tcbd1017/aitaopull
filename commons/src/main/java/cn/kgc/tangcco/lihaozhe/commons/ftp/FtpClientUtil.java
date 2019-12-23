package cn.kgc.tangcco.lihaozhe.commons.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author	作者 : 李昊哲
 * @version	创建时间：2019年3月20日 下午9:23:29
  *  类说明
 */
public class FtpClientUtil {
	//使用指定类初始化日志对象
	private static Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);
	//初始化ftp
    private FTPClient ftpClient;
    public boolean connectServer(String Servcer, String username, String password){
        boolean  res = false;
        try {
        	//创建ftp
            ftpClient = new FTPClient();
            //连接ftp
            ftpClient.connect(Servcer);
            //登陆
            boolean login = ftpClient.login(username, password);
            if(!login){
                logger.error("FtpClientUtils -> connectServer    ftp login failed! ftpConfig[{},{},{}]", new Object[]{ Servcer, username, password});
                return res;
            }
            logger.info("FtpClientUtils conneted {} server ." ,  username);
            //获取ftp返回码 220 成功
            int replyCode = ftpClient.getReplyCode();
            //如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限下面有详细的解释。
            if(!FTPReply.isPositiveCompletion(replyCode)){
            	//关闭连接
                ftpClient.disconnect();
                logger.error("FTP server refused connection.");
                return res;
            }
            
            //设置缓冲大小
            ftpClient.setBufferSize(2048);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            res =  true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if(ftpClient != null && ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            logger.error("###FtpClientUtils -> connectServer failed!!!!");
        }
        return res;
        
    }
    
    /**
     * 断开
     */
    public void disconnectServer(){
    	//如果ftp不为null 并且 连接是true
        if(ftpClient != null && ftpClient.isConnected()){
            try {
                ftpClient.disconnect();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        
        logger.debug("disconnectServer success!");
    }
    
    
    public boolean uploadFile(String fileName, InputStream is){
        boolean  res = false;
        if(ftpClient != null && ftpClient.isConnected()){
            try {
                //如果名称含有文件夹刚先创建文件夹
                if(fileName.indexOf("/") > -1){

                    String parentPath = fileName.substring(0, fileName.lastIndexOf("/"));
                    String[] filePaths = parentPath.split("/");
                    String dirPath = "";
                    //出现多层目录时需要一层层建目录
                    for(String dirName : filePaths){
                        if(!dirName.equals("") ){
                            dirPath = dirPath + "/" + dirName;
                            //创建目录
                            ftpClient.makeDirectory(dirPath);
                        }
                    }
                    logger.debug("FtpClientUtils->uploadFile  [{}] makedir success!->" );
                
                }
                //创建文件
                res = ftpClient.storeFile(fileName, is);
                //关闭流
                is.close();
                if(res){
                    logger.info("uploadFile[{}] success! ", fileName);
                }else{
                    logger.error("uploafile[{}] failed! ", fileName);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.error("FtpClientUtils->uploadFile failed!!!");
            }
            
        }
        return res;
    }
}
