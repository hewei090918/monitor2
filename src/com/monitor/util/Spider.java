package com.monitor.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.monitor.jdbc.JdbcBaseDao;

/**
 * 
 * 包名：com.mop.util <br/>
 * 类名：Spider.java <br/>
 * 版本：version 1.0 <br/>
 * 作者：HeWei <br/>
 * 描述：图片下载工具类
 */
public class Spider {
	/**
	 * 
	 * 描述：	连接数据库并提取数据库里的图片地址 <br/>
	 * 作者：	HeWei
	 * @param content
	 * @param count
	 * @return 
	 * List<String> 
	 * <br/>
	 */
	public static List<String> packContent(int count) {
		List<String> urlList = new ArrayList<String>();
		//获取数据库连接
		Connection conn = (Connection) JdbcBaseDao.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from source";
//			String sql = "select * from T_WAIT_MATCH";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(count > 0) {
					String url = rs.getString(2);
					System.out.println(url);
					urlList.add(url);
				}
				count--;
			}
	        
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return urlList;
	}
	/**
	 * 
	 * 描述：	下载图片地址以http:或ftp:开头的图片 <br/>
	 * 作者：	HeWei
	 * @param imgUrl
	 * @param dist 
	 * void 
	 * <br/>
	 */
    public static void netImgDownload(String imgUrl, String dist) {
        try {
        	URL url = new URL(imgUrl);
        	URLConnection conn = url.openConnection();
        	
			InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            
            File f = new File(dist);
            OutputStream os = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            
            byte[] bt = new byte[1024];
            int len = -1;
            while ((len = bis.read(bt)) != -1) {
                bos.write(bt, 0, len);
                bos.flush();
            }
            bos.close();
            os.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    /**
     * 
     * 描述：	下载本地图片（类似于拷贝） <br/>
     * 作者：	HeWei
     * @param imgUrl
     * @param dist 
     * void 
     * <br/>
     */
    public static void localImgDownload(String imgUrl, String dist) {
    	File file = new File(imgUrl);
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			FileOutputStream fos = new FileOutputStream(new File(dist));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			byte[] bt = new byte[1024];
			int len = -1;
			while((len = bis.read(bt)) != -1) {
				bos.write(bt, 0, len);
				bos.flush();
			}
			bos.close();
			fos.close();
			bis.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
