/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.core.constants.ProtocolMessageType;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.protocol.message.AlertMessage;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.workflow.ParallelExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTraceUtil;
import de.rub.nds.tlsattacker.core.workflow.factory.WorkflowTraceType;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.core.probe.result.VersionSuiteListPair;
import de.rub.nds.tlsscanner.serverscanner.probe.result.CipherSuiteResult;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import de.rub.nds.tlsscanner.serverscanner.selector.ConfigSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CipherSuiteProbe extends TlsServerProbe<ConfigSelector, ServerReport, CipherSuiteResult> {

    private final List<ProtocolVersion> protocolVersions;

    public CipherSuiteProbe(ConfigSelector configSelector, ParallelExecutor parallelExecutor) {
        super(parallelExecutor, TlsProbeType.CIPHER_SUITE, configSelector);
        protocolVersions = new LinkedList<>();
    }

    @Override
    public CipherSuiteResult executeTest() {
        List<VersionSuiteListPair> pairLists = new LinkedList<>();
        for (ProtocolVersion version : protocolVersions) {
            LOGGER.debug("Testing:" + version.name());
            if (version.isTLS13()) {
                pairLists.add(new VersionSuiteListPair(version, getSupportedCipherSuites()));
            } else {
                List<CipherSuite> toTestList = new LinkedList<>();
                List<CipherSuite> versionSupportedSuites = new LinkedList<>();
                if (version == ProtocolVersion.SSL3) {
                    toTestList.addAll(CipherSuite.SSL3_SUPPORTED_CIPHERSUITES);
                    versionSupportedSuites = getSupportedCipherSuitesWithIntolerance(toTestList, version);
                } else {
                    toTestList.addAll(Arrays.asList(CipherSuite.values()));
                    toTestList.remove(CipherSuite.TLS_FALLBACK_SCSV);
                    toTestList.remove(CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV);
                    versionSupportedSuites = getSupportedCipherSuitesWithIntolerance(toTestList, version);
                    if (versionSupportedSuites.isEmpty()) {
                        versionSupportedSuites = getSupportedCipherSuitesWithIntolerance(version);
                    }
                }
                if (versionSupportedSuites.size() > 0) {
                    pairLists.add(new VersionSuiteListPair(version, versionSupportedSuites));
                }
            }
        }
        return new CipherSuiteResult(pairLists);
    }

    private List<CipherSuite> getSupportedCipherSuites() {
        CipherSuite selectedSuite = null;
        List<CipherSuite> toTestList = new LinkedList<>();
        List<CipherSuite> supportedSuits = new LinkedList<>();
        for (CipherSuite suite : CipherSuite.values()) {
            if (suite.isTLS13()) {
                toTestList.add(suite);
            }
        }
        do {
            selectedSuite = getSelectedCipherSuite(toTestList);

            if (selectedSuite != null) {
                if (!toTestList.contains(selectedSuite)) {
                    LOGGER.warn("Server chose a CipherSuite we did not propose!");
                    // TODO write to site report
                    break;
                }
                supportedSuits.add(selectedSuite);
                toTestList.remove(selectedSuite);
            }
        } while (selectedSuite != null && !toTestList.isEmpty());
        return supportedSuits;
    }

    private CipherSuite getSelectedCipherSuite(List<CipherSuite> toTestList) {
        Config tlsConfig = configSelector.getTls13BaseConfig();
        tlsConfig.setWorkflowTraceType(WorkflowTraceType.DYNAMIC_HELLO);
        tlsConfig.setDefaultClientSupportedCipherSuites(toTestList);
        configSelector.repairConfig(tlsConfig);
        State state = new State(tlsConfig);
        executeState(state);
        if (WorkflowTraceUtil.didReceiveMessage(HandshakeMessageType.SERVER_HELLO, state.getWorkflowTrace())) {
            return state.getTlsContext().getSelectedCipherSuite();
        } else if (WorkflowTraceUtil.didReceiveMessage(HandshakeMessageType.HELLO_RETRY_REQUEST,
            state.getWorkflowTrace())) {
            return state.getTlsContext().getSelectedCipherSuite();
        } else {
            LOGGER.debug("Did not receive ServerHello Message");
            LOGGER.debug(state.getWorkflowTrace().toString());
            return null;
        }
    }

    public List<CipherSuite> getSupportedCipherSuitesWithIntolerance(ProtocolVersion version) {
        return getSupportedCipherSuitesWithIntolerance(new ArrayList<>(CipherSuite.getImplemented()), version);
    }

    public List<CipherSuite> getSupportedCipherSuitesWithIntolerance(List<CipherSuite> toTestList,
        ProtocolVersion version) {
        List<CipherSuite> listWeSupport = new LinkedList<>(toTestList);
        List<CipherSuite> supported = new LinkedList<>();

        boolean supportsMore = false;
        do {
            Config config = configSelector.getBaseConfig();
            config.setWorkflowTraceType(WorkflowTraceType.DYNAMIC_HELLO);
            config.setDefaultClientSupportedCipherSuites(listWeSupport);
            config.setDefaultSelectedProtocolVersion(version);
            config.setHighestProtocolVersion(version);
            config.setEnforceSettings(true);
            configSelector.repairConfig(config);
            State state = new State(config);
            executeState(state);
            if (WorkflowTraceUtil.didReceiveMessage(HandshakeMessageType.SERVER_HELLO, state.getWorkflowTrace())) {
                if (state.getTlsContext().getSelectedProtocolVersion() != version) {
                    LOGGER.debug("Server does not support " + version);
                    return new LinkedList<>();
                }
                LOGGER.debug("Server chose " + state.getTlsContext().getSelectedCipherSuite().name());
                if (listWeSupport.contains(state.getTlsContext().getSelectedCipherSuite())) {
                    supportsMore = true;
                    supported.add(state.getTlsContext().getSelectedCipherSuite());
                    listWeSupport.remove(state.getTlsContext().getSelectedCipherSuite());
                } else {
                    supportsMore = false;
                    LOGGER.warn("Server chose not proposed cipher suite");
                }
            } else {
                supportsMore = false;
                LOGGER.debug("Server did not send ServerHello");
                LOGGER.debug(state.getWorkflowTrace().toString());
                if (state.getTlsContext().isReceivedFatalAlert()) {
                    LOGGER.debug("Received Fatal Alert");
//                    AlertMessage alert = (AlertMessage) WorkflowTraceUtil
//                        .getFirstReceivedMessage(ProtocolMessageType.ALERT, state.getWorkflowTrace());
//                    LOGGER.debug("Type:" + alert.toString());

                }
            }
        } while (supportsMore);
        return supported;
    }

    @Override
    public boolean canBeExecuted(ServerReport report) {
        return report.isProbeAlreadyExecuted(TlsProbeType.PROTOCOL_VERSION);
    }

    @Override
    public void adjustConfig(ServerReport report) {
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_0) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.DTLS10);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_DTLS_1_2) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.DTLS12);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_SSL_3) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.SSL3);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_0) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.TLS10);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_1) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.TLS11);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_2) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.TLS12);
        }
        if (report.getResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3) == TestResults.TRUE) {
            protocolVersions.add(ProtocolVersion.TLS13);
        }
    }

    @Override
    public CipherSuiteResult getCouldNotExecuteResult() {
        return new CipherSuiteResult(null);
    }
}
