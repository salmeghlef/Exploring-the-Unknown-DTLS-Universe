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
import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;

public class DtlsBugsResult extends ProbeResult<ServerReport> {

    private TestResult isEarlyFinished;
    private TestResult isAcceptingUnencryptedFinished;

    public DtlsBugsResult(TestResult isAcceptingUnencryptedFinished, TestResult isEarlyFinished) {
        super(TlsProbeType.DTLS_COMMON_BUGS);
        this.isAcceptingUnencryptedFinished = isAcceptingUnencryptedFinished;
        this.isEarlyFinished = isEarlyFinished;
    }

    @Override
    protected void mergeData(ServerReport report) {
        report.putResult(TlsAnalyzedProperty.ACCEPTS_UNENCRYPTED_FINISHED, isAcceptingUnencryptedFinished);
        report.putResult(TlsAnalyzedProperty.HAS_EARLY_FINISHED_BUG, isEarlyFinished);
    }

}
