/**
 * Copyright (C) 2010-16 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.rvesse.airline.tests;

import java.util.LinkedHashSet;
import java.util.List;

import com.github.rvesse.airline.help.UsageHelper;
import com.github.rvesse.airline.help.common.AbstractUsageGenerator;
import com.github.rvesse.airline.model.ArgumentsMetadata;
import com.github.rvesse.airline.model.OptionMetadata;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractUsageGeneratorTest {

    static class UsageGeneratorImpl extends AbstractUsageGenerator {
        UsageGeneratorImpl(boolean includeHidden) {
            super(UsageHelper.DEFAULT_HINT_COMPARATOR, UsageHelper.DEFAULT_OPTION_COMPARATOR,
                    UsageHelper.DEFAULT_COMMAND_COMPARATOR, includeHidden);
        }

        @Override
        protected String toDescription(OptionMetadata option) {
            return super.toDescription(option);
        }

        @Override
        protected String toDescription(ArgumentsMetadata arguments) {
            return super.toDescription(arguments);
        }

        @Override
        protected List<String> toSynopsisUsage(List<OptionMetadata> options) {
            return super.toSynopsisUsage(options);
        }

        @Override
        protected String toUsage(OptionMetadata option) {
            return super.toUsage(option);
        }
    }

    @Test
    public void toDescriptionOptionMetadata() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var optionMetadata = mock(OptionMetadata.class);
        var optionsSet = new LinkedHashSet<String>();
        optionsSet.add("foo");
        optionsSet.add("bar");
        // We can not use Set.of("foo", "bar") because the iteration order is not defined!
        when(optionMetadata.getOptions()).thenReturn(optionsSet);
        var toDescription = generator.toDescription(optionMetadata);
        assertThat(toDescription).isEqualTo("foo, bar");
    }

    @Test
    public void toDescriptionArgumentsMetadata() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var argumentsMetadata = mock(ArgumentsMetadata.class);
        when(argumentsMetadata.getTitle()).thenReturn(List.of("A", "B", "C"));
        var toDescription = generator.toDescription(argumentsMetadata);
        assertThat(toDescription).isEqualTo("<A> <B> <C>");
    }

    @Test
    public void toSynopsisUsage() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var om1 = mock(OptionMetadata.class);
        when(om1.isHidden()).thenReturn(false);
        var optionsSet = new LinkedHashSet<String>();
        optionsSet.add("op1");
        optionsSet.add("op2");
        // We can not use Set.of("op1", "op2") because the iteration order is not defined!
        when(om1.getOptions()).thenReturn(optionsSet);
        var toDescription = generator.toSynopsisUsage(List.of(om1));
        assertThat(toDescription).containsExactly("[ {op1 | op2} ]");
    }

    @Test
    public void toUsageSingleOption() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var om1 = mock(OptionMetadata.class);
        when(om1.isRequired()).thenReturn(false);
        var optionsSet = new LinkedHashSet<String>();
        optionsSet.add("op1");
        // We can not use Set.of("op1") because the iteration order is not defined!
        when(om1.getOptions()).thenReturn(optionsSet);
        var toDescription = generator.toUsage(om1);
        assertThat(toDescription).isEqualTo("[ op1 ]");
    }
    @Test
    public void toUsageTwoOptions() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var om1 = mock(OptionMetadata.class);
        when(om1.isRequired()).thenReturn(false);
        var optionsSet = new LinkedHashSet<String>();
        optionsSet.add("op1");
        optionsSet.add("op2");
        // We can not use Set.of("op1", "op2") because the iteration order is not defined!
        when(om1.getOptions()).thenReturn(optionsSet);
        var toDescription = generator.toUsage(om1);
        assertThat(toDescription).isEqualTo("[ {op1 | op2} ]");
    }
    @Test
    public void toUsageTwoOptionsAndTitles() {
        UsageGeneratorImpl generator = new UsageGeneratorImpl(false);
        var om1 = mock(OptionMetadata.class);
        when(om1.isRequired()).thenReturn(false);
        var optionsSet = new LinkedHashSet<String>();
        optionsSet.add("op1");
        optionsSet.add("op2");
        // We can not use Set.of("op1", "op2") because the iteration order is not defined!
        when(om1.getOptions()).thenReturn(optionsSet);
        when(om1.getTitles()).thenReturn(List.of("T1", "T2"));
        when(om1.getTitle(0)).thenReturn("T1");
        when(om1.getTitle(1)).thenReturn("T2");
        when(om1.getArity()).thenReturn(2);
        var toDescription = generator.toUsage(om1);
        assertThat(toDescription).isEqualTo("[ {op1 | op2} <T1> <T2> ]");
    }
}