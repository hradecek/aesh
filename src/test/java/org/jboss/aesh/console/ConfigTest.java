/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.console;

import junit.framework.TestCase;
import org.jboss.aesh.console.settings.Settings;
import org.jboss.aesh.edit.KeyOperation;
import org.jboss.aesh.edit.Mode;
import org.jboss.aesh.edit.actions.Operation;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class ConfigTest extends TestCase {

    public ConfigTest(String name) {
        super(name);
    }


    public void testParseInputrc() throws IOException {
        Settings settings = Settings.getInstance();
        settings.resetToDefaults();
        settings.setInputrc( Config.isOSPOSIXCompatible() ?
                new File("src/test/resources/inputrc1") : new File("src\\test\\resources\\inputrc1"));

        Config.parseInputrc(settings);

        assertEquals(settings.getEditMode(), Mode.VI);

        assertEquals(settings.getBellStyle(), "visible");

        assertEquals(settings.getHistorySize(), 300);

        assertEquals(settings.isDisableCompletion(), true);

    }

    public void testParseInputrc2() throws IOException {
        Settings settings = Settings.getInstance();
        settings.resetToDefaults();
        settings.setInputrc( Config.isOSPOSIXCompatible() ?
                new File("src/test/resources/inputrc2") : new File("src\\test\\resources\\inputrc2"));

        if(Config.isOSPOSIXCompatible()) {  //TODO: must fix this for windows
            Config.parseInputrc(settings);

            assertEquals(new KeyOperation(new int[]{27,91,68}, Operation.MOVE_NEXT_CHAR),
                    settings.getOperationManager().findOperation(new int[]{27,91,68}));

            assertEquals(new KeyOperation(new int[]{27,91,66}, Operation.HISTORY_PREV),
                    settings.getOperationManager().findOperation(new int[]{27,91,66}));

            assertEquals(new KeyOperation(new int[]{27,10}, Operation.MOVE_PREV_CHAR),
                    settings.getOperationManager().findOperation(new int[]{27,10}));

            assertEquals(new KeyOperation(new int[]{1}, Operation.MOVE_NEXT_WORD),
                    settings.getOperationManager().findOperation(new int[]{1}));
        }
    }

    public void testParseProperties() throws IOException {
        System.setProperty("aesh.terminal", "org.jboss.aesh.terminal.TestTerminal");
        System.setProperty("aesh.editmode", "vi");
        System.setProperty("aesh.historypersistent", "false");
        System.setProperty("aesh.historydisabled", "true");
        System.setProperty("aesh.historysize", "42");
        System.setProperty("aesh.logging", "false");
        System.setProperty("aesh.disablecompletion", "true");

        Config.readRuntimeProperties(Settings.getInstance());

        assertEquals(Settings.getInstance().getTerminal().getClass().getName(), "org.jboss.aesh.terminal.TestTerminal");

        assertEquals(Settings.getInstance().getEditMode(), Mode.VI);

        assertEquals(Settings.getInstance().isHistoryPersistent(), false);
        assertEquals(Settings.getInstance().isHistoryDisabled(), true);
        assertEquals(Settings.getInstance().getHistorySize(), 42);
        assertEquals(Settings.getInstance().isLogging(), false);
        assertEquals(Settings.getInstance().isDisableCompletion(), true);

        System.setProperty("aesh.terminal", "");
        System.setProperty("aesh.editmode", "");
        System.setProperty("aesh.historypersistent", "");
        System.setProperty("aesh.historydisabled", "");
        System.setProperty("aesh.historysize", "");
        System.setProperty("aesh.logging", "");
        System.setProperty("aesh.disablecompletion", "");

        Settings.getInstance().resetToDefaults();
    }
}