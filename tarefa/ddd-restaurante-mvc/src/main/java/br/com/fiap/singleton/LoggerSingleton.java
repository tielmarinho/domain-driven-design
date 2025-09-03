package br.com.fiap.singleton;

public class LoggerSingleton {
    private static volatile LoggerSingleton INSTANCE;
    private LoggerSingleton() {}

    public static LoggerSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LoggerSingleton.class) {
                if (INSTANCE == null) INSTANCE = new LoggerSingleton();
            }
        }
        return INSTANCE;
    }

    public void info(String msg) { System.out.println("[INFO] " + msg); }
    public void error(String msg, Throwable t) {
        System.err.println("[ERROR] " + msg);
        t.printStackTrace(System.err);
    }
}