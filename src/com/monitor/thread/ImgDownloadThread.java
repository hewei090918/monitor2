package com.monitor.thread;

import java.io.File;
import java.util.List;

import com.monitor.util.Spider;

public class ImgDownloadThread implements Runnable {
	
	static final String savePath = "D:/imgDownload";

	@Override
	public void run() {
		File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        long start = System.currentTimeMillis();
        
        int count = 200;//一共下载n张图片
        for(int index = 0; index < count; index++) {
        	//获取表里第n张图片的url
        	List<String> urlList = Spider.packContent(index);
        	if(urlList != null && urlList.size() == 1) {
        		String imgUrl = urlList.get(0);
        		String[] ss = imgUrl.split("/");
    			String fileName = ss[ss.length-1];
    			String dist = savePath + "/" + fileName;
    			//下载本地图片
    			Spider.localImgDownload(imgUrl, dist);
    			//下载网络图片
//    			Spider.netImgDownload(imgUrl, dist);
        	}
        }
		long end = System.currentTimeMillis();
		System.out.println("下载[" + count + "]张图片，共耗时：" + (float)(end - start) / 1000 + " s");
	}

}
