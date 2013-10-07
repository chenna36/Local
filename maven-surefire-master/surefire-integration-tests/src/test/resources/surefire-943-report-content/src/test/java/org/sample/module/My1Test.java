package org.sample.module;

/*
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
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.junit.Assert.fail;

import java.lang.management.ManagementFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class My1Test
{
    @Test
    public void fails()
        throws Exception
    {
        Thread.sleep( 100 );
        fail( "Always fails" );
    }

    @Test
    public void alwaysSuccessful()
        throws Exception
    {
        Thread.sleep( 100 );
    }

    @Test
    @Ignore( "Ignore-Message" )
    public void alwaysIgnored()
    {

    }
}
