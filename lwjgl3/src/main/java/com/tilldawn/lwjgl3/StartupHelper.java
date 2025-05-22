//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.tilldawn.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import org.lwjgl.system.JNI;
import org.lwjgl.system.macosx.LibC;
import org.lwjgl.system.macosx.ObjCRuntime;

public class StartupHelper {
    private static final String JVM_RESTARTED_ARG = "jvmIsRestarted";

    private StartupHelper() {
        throw new UnsupportedOperationException();
    }

    public static boolean startNewJvmIfRequired(boolean redirectOutput) {
        String osName = System.getProperty("os.name").toLowerCase();
        if (!osName.contains("mac")) {
            if (osName.contains("windows")) {
                String programData = System.getenv("ProgramData");
                if (programData == null) {
                    programData = "C:\\Temp\\";
                }

                String prevTmpDir = System.getProperty("java.io.tmpdir", programData);
                String prevUser = System.getProperty("user.name", "libGDX_User");
                System.setProperty("java.io.tmpdir", programData + "/libGDX-temp");
                System.setProperty("user.name", ("User_" + prevUser.hashCode() + "_GDX1.12.0").replace('.', '_'));
                Lwjgl3NativesLoader.load();
                System.setProperty("java.io.tmpdir", prevTmpDir);
                System.setProperty("user.name", prevUser);
            }

            return false;
        } else if (!System.getProperty("org.graalvm.nativeimage.imagecode", "").isEmpty()) {
            return false;
        } else {
            long objc_msgSend = ObjCRuntime.getLibrary().getFunctionAddress("objc_msgSend");
            long NSThread = ObjCRuntime.objc_getClass("NSThread");
            long currentThread = JNI.invokePPP(NSThread, ObjCRuntime.sel_getUid("currentThread"), objc_msgSend);
            boolean isMainThread = JNI.invokePPZ(currentThread, ObjCRuntime.sel_getUid("isMainThread"), objc_msgSend);
            if (isMainThread) {
                return false;
            } else {
                long pid = LibC.getpid();
                if ("1".equals(System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" + pid))) {
                    return false;
                } else if ("true".equals(System.getProperty("jvmIsRestarted"))) {
                    System.err.println("There was a problem evaluating whether the JVM was started with the -XstartOnFirstThread argument.");
                    return false;
                } else {
                    ArrayList<String> jvmArgs = new ArrayList();
                    String separator = System.getProperty("file.separator", "/");
                    String var10000 = System.getProperty("java.home");
                    String javaExecPath = var10000 + separator + "bin" + separator + "java";
                    if (!(new File(javaExecPath)).exists()) {
                        System.err.println("A Java installation could not be found. If you are distributing this app with a bundled JRE, be sure to set the -XstartOnFirstThread argument manually!");
                        return false;
                    } else {
                        jvmArgs.add(javaExecPath);
                        jvmArgs.add("-XstartOnFirstThread");
                        jvmArgs.add("-DjvmIsRestarted=true");
                        jvmArgs.addAll(ManagementFactory.getRuntimeMXBean().getInputArguments());
                        jvmArgs.add("-cp");
                        jvmArgs.add(System.getProperty("java.class.path"));
                        String mainClass = System.getenv("JAVA_MAIN_CLASS_" + pid);
                        if (mainClass == null) {
                            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
                            if (trace.length <= 0) {
                                System.err.println("The main class could not be determined.");
                                return false;
                            }

                            mainClass = trace[trace.length - 1].getClassName();
                        }

                        jvmArgs.add(mainClass);

                        try {
                            if (!redirectOutput) {
                                ProcessBuilder processBuilder = new ProcessBuilder(jvmArgs);
                                processBuilder.start();
                            } else {
                                Process process = (new ProcessBuilder(jvmArgs)).redirectErrorStream(true).start();
                                BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                                String line;
                                while((line = processOutput.readLine()) != null) {
                                    System.out.println(line);
                                }

                                process.waitFor();
                            }
                        } catch (Exception e) {
                            System.err.println("There was a problem restarting the JVM");
                            e.printStackTrace();
                        }

                        return true;
                    }
                }
            }
        }
    }

    public static boolean startNewJvmIfRequired() {
        return startNewJvmIfRequired(true);
    }
}
