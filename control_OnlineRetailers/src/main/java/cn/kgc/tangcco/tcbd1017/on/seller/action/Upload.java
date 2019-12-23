package cn.kgc.tangcco.tcbd1017.on.seller.action;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author 胡彬
 * @version 1.0
 * @date 2019/10/24 11:36
 */
@WebServlet("/upload.action")
public class Upload extends BaseServlet{

    private static final long serialVersionUID = 1L;
    
    /**
     *  上传图片或者视频到本地；
     */
    protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得一个文件上传的工厂类
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        //DiskFileItemFactory 是创建 FileItem 对象的工厂
        //设置文件上传临时存储的大小8M
        dfif.setSizeThreshold(1024*1024*8);
        //指定临时文件目录，默认值为当前用户的系统临时文件目录，可通过System.getProperty(“java.io.tmpdir”)打印查看；
        dfif.setRepository(new File("imgv")); //胡彬的注解==》放存储视频的本地文件夹名字；
        System.out.println(System.getProperty("imgv"));
        //使用servlet来处理文件上传
        ServletFileUpload upload = new ServletFileUpload(dfif);
        //设置文件上传的大小
        upload.setSizeMax(1024*1024*50);
        //获得当前服务器的路径
        String uploadPath = this.getServletContext().getRealPath("E");  //胡彬注解： 放存储视频的本地服务器的盘符；
        System.out.println("当前服务器路径---->"+uploadPath);
        uploadPath = uploadPath+"..\\"+"upload";  //胡彬的注解： 就是本地存储的路径；E:\imgv
        
        
        //胡彬的注解： uploadPath 是文件名前面的路径；
        
        
        
        File uploadDir = new File(uploadPath);
        String filePath=null;
        String msg = null;
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        try {
            //解析所有上传的文件数据
            List<FileItem> formItem = upload.parseRequest(request);
            if(formItem != null && formItem.size() >0){
                //循环遍历要上传的文件
                for(FileItem item : formItem){
                    if(!item.isFormField()){
                        //获得要上传的文件的名字
                        String fileName = new File(item.getName()).getName();
                        //这个是可以随机修改本地文件名字的
                        //fileName = new Date().getTime()+(int)(Math.random()*100)+fileName;

                        //目的文件
                         filePath = uploadPath+"\\"+fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);

                        System.out.println(fileName);
                        System.out.println(filePath);
                        //request.setAttribute("fileName", fileName);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
           msg = "失败";
        } catch (Exception e) {
            e.printStackTrace();
        }
        //request.getRequestDispatcher("upload.jsp").forward(request, response);
        msg = filePath;
        PrintWriter writer = response.getWriter();
        writer.println(JSON.toJSONString(msg));
        writer.close();
        
        
      //胡彬注解===》加上service层的对象方法，把 msg 传到dao层,进行添加；
    }
    
    
    /**
     *   上传图片视频到7牛云；
     */
    protected void upload02(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得一个文件上传的工厂类
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        //DiskFileItemFactory 是创建 FileItem 对象的工厂
        //设置文件上传临时存储的大小8M
        dfif.setSizeThreshold(1024*1024*8);
        //指定临时文件目录，默认值为当前用户的系统临时文件目录，可通过System.getProperty(“java.io.tmpdir”)打印查看；
        dfif.setRepository(new File("hubindekj/imgv")); //hubin七牛云上的空间名字： hubindekj 里面创建一个文件夹imgv;
        System.out.println(System.getProperty("imgv"));
        //使用servlet来处理文件上传
        ServletFileUpload upload = new ServletFileUpload(dfif);
        //设置文件上传的大小
        upload.setSizeMax(1024*1024*50);
        //获得当前服务器的路径
        String uploadPath = this.getServletContext().getRealPath("http://q2onhzijy.bkt.clouddn.com/"); //胡彬的注解=》服务器的地址；这个是我注册七牛云的服务器 地址；
        System.out.println("当前服务器路径---->"+uploadPath);
        uploadPath = uploadPath+"..\\"+"upload";
        
        //胡彬的注解： uploadPath 是文件名前面的路径；
        
        File uploadDir = new File(uploadPath);
        String filePath=null;
        String msg = null;
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        try {
            //解析所有上传的文件数据
            List<FileItem> formItem = upload.parseRequest(request);
            if(formItem != null && formItem.size() >0){
                //循环遍历要上传的文件
                for(FileItem item : formItem){
                    if(!item.isFormField()){
                        //获得要上传的文件的名字
                        String fileName = new File(item.getName()).getName();
                        //这个是可以随机修改本地文件名字的
                        fileName = System.currentTimeMillis()+(int)(Math.random()*100)+fileName;
                        //Ftp(String host, int port, String user, String password)
                        // ftp://47.94.21.191:21
                        //账号mazepeng
                        //密码
                        
                        //http://q2opyz2oh.bkt.clouddn.com/binbinjianshen%E6%95%B0%E6%8D%AE%E5%BA%93%E7%89%A9%E7%90%86%E6%A8%A1%E5%9E%8B%E5%9B%BE1.png
                        //Ftp ftp = new Ftp("http://q2onhzijy.bkt.clouddn.com/", 21, "mazepeng", ""); //ip, 端口，账号，密码；
                        Ftp ftp = new Ftp("http://q2onhzijy.bkt.clouddn.com/", 21, "2550481565@qq.com", "jj13839717011"); 
                        
                        String pwd = ftp.pwd();
                        ftp.setMode(FtpMode.Passive);
                        System.out.println(pwd);

                        boolean upload1 = ftp.upload(null, fileName,item.getInputStream());
                        ftp.close();

                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            msg = "失败";
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = filePath;
        PrintWriter writer = response.getWriter();
        writer.println(JSON.toJSONString(msg));
        writer.close();
      //胡彬注解===》加上service层的对象方法，把 msg 传到dao层,进行添加；
        
        
    }

}
