package de.stevenschwenke.java.javaadvancedworkshop.jdk;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * This program demonstrates how to use the Watch Service API to monitor change
 * events for a specific directory.
 *
 * @author www.codejava.net, Steven Schwenke
 */
public class FileManipulator {

  public static void main(String[] args) {
    try {
      WatchService watcher = FileSystems.getDefault().newWatchService();
      Path directory = Paths.get("C:/Test");
      directory.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

      System.out.println("Watch Service registered for directory: " + directory.getFileName());

      while (true) {
        WatchKey key;
        try {
          key = watcher.take();
        } catch (InterruptedException ex) {
          return;
        }

        for (WatchEvent<?> event : key.pollEvents()) {
          WatchEvent.Kind<?> kind = event.kind();

          @SuppressWarnings("unchecked")
          WatchEvent<Path> ev = (WatchEvent<Path>) event;
          Path fileName = ev.context();

          System.out.println(kind.name() + ": " + fileName);
        }

        boolean valid = key.reset();
        if (!valid) {
          break;
        }
      }

    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
