package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerListener implements ServletContextListener {


        @Override
        public void contextInitialized(ServletContextEvent sce) {
                System.out.println("kuku");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("bye bye");
        }
}
