/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.benchmark;

import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;


@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 2)
public class MyBenchmark {

    @State(Scope.Benchmark)
    public static class Commands{
//        @Param({"ls -a", "pwd", "whoami", "cal 2020", "du",
//        "cat pom.xml", "echo hello world", "df", "date"})
        @Param({"https -h google.com"})
        public String cmd;

        public List<String> commandList;

        @Setup(Level.Iteration)
        public void setup(){
            commandList = Arrays.asList(cmd.split(" "));
        }
    }
    private static File NULL_FILE = new File(
            (System.getProperty("os.name")
                    .startsWith("Windows") ? "NUL" : "/dev/null")
    );
    @Benchmark
    public int runProcess(Commands command) throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.commandList);
        processBuilder.redirectOutput(NULL_FILE);
        Process process = processBuilder.start();

        return process.waitFor();
    }
//
//    @Benchmark
//    public int runProcessStd(Commands command) throws IOException, InterruptedException {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        File errorFile = new File("./", "error.txt");
//        File outputFile = new File("./", "output.txt");
//        processBuilder.command(command.commandList)
//                .redirectError(errorFile)
//                .redirectOutput(outputFile);
//        Process process = processBuilder.start();
//        return process.waitFor();
//    }
//    @Benchmark
//    public int apacheProcessStd(Commands command) throws IOException {
//        OutputStream output = new FileOutputStream("./apache_out.txt");
//        OutputStream error = new FileOutputStream("./apache_err.txt");
//        File workDir = new File(System.getProperty("user.dir"));
//        CommandLine commandLine = CommandLine.parse(command.cmd);
//        DefaultExecutor exec = new DefaultExecutor();
//        PumpStreamHandler streamHandler = new PumpStreamHandler(output, error);
//        exec.setWorkingDirectory(workDir);
//        exec.setStreamHandler(streamHandler);
//
//        int exitCode = exec.execute(commandLine);
//
//        streamHandler.stop();
//        output.close();
//        error.close();
//        return exitCode;
//    }

//    @Benchmark
//    public int apacheProcess(Commands command) throws IOException {
//        CommandLine commandLine = CommandLine.parse(command.cmd);
//        DefaultExecutor exec = new DefaultExecutor();
//
//        return exec.execute(commandLine);
//    }



}
