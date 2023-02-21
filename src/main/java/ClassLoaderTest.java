import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;

/**
 * @Author RHD
 * @Description TODO
 * @Date 2023/2/21 21:44
 * @Project_name JUMDemo
 * @Version 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2


        //获取扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);//sun.misc.Launcher$ExtClassLoader@1540e19d


        //获取不到引导类加载器
        ClassLoader parent1 = parent.getParent();
          //null

        //获取用户自定义类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();  //默认使用系统类加载器
        System.out.println(classLoader); // //sun.misc.Launcher$AppClassLoader@18b4aac2
        //java核心类库使用引导类加载器加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1); //null

        //引导类加载器可以加载的类路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        System.out.println("===============系统类加载器加载的路径=================");
        String property = System.getProperty("java.ext.dirs");
        for(String path : property.split(";")){
            System.out.println(path);
        }
//        ===============系统类加载器加载的路径=================
//        E:\Java\jdk1.8.0_152\jre\lib\ext
//        C:\WINDOWS\Sun\Java\lib\ext
        ClassLoader classLoader2 = CurveDB.class.getClassLoader();
        System.out.println(classLoader2); //sun.misc.Launcher$ExtClassLoader@1540e19d


        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);


        ClassLoader classLoader3 = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader3);

    }
}
