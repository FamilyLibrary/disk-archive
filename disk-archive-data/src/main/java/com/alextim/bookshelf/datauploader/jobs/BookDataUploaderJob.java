package com.alextim.bookshelf.datauploader.jobs;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;
import com.alextim.bookshelf.service.ISettingService;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamSource;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by admin on 21.08.2016.
 */
public class BookDataUploaderJob {
   final static Logger LOG = Logger.getLogger(BookDataUploaderJob.class);

   @Resource(name = "xlsSource")
   private InputStreamSource xlsSource;

   @Resource
   private ISettingService settingService;

   @Resource
   private IBookService bookService;

   public void doIt() {
      Collection<Book> books = bookService.uploadBookFile();
      bookService.save(books);
      LOG.info(String.format("Uploaded %d books:", books.size()));
   }
}
