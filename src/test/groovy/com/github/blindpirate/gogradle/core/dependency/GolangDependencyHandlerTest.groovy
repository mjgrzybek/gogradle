/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.blindpirate.gogradle.core.dependency

import com.github.blindpirate.gogradle.GogradleRunner
import com.github.blindpirate.gogradle.core.GolangConfiguration
import com.github.blindpirate.gogradle.core.GolangConfigurationManager
import com.github.blindpirate.gogradle.core.GolangDependencyHandler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@RunWith(GogradleRunner)
class GolangDependencyHandlerTest {

    @Mock
    GolangConfigurationManager configurationManager
    @Mock
    GolangConfiguration configuration
    @Mock
    AbstractGolangDependency dependency

    GolangDependencyHandler handler

    @Before
    void setUp() {
        handler = new GolangDependencyHandler(configurationManager)
        when(configurationManager.getByName('build')).thenReturn(configuration)
    }

    @Test(expected = MissingMethodException)
    void 'exception should be thrown if no configuration found'() {
        handler.unexistent('')
    }

    @Test
    void 'creating dependency should succeed'() {
        // when
        Closure c = { 1 }
        handler.build([:], c)
        // then
        verify(configuration).addFirstLevelDependency([:], c)
    }
}
