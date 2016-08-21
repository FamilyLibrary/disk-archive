package com.alextim.bookshelf.datauploader.jobs;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by admin on 21.08.2016.
 */
public class BookDataUploaderJob {
   public void doIt(){
      System.out.println("Run it!");
   }
}
