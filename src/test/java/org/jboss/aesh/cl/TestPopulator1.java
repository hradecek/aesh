/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.aesh.cl;

import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.util.List;
import org.jboss.aesh.console.command.CommandException;

@CommandDefinition(name = "test", description = "a simple test")
public class TestPopulator1 implements Command {

    @Option(shortName = 'X', name = "X", description = "enable X", hasValue = false)
    private Boolean enableX;

    @Option(shortName = 'b', hasValue = false)
    public boolean bar;

    @Option(shortName = 'f', name = "foo", description = "enable foo", hasValue = false)
    public boolean foo;

    @Option(shortName = 'e', name = "equal", description = "enable equal", required = true)
    public String equal;

    @Option(shortName = 'i', name = "int1", defaultValue = {"42"})
    private Integer int1;

    @Option(shortName = 'n')
    public int int2;

    @Arguments(defaultValue = {"foo"})
    public List<String> arguments;

    /*
    @OptionGroup(shortName = 'D', description = "define properties")
    public Map<String, String> define;
    */

    public TestPopulator1() {
    }

    public String getEqual() {
        return equal;
    }

    public Boolean getEnableX() {
        return enableX;
    }

    public Integer getInt1() {
        return int1;
    }

    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
        return CommandResult.SUCCESS;
    }
}