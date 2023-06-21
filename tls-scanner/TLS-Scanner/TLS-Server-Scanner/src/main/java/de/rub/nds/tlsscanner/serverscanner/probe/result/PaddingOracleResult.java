/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe.result;

import de.rub.nds.scanner.core.constants.TestResult;
import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsscanner.core.vector.statistics.InformationLeakTest;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.leak.PaddingOracleTestInfo;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaddingOracleResult extends ProbeResult<ServerReport> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final List<InformationLeakTest<PaddingOracleTestInfo>> resultList;

    private TestResult vulnerable;

    public PaddingOracleResult(TestResult result) {
        super(TlsProbeType.PADDING_ORACLE);
        this.vulnerable = result;
        resultList = new LinkedList<>();
    }

    public PaddingOracleResult(List<InformationLeakTest<PaddingOracleTestInfo>> resultList) {
        super(TlsProbeType.PADDING_ORACLE);
        this.resultList = resultList;
        if (this.resultList != null) {
            vulnerable = TestResults.FALSE;
            for (InformationLeakTest informationLeakTest : resultList) {
                if (informationLeakTest.isSignificantDistinctAnswers()) {
                    vulnerable = TestResults.TRUE;
                }
            }
        } else {
            vulnerable = TestResults.ERROR_DURING_TEST;
        }
    }

    @Override
    public void mergeData(ServerReport report) {
        report.setPaddingOracleTestResultList(resultList);
        report.putResult(TlsAnalyzedProperty.VULNERABLE_TO_PADDING_ORACLE, vulnerable);
    }

    public List<InformationLeakTest<PaddingOracleTestInfo>> getResultList() {
        return resultList;
    }

}
