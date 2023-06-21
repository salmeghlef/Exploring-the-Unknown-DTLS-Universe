/**
 * TLS-Client-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.clientscanner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.rub.nds.scanner.core.report.AnsiColor;
import de.rub.nds.scanner.core.util.ConsoleLogger;
import de.rub.nds.tlsattacker.core.config.delegate.GeneralDelegate;
import de.rub.nds.tlsattacker.core.exceptions.ConfigurationException;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsscanner.clientscanner.config.ClientScannerConfig;
import de.rub.nds.tlsscanner.clientscanner.execution.TlsClientScanner;
import de.rub.nds.tlsscanner.clientscanner.report.ClientReport;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        ClientScannerConfig config = new ClientScannerConfig(new GeneralDelegate());
        JCommander commander = new JCommander(config);
        try {
            commander.parse(args);
            if (config.getGeneralDelegate().isHelp()) {
                commander.usage();
                return;
            } else if (config.getRunCommand() == null) {
                throw new ParameterException("No run command given. Use -run parameter to specify shell command.");
            }
            // Cmd was parsable
            try {

                TlsClientScanner scanner = new TlsClientScanner(config, (State state) -> {
                    try {
                        String command = config.getRunCommand();
                        command = command.replace("[port]", config.getServerDelegate().getPort().toString());
                        LOGGER.debug("Running start command: {}", command);
                        Runtime.getRuntime().exec(command.split(" "));
                    } catch (Exception E) {
                        LOGGER.error("Error during command execution", E);
                    }
                    return 0;
                });
                long time = System.currentTimeMillis();

                LOGGER.info("Performing Scan, this may take some time...");
                ClientReport report = scanner.scan();

                LOGGER.info("Scanned in: {}s\n", (System.currentTimeMillis() - time) / 1000);
                ConsoleLogger.CONSOLE.info("{}Scanned in: {}s\n{}", AnsiColor.RESET.getCode(),
                    ((System.currentTimeMillis() - time) / 1000),
                    report.getFullReport(config.getReportDetail(), !config.isNoColor()));
            } catch (ConfigurationException e) {
                LOGGER.error("Encountered a ConfigurationException aborting.", e);
            }
        } catch (ParameterException e) {
            LOGGER.error("Could not parse provided parameters", e);
            commander.usage();
        }
    }
}
