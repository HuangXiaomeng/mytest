/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.armon.test.quartz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
/**
 * Adds quartz configuration files to a Configuration
 */
public class QuartzConfiguration extends Configuration {

  private static final Log LOG = LogFactory.getLog(QuartzConfiguration.class);

  /**
   * Instantinating QuartzConfiguration() is deprecated. Please use
   * QuartzConfiguration#create() to construct a plain Configuration
   */
  @Deprecated
  public QuartzConfiguration() {
    //TODO:replace with private constructor, QuartzConfiguration should not extend Configuration
    super();
    addQuartzResources(this);
    LOG.warn("instantiating QuartzConfiguration() is deprecated. Please use"
        + " QuartzConfiguration#create() to construct a plain Configuration");
  }

  /**
   * Instantiating QuartzConfiguration() is deprecated. Please use
   * QuartzConfiguration#create(conf) to construct a plain Configuration
   */
  @Deprecated
  public QuartzConfiguration(final Configuration c) {
    //TODO:replace with private constructor
    this();
    merge(this, c);
  }

  public static Configuration addQuartzResources(Configuration conf) {
    conf.addResource("quartz-site.xml");

    return conf;
  }

  /**
   * Creates a Configuration with quartz resources
   * @return a Configuration with quartz resources
   */
  public static Configuration create() {
    Configuration conf = new Configuration();
    // In case QuartzConfiguration is loaded from a different classloader than
    // Configuration, conf needs to be set with appropriate class loader to resolve
    // quartz resources.
    conf.setClassLoader(QuartzConfiguration.class.getClassLoader());
    return addQuartzResources(conf);
  }

  /**
   * @param that Configuration to clone.
   * @return a Configuration created with the quartz-*.xml files plus
   * the given configuration.
   */
  public static Configuration create(final Configuration that) {
    Configuration conf = create();
    merge(conf, that);
    return conf;
  }

  /**
   * Merge two configurations.
   * @param destConf the configuration that will be overwritten with items
   *                 from the srcConf
   * @param srcConf the source configuration
   **/
  public static void merge(Configuration destConf, Configuration srcConf) {
    for (Entry<String, String> e : srcConf) {
      destConf.set(e.getKey(), e.getValue());
    }
  }

  /**
   * @return whether to show quartz Configuration in servlet
   */
  public static boolean isShowConfInServlet() {
    boolean isShowConf = false;
    try {
      if (Class.forName("org.apache.hadoop.conf.ConfServlet") != null) {
        isShowConf = true;
      }
    } catch (LinkageError e) {
       // should we handle it more aggressively in addition to log the error?
       LOG.warn("Error thrown: ", e);
    } catch (ClassNotFoundException ce) {
      LOG.debug("ClassNotFound: ConfServlet");
      // ignore
    }
    return isShowConf;
  }

  /**
   * Get the value of the <code>name</code> property as an <code>int</code>, possibly
   * referring to the deprecated name of the configuration property.
   * If no such property exists, the provided default value is returned,
   * or if the specified value is not a valid <code>int</code>,
   * then an error is thrown.
   *
   * @param name property name.
   * @param deprecatedName a deprecatedName for the property to use
   * if non-deprecated name is not used
   * @param defaultValue default value.
   * @throws NumberFormatException when the value is invalid
   * @return property value as an <code>int</code>,
   *         or <code>defaultValue</code>.
   */
  // TODO: developer note: This duplicates the functionality of deprecated
  // property support in Configuration in Hadoop 2. But since Hadoop-1 does not
  // contain these changes, we will do our own as usual. Replace these when H2 is default.
  public static int getInt(Configuration conf, String name,
      String deprecatedName, int defaultValue) {
    if (conf.get(deprecatedName) != null) {
      LOG.warn(String.format("Config option \"%s\" is deprecated. Instead, use \"%s\""
        , deprecatedName, name));
      return conf.getInt(deprecatedName, defaultValue);
    } else {
      return conf.getInt(name, defaultValue);
    }
  }

  /**
   * Get the password from the Configuration instance using the
   * getPassword method if it exists. If not, then fall back to the
   * general get method for configuration elements.
   * @param conf configuration instance for accessing the passwords
   * @param alias the name of the password element
   * @param defPass the default password
   * @return String password or default password
   * @throws IOException
   */
  public static String getPassword(Configuration conf, String alias,
      String defPass) throws IOException {
    String passwd = null;
    try {
      Method m = Configuration.class.getMethod("getPassword", String.class);
      char[] p = (char[]) m.invoke(conf, alias);
      if (p != null) {
        LOG.debug(String.format("Config option \"%s\" was found through" +
            " the Configuration getPassword method.", alias));
        passwd = new String(p);
      }
      else {
        LOG.debug(String.format(
            "Config option \"%s\" was not found. Using provided default value",
            alias));
        passwd = defPass;
      }
    } catch (NoSuchMethodException e) {
      // this is a version of Hadoop where the credential
      //provider API doesn't exist yet
      LOG.debug(String.format(
          "Credential.getPassword method is not available." +
          " Falling back to configuration."));
      passwd = conf.get(alias, defPass);
    } catch (SecurityException e) {
      throw new IOException(e.getMessage(), e);
    } catch (IllegalAccessException e) {
      throw new IOException(e.getMessage(), e);
    } catch (IllegalArgumentException e) {
      throw new IOException(e.getMessage(), e);
    } catch (InvocationTargetException e) {
      throw new IOException(e.getMessage(), e);
    }
    return passwd;
  }

  /** For debugging.  Dump configurations to system output as xml format.
   * Master and RS configurations can also be dumped using
   * http services. e.g. "curl http://master:16010/dump"
   */
  public static void main(String[] args) throws Exception {
	  QuartzConfiguration.create().writeXml(System.out);
  }
}
