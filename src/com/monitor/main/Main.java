package com.monitor.main;

import com.monitor.thread.ImgDownloadThread;

public class Main {

	public static void main(String[] args) {

		ImgDownloadThread r = new ImgDownloadThread();
		Thread t = new Thread(r);
		t.start();
		
	}

}
