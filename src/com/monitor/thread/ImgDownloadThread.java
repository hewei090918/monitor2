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
        int count = 200;
		List<String> urlList = Spider.packContent(count);
		for(String imgUrl: urlList) {
			String fileName = imgUrl.split("/")[3];
			//下载本地图片
			String dist = savePath + "/" + fileName;
			Spider.localImgDownload(imgUrl, dist);
			//下载网络图片
		}
		long end = System.currentTimeMillis();
		System.out.println("下载[" + count + "]张图片，共耗时：" + (float)(end - start) / 1000 + " s");
	}

}
