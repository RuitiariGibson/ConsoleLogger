package com.gibsconcodes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * a logger for lazy people
 * Example usage
 * <pre class="code"><code class="java">
 *  Thread thread=new Thread(new Runnable() {
 *             @Override
 *             public void run() {
 *                 try {
 *                     Thread.sleep(10000);
 *                     ConsoleLogger.w("sleeping for 10000");
 *
 *                 } catch (InterruptedException e) {
 *                     e.printStackTrace();
 *                 }
 *             }
 *         });
 *         thread.start();
 *         try{
 *             System.out.println(args[2]);
 *         }catch (Throwable e){
 *             ConsoleLogger.e(e,"warning");
 *         }
 * </code></pre>
 */
public class ConsoleLogger  {

    private static final DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("E M HH:mm:ss z Y ");
    private ConsoleLogger(){ }

/* log a debug exception */
public static void d(String message){
        prepareLog(System.Logger.Level.DEBUG,
                message,null);
}
    /* log a debug message & throwable */

public static void d(Throwable throwable,String message){
        prepareLog(System.Logger.Level.DEBUG,
                message,throwable);
}
    /* log a debug exception */

public static void d(Throwable throwable){
        prepareLog(System.Logger.Level.DEBUG,
                null,throwable);
}
    /* log an error message */

public static void e(String message){
        prepareLog(System.Logger.Level.ERROR,
                message,null);
}
    /* log an error message & exception */

public static void e(Throwable throwable,String message){
        prepareLog(System.Logger.Level.ERROR,
                message,throwable);
}
    /* log an error exception */

public static void e(Throwable throwable){
        prepareLog(System.Logger.Level.ERROR,
                null,throwable);
}
    /* log a warning message */

    public static void w(String message){
        prepareLog(System.Logger.Level.WARNING,
                message,null);
    }
    /* log a warning exception  */

    public static void w(Throwable throwable,String message){
        prepareLog(System.Logger.Level.WARNING,
                message,throwable);
    }
    /* log a warning exception */

    public static void w(Throwable throwable){
        prepareLog(System.Logger.Level.WARNING,
                null,throwable);
    }
    /* log an info message */

    public static void i(String message){
        prepareLog(System.Logger.Level.INFO,
                message,null);
    }
    /* log a info message & an exception */

    public static void i(Throwable throwable,String message){
        prepareLog(System.Logger.Level.INFO,
                message,throwable);
    }
    /* log an info exception */

    public static void i(Throwable throwable){
        prepareLog(System.Logger.Level.INFO,
                null,throwable);
    }


    /** Return whether a message at `priority` should be logged. */
private static boolean isLoggable(System.Logger.Level level){
      switch (level){
          case INFO:
          case WARNING:
          case ERROR:
          case DEBUG:
              return true;
          default:
              return false;
      }
}
    private static void prepareLog(System.Logger.Level priority,String message,Throwable throwable){
            System.Logger.Level level1 = null;
            String msg = null;
            if (isLoggable(priority)) {
                level1 = priority;
            }else throw new IllegalArgumentException("The priority provided must be one of System.logger types");
            if (message!=null){
                   msg= message;
               }
            log(level1, msg, throwable);
    }
    /** log the message by printing it to the console . You can change the colors if you want ^_^ but remember to reset the
     * color or else the color will persist .
      */

    private static void log(System.Logger.Level priority,String message,Throwable throwable){
          final String ANSI_RED = "\u001B[31m";
          final String ANSI_GREEN = "\u001B[32m";
          final String ANSI_YELLOW = "\u001B[33m";
          final String ANSI_BLUE = "\u001B[34m";
         final String ANSI_RESET = "\u001B[0m";

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String dateString = dateTimeFormatter.format(zonedDateTime);
        StringWriter stringWriter=new StringWriter(256);
        PrintWriter printWriter=new PrintWriter(stringWriter,true);
        StackTraceElement s;
        if (Objects.nonNull(throwable)){
            s=throwable.getStackTrace()[0];
        }else s=Thread.currentThread().getStackTrace()[1];
        if (priority== System.Logger.Level.ERROR){
            if (Objects.isNull(throwable)){
                printWriter.printf(ANSI_RED+"%s [ERROR]: %s ",dateString,message);
            }else{
                printWriter.printf(ANSI_RED+"%s [ERROR]: %s occurred  %s at line %s ",dateString,throwable.getClass().getSimpleName(),
                        throwable.getMessage(),s.getLineNumber()+ANSI_RESET);
                printWriter.printf(" .(%s:%s)",s.getFileName(),s.getLineNumber());
            }
            System.out.println(stringWriter.toString());
            printWriter.flush();

        }else if (priority==System.Logger.Level.DEBUG){
            printWriter.printf(ANSI_BLUE+"%s [DEBUG]: %s",dateString,message+ANSI_RESET);
            System.out.println(stringWriter.toString());
            printWriter.flush();
        }else if (priority== System.Logger.Level.INFO){
            printWriter.printf(ANSI_GREEN+"%s [INFO]: %s %n",dateString,message+ANSI_RESET);
            System.out.println(stringWriter.toString());
            printWriter.flush();
        }else if (priority== System.Logger.Level.WARNING){
            printWriter.printf(ANSI_YELLOW+"%s [WARNING]: %s %n",dateString,message+ANSI_RESET);
            System.out.println(stringWriter.toString());
            printWriter.flush();
        }
    }

}
