package org.benchmark;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class runProcessBuilder {
    public static void main(String[] args) {
        try {
            runProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static File NULL_FILE = new File(
            (System.getProperty("os.name")
                    .startsWith("Windows") ? "NUL" : "/dev/null")
    );
    public static int runProcess() throws IOException, InterruptedException {
        String cmd = "https -h google.com";
        List<String> commandList = Arrays.asList(cmd.split(" "));
        ProcessBuilder pb = new ProcessBuilder(commandList).inheritIO();
//        pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
//        pb.redirectError(ProcessBuilder.Redirect.DISCARD);
        pb.redirectOutput(NULL_FILE);
        Process p = pb.start();
        return p.waitFor();
    }
}
//public class runProcessBuilder {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        runProcess();
//    }
//    public static int runProcess() throws InterruptedException, IOException {
//        String cmd = "https -h google.com";
//        List<String> commandList = Arrays.asList(cmd.split(" "));
//        Process p = new ProcessBuilder().inheritIO().command(commandList).start();
//        Boolean flag = false;
////        while (!flag) {
////            p.waitFor();
////            flag = true;
////        }
//        return 0;
//    }
//}
//public class runProcessBuilder {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        runProcess();
//    }
////    private static void inheritIO(final InputStream src, final PrintStream dest) {
////        new Thread(new Runnable() {
////            public void run() {
////                Scanner sc = new Scanner(src);
////                while (sc.hasNextLine()) {
////                    dest.println(sc.nextLine());
////                }
////            }
////        }).start();
////    }
//    public static int runProcess() throws InterruptedException, IOException {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        String cmd = "docker build -t coderrect -f /Users/aaronzang/Desktop/Dockerfile.coderrect .";
//        List<String> commandList = Arrays.asList(cmd.split(" "));
//        processBuilder.command(commandList);
//        Process process = processBuilder.inheritIO().start();
////        StreamGobbler errorGobbler = new
////                StreamGobbler(process.getErrorStream(), "ERROR");
////
////        // any output?
////        StreamGobbler outputGobbler = new
////                StreamGobbler(process.getInputStream(), "OUTPUT");
//////        inheritIO(process.getInputStream(), System.out);
//////        inheritIO(process.getErrorStream(), System.err);
////        errorGobbler.start();
////        outputGobbler.start();
////        //System.out.println(process.waitFor());
//        return process.waitFor();
//
//    }
//
//}
////class StreamGobbler implements Runnable {
////    private InputStream inputStream;
////    private Consumer<String> consumeInputLine;
////
////    public StreamGobbler(InputStream inputStream, Consumer<String> consumeInputLine) {
////        this.inputStream = inputStream;
////        this.consumeInputLine = consumeInputLine;
////    }
////
////    public void run() {
////        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumeInputLine);
////    }
////}
//class StreamGobbler extends Thread
//{
//    InputStream is;
//    String type;
//
//    StreamGobbler(InputStream is, String type)
//    {
//        this.is = is;
//        this.type = type;
//    }
//
//    public void run()
//    {
//        try
//        {
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
////            Scanner scanner = new Scanner(isr);
//
//            String line = null;
////            System.out.println("aaaa");
////            int a = br.read();
////            System.out.println(a);
//            while ( isr.ready() && (line = br.readLine()) != null) {
//                System.out.println(type + ">" + line);
//            }
//        } catch (IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//    }
//}
