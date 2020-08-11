

<p align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a><br>
   <a><img src="https://github.com/RuitiariGibson/ConsoleLogger/blob/master/log.png"/><a/>

  <h3 align="center">Console Logger</h3>

This is a small Java logger, inspired by [Timber] (https://github.com/JakeWharton/timber) ,which logs colorful messages/logs to your console.

Usage
-----

One easy step:

 1. Call `ConsoleLogger` static methods anywhere in your app
 For example
 
 ```
 // example 1
 Thread thread=new Thread(new Runnable() {
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
 // example 2
 *         try{
 *             System.out.println(args[2]);
 *         }catch (Throwable e){
 *             ConsoleLogger.e(e,"warning");
 *         }
 ```
 
 License
-------

    Copyright 2013 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 

