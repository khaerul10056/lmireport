/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * ReportClassLoader.java
 *
 * Created on 24 maggio 2004, 12.56
 *
 */

package it.businesslogic.ireport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This special class loader is used when running a report.
 * The main feature is that this classloader reload Scriptlet class every
 * time the class is needed.
 * This class is based on JUnit test case class loader.
 * @author  Administrator
 */
public class ReportClassLoader extends java.lang.ClassLoader {

    /** scanned class path */
    private ArrayList fPathItems;

    /** scanned class path */
    private ArrayList fPathChachedItems;
    private HashMap cachedClasses;

    /** default excluded paths */

    /**
     * Constructs a ReloadableTestClassLoader. It tokenizes the value of
     * <code>reportPath</code> and adds it and any sub-paths to a list.
     * Paths are searched for tests.  All other classes are loaded
     * by parent loaders, to which this classloader always delegates.
     *
     * This ClassLoader never looks into or knows about the system classpath.
     * It should always refer to a path of repositories (jars or dirs)
     * <em>not</em> on the system classpath (as retrieved via
     * <code>reportPath</code>).
     *
     * @param classPath to scan and use for finding and reloading classes
     */
    public ReportClassLoader() {
        super( ReportClassLoader.class.getClassLoader() );
        setup();
    }

     public ReportClassLoader(ClassLoader parent) {

        super(parent);
        setup();
     }

     private void setup()
     {
        fPathItems = new ArrayList();
        fPathChachedItems = new ArrayList();
        cachedClasses = new HashMap();
        rescanLibDirectory();
     }

    /**
     *  Add to search paths list as no relodable jar/zip all new .jar,.zip not already in classpath
     */
     //LIMAO: 移除对$IREPORT_HOME/lib
    public void rescanLibDirectory()
    {
        try {
            rescanAdditionalClasspath();
        } catch (Exception ex) {}

        if (it.businesslogic.ireport.gui.MainFrame.getMainInstance() == null) return;
        // Looking for jars or zip in lib directory not in classpath...
        String irHome = it.businesslogic.ireport.gui.MainFrame.getMainInstance().IREPORT_HOME_DIR;
		if (irHome == null) irHome = System.getProperty("ireport.home",".");
		//File lib_dir = new File(irHome,"lib");

		String libpath = it.businesslogic.ireport.gui.MainFrame.getMainInstance().IREPORT_USER_HOME_DIR+"/lib";
		File lib_dir = new File(libpath);
		String classpath = it.businesslogic.ireport.util.Misc.nvl(System.getProperty("java.class.path"),"");

        if (!lib_dir.exists())
        {
        	lib_dir.mkdirs();
        	System.out.println("在目录："+libpath+"为类路径，需要的jar包可以添加于此");
            return;
        }

        //System.out.println("Rescan lib...");
        File[] new_libs = lib_dir.listFiles();

        for (int i=0; i< new_libs.length; ++i)
        {
            if (!new_libs[i].getName().toLowerCase().endsWith("jar") &&
                !new_libs[i].getName().toLowerCase().endsWith("zip")) continue;

            if (classpath.indexOf( new_libs[i].getName()) < 0)
            {
                try {
                    if (!fPathChachedItems.contains(new_libs[i].getCanonicalPath()))
                    {
                        //if ( new File(new_libs[i].getAbsolutePath()).exists())
                        //{
                            //System.out.println("Added lib " + new_libs[i].getCanonicalPath() + " to ireport class path\n");
                            fPathChachedItems.add(new_libs[i].getCanonicalPath());
                        //}
                    }
                } catch (Exception ex)
                {
                    System.out.println("Invalid path: " + new_libs[i]);
                }
            }
        }
    }

    public java.util.List getCachedItems()
    {
        return fPathChachedItems;
    }

    public void clearCache()
    {
        cachedClasses.clear();
        //System.out.println("Cached classes " + cachedClasses);
    }

    /**
     *  Add to search paths list as no relodable jar/zip all new .jar,.zip not already in classpath
     */
    public void rescanAdditionalClasspath()
    {
        if (it.businesslogic.ireport.gui.MainFrame.getMainInstance() == null) return;
        // Looking for jars or zip in lib directory not in classpath...
        Vector cp = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getClasspath();
        for (int i=0; i<cp.size(); ++i)
        {
            File f = new File( cp.elementAt(i) + "" );
            if (!f.exists()) continue;
            try {
                if (!fPathChachedItems.contains(f.getCanonicalPath()))
                {
                      fPathChachedItems.add(f.getCanonicalPath());
                }
            } catch (Exception ex)
            {
                System.out.println("Invalid path: " + f);
            }


        }
    }

    /**
     *  Add a dir or a file (i.e. a jar or a zip) to the search path
     */
    public void addNoRelodablePath(String path)
    {
        if (!fPathChachedItems.contains(path))
        {
          fPathChachedItems.add(path);
        }
    }

    public void setRelodablePaths(String classPath)
    {
       scanPath(classPath);
    }

    private void scanPath(String classPath) {
        String separator = System.getProperty("path.separator");
        fPathItems = new ArrayList(31);
        StringTokenizer st = new StringTokenizer(classPath, separator);
        while (st.hasMoreTokens()) {
            String pp = st.nextToken();
            //System.out.println("add " + pp);
            fPathItems.add(pp);
        }
    }

    public URL getResource(String name) {
                // We have to try to solve the name...
        return this.findResource(name);
        /*
        for (int i = 0; i < fPathChachedItems.size(); i++) {
            String path = (String) fPathChachedItems.get(i);

            if (isJar(path))
            {
                InputStream is = getInputStreamFromJar(path, name);
                if (is != null)
                {
                    try {
                        is.close();
                        File f = new File(path);
                        System.out.println("jar:" + f.toURL() + "!" + name);
                        return new URL("jar:" + f.toURL() + "!" + name);
                        //return new URL("jar:file:/" + path + "!" + name);
                    } catch (Exception ex)
                    {}
                }
            }
            else
            {
                File f = new File(path, name);
                if (f.exists())
                {
                    try {
                        return f.toURL();
                        //return new URL("file:/" + f);
                    } catch (Exception ex)
                    {}
                }
            }
        }

        // Else try to load from reloadable paths...
        for (int i = 0; i < fPathItems.size(); i++) {

            String path = (String) fPathItems.get(i);

            if (isJar(path))
            {
                InputStream is = getInputStreamFromJar(path, name);
                if (is != null)
                {
                    try {
                        is.close();
                        File f = new File(path);
                        System.out.println("jar:" + f.toURL() + "!" + name);
                        return new URL("jar:" + f.toURL() + "!" + name);
                    } catch (Exception ex)
                    {}
                }
            }
            else
            {
                File f = new File(path, name);
                if (f.exists())
                {
                    try {
                        return f.toURL();
                        //return new URL("file:/" + f);
                    } catch (Exception ex)
                    {}
                }
            }
        }

        return ClassLoader.getSystemResource(name);
         **/
    }

    public InputStream getResourceAsStream(String name) {

        // We have to try to solve the name...
        for (int i = 0; i < fPathChachedItems.size(); i++) {
            String path = (String) fPathChachedItems.get(i);

            if (isJar(path))
            {
                InputStream is = getInputStreamFromJar(path, name);
                if (is != null) return is;
            }
            else
            {
                File f = new File(path, name);
                if (f.exists())
                {
                     try {
                        return new FileInputStream( f );
                    } catch (Exception ex)
                    {}
                }
            }
        }

        // Else try to load from reloadable paths...
        for (int i = 0; i < fPathItems.size(); i++) {

            String path = (String) fPathItems.get(i);

            if (isJar(path))
            {
                InputStream is = getInputStreamFromJar(path, name);
                if (is != null) return is;
            }
            else
            {
                File f = new File(path, name);
                if (f.exists())
                {
                     try {
                        return new FileInputStream( f );
                    } catch (Exception ex)
                    {}
                }
            }
        }

        return ClassLoader.getSystemResourceAsStream(name);
    }

       private InputStream getInputStreamFromJar(String archive_path, String fileName) {
        ZipFile zipFile = null;
        InputStream stream = null;
        File archive = new File(archive_path);
        if (!archive.exists())
        {
            //System.out.println("Il jar non esiste!");
            return null;
        }
        try {
            zipFile = new ZipFile(archive);
        } catch (IOException io) {
            //io.printStackTrace();
            return null;
        }

        //System.out.println("Ricerca entry" + fileName );
        ZipEntry entry = zipFile.getEntry(fileName);
        if (entry == null)
        {
            //System.out.println("Entry null!");
            return null;
        }
        try {
            return zipFile.getInputStream(entry);
        } catch (IOException e) {
        } finally {
        }

        return null;
    }


    public synchronized Class findClass(String name) throws ClassNotFoundException {

        Class c = null;

        if (cachedClasses.containsKey( name ))
        {
            c = (Class)cachedClasses.get(name);

        }
        else
        {
            c =  loadClassData(name);
        }


        return c;
        /*
        if (cachedClasses.containsKey( name ))
        {
            return (Class)cachedClasses.get(name);
        }

        return defineClass(name, b, 0, b.length);
        */
    }

    // From here down is all code copied and pasted from JUnit's
    // TestCaseClassLoader
    private Class loadClassData(String className)
        throws ClassNotFoundException {

        // 1. Look for cached class...

        // if we can't find the cached class, looking first in no relodable paths...

        byte[] data = null;

        if (!cachedClasses.containsKey(className))
        {

            for (int i = 0; i < fPathChachedItems.size(); i++) {


                String path = (String) fPathChachedItems.get(i);
                String fileName = className.replace('.', File.separatorChar) + ".class";


                if (isJar(path)) {
                    //System.out.println("looking for " + fileName.replace(File.separatorChar,'/') + " in jar " +path);
                    data = loadJarData(path, fileName.replace(File.separatorChar,'/'));
                } else {
                    //System.out.println("looking for " + fileName + " in dir " +path);
                    data = loadFileData(path, fileName.replace(File.separatorChar,'/'));
                }
                if (data != null)
                {
                    Class c = defineClass(className, data, 0, data.length);
                    cachedClasses.put(className,c);

                    return c;
                }
            }
        }
        else
        {
            return (Class)cachedClasses.get(className);
        }

        // Else try to load from reloadable paths...
        for (int i = 0; i < fPathItems.size(); i++) {


                String path = (String) fPathItems.get(i);
                String fileName = className.replace('.', File.separatorChar) + ".class";

                if (isJar(path)) {
                    data = loadJarData(path, fileName);
                } else {
                    data = loadFileData(path, fileName);
                }
                if (data != null)
                {
                    Class c = defineClass(className, data, 0, data.length);
                    return c;
                }
            }

        throw new ClassNotFoundException(className);
    }

    boolean isJar(String pathEntry) {
        return pathEntry.toLowerCase().endsWith(".jar") || pathEntry.toLowerCase().endsWith(".zip");
    }

    private byte[] loadFileData(String path, String fileName) {
        File file = new File(path, fileName);
        //System.out.println("Final class name: " + file.getPath());
        if (file.exists()) {
            return getClassData(file);
        }
        return null;
    }

    private byte[] getClassData(File f) {
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();

        } catch (IOException e) {
        }
        return null;
    }

    private byte[] loadJarData(String path, String fileName) {
        ZipFile zipFile = null;
        InputStream stream = null;
        File archive = new File(path);
        if (!archive.exists())
        {
            //System.out.println("Il jar non esiste!");
            return null;
        }
        try {
            zipFile = new ZipFile(archive);
        } catch (IOException io) {
            //io.printStackTrace();
            return null;
        }

        //System.out.println("Ricerca entry" + fileName );
        ZipEntry entry = zipFile.getEntry(fileName);
        if (entry == null)
        {
            //System.out.println("Entry null!");
            return null;
        }
        int size = (int) entry.getSize();
        try {
            stream = zipFile.getInputStream(entry);
            byte[] data = new byte[size];
            int pos = 0;
            while (pos < size) {
                int n = stream.read(data, pos, data.length - pos);
                pos += n;
            }
            zipFile.close();
            return data;
        } catch (IOException e) {
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        //System.out.println("Class not found really!");
        return null;
    }


    public Enumeration findResources(String name) {
        // We have to try to solve the name...
        Vector urls = new Vector();

        URL[] pathUrls = new URL[fPathChachedItems.size() + fPathItems.size()];

        for (int i = 0; i < fPathChachedItems.size(); i++) {
            String path = (String) fPathChachedItems.get(i);
            try {
                pathUrls[i] = (new File(path)).toURL();
            } catch (Exception ex)
            {
            }
        }

        for (int i = 0; i < fPathItems.size(); i++) {
            String path = (String) fPathItems.get(i);
            try {
                pathUrls[i + fPathChachedItems.size()] = (new File(path)).toURL();
            } catch (Exception ex)
            {
            }
        }

        URLClassLoader urlCl = new URLClassLoader(pathUrls, null);
        try {
            return urlCl.findResources(name);
        } catch (Exception ex)
        {

        }

        return new Vector().elements();

    }

    public URL findResource(String name) {

        if (name.startsWith("/")) name = name.substring(1);
        URL[] pathUrls = new URL[fPathChachedItems.size() + fPathItems.size()];

        for (int i = 0; i < fPathChachedItems.size(); i++) {
            String path = (String) fPathChachedItems.get(i);
            try {
                pathUrls[i] = (new File(path)).toURL();
            } catch (Exception ex)
            {
            }
        }

        for (int i = 0; i < fPathItems.size(); i++) {
            String path = (String) fPathItems.get(i);
            try {
                pathUrls[i + fPathChachedItems.size()] = (new File(path)).toURL();
            } catch (Exception ex)
            {
            }
        }

        URLClassLoader urlCl = new URLClassLoader(pathUrls, null);
        try {
            URL url = urlCl.findResource(name);
            //System.out.println( url);
            if (url != null) return url;
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return ClassLoader.getSystemResource(name);

    }
}
