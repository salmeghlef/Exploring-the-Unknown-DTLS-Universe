/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe.result;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.List;

public class ProtocolVersionResult extends ProbeResult<ServerReport> {

    private final List<ProtocolVersion> supportedProtocolVersions;

    private final List<ProtocolVersion> unsupportedProtocolVersions;

    public ProtocolVersionResult(List<ProtocolVersion> supportedProtocolVersions,
        List<ProtocolVersion> unsupportedProtocolVersions) {
        super(TlsProbeType.PROTOCOL_VERSION);
        this.supportedProtocolVersions = supportedProtocolVersions;
        this.unsupportedProtocolVersions = unsupportedProtocolVersions;
    }

    @Override
    public void mergeData(ServerReport report) {
        if (supportedProtocolVersions != null) {
            report.setVersions(supportedProtocolVersions);

            for (ProtocolVersion version : supportedProtocolVersions) {
                if (version == ProtocolVersion.DTLS10_DRAFT) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0_DRAFT, TestResults.TRUE);
                }
                if (version == ProtocolVersion.DTLS10) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0, TestResults.TRUE);
                }
                if (version == ProtocolVersion.DTLS12) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_2, TestResults.TRUE);
                }
                if (version == ProtocolVersion.SSL2) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_2, TestResults.TRUE);
                }
                if (version == ProtocolVersion.SSL3) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_3, TestResults.TRUE);
                }
                if (version == ProtocolVersion.TLS10) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_0, TestResults.TRUE);
                }
                if (version == ProtocolVersion.TLS11) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_1, TestResults.TRUE);
                }
                if (version == ProtocolVersion.TLS12) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_2, TestResults.TRUE);
                }
                if (version == ProtocolVersion.TLS13) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, TestResults.TRUE);
                }
            }

            for (ProtocolVersion version : unsupportedProtocolVersions) {
                if (version == ProtocolVersion.DTLS10_DRAFT) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0_DRAFT, TestResults.FALSE);
                }
                if (version == ProtocolVersion.DTLS10) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0, TestResults.FALSE);
                }
                if (version == ProtocolVersion.DTLS12) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_2, TestResults.FALSE);
                }
                if (version == ProtocolVersion.SSL2) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_2, TestResults.FALSE);
                }
                if (version == ProtocolVersion.SSL3) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_3, TestResults.FALSE);
                }
                if (version == ProtocolVersion.TLS10) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_0, TestResults.FALSE);
                }
                if (version == ProtocolVersion.TLS11) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_1, TestResults.FALSE);
                }
                if (version == ProtocolVersion.TLS12) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_2, TestResults.FALSE);
                }
                if (version == ProtocolVersion.TLS13) {
                    report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, TestResults.FALSE);
                }
            }
        } else {
            report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0_DRAFT, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_2, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_2, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_SSL_3, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_0, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_1, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_2, TestResults.COULD_NOT_TEST);
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, TestResults.COULD_NOT_TEST);
        }
        report.setVersions(supportedProtocolVersions);
    }

}
