/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rub.nds.tlsscanner.serverscanner.report;

import de.rub.nds.scanner.core.constants.ScannerDetail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SiteReportPrinterTest {

    public SiteReportPrinterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFullReport method, of class SiteReportPrinter.
     */
    @Test
    public void testPrintEmptyReport() {
        ServerReport report = new ServerReport("somehost", 443);
        for (ScannerDetail detail : ScannerDetail.values()) {
            ServerReportPrinter printer =
                new ServerReportPrinter(report, detail, DefaultPrintingScheme.getDefaultPrintingScheme(true), true);
            printer.getFullReport();
        }
    }

}
