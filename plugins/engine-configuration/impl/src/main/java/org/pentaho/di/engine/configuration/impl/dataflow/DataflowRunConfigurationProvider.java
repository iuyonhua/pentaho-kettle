/*
 * *****************************************************************************
 *
 *  Pentaho Data Integration
 *
 *  Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *  *******************************************************************************
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 *  this file except in compliance with the License. You may obtain a copy of the
 *  License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * *****************************************************************************
 *
 */
package org.pentaho.di.engine.configuration.impl.dataflow;

import org.pentaho.di.engine.configuration.api.RunConfiguration;
import org.pentaho.di.engine.configuration.api.RunConfigurationExecutor;
import org.pentaho.di.engine.configuration.api.RunConfigurationProvider;
import org.pentaho.di.engine.configuration.impl.MetaStoreRunConfigurationFactory;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.metastore.api.exceptions.MetaStoreException;
import org.pentaho.metastore.persist.MetaStoreFactory;
import org.pentaho.osgi.metastore.locator.api.MetastoreLocator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ccaspanello on 6/13/18.
 */
public class DataflowRunConfigurationProvider extends MetaStoreRunConfigurationFactory
  implements RunConfigurationProvider {

  public static String TYPE = "Dataflow";
  private DataflowRunConfigurationExecutor dataflowRunConfigurationExecutor;
  private List<String> supported = Arrays.asList( TransMeta.XML_TAG );

  public DataflowRunConfigurationProvider( MetastoreLocator metastoreLocator,
                                           DataflowRunConfigurationExecutor dataflowRunConfigurationExecutor ) {
    super( metastoreLocator );
    this.dataflowRunConfigurationExecutor = dataflowRunConfigurationExecutor;
  }

  @Override
  public RunConfiguration getConfiguration() {
    return new DataflowRunConfiguration();
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @SuppressWarnings( "unchecked" )
  protected MetaStoreFactory<DataflowRunConfiguration> getMetaStoreFactory() throws MetaStoreException {
    return getMetastoreFactory( DataflowRunConfiguration.class );
  }

  @Override
  public RunConfigurationExecutor getExecutor() {
    return dataflowRunConfigurationExecutor;
  }

  @Override
  public boolean isSupported( String type ) {
    return supported.contains( type );
  }

  @Override
  public List<String> getNames( String type ) {
    return isSupported( type ) ? getNames() : Collections.emptyList();
  }
}
