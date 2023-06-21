/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe;

import de.rub.nds.scanner.core.constants.TestResult;
import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.constants.ExtensionType;
import de.rub.nds.tlsattacker.core.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.core.constants.NamedGroup;
import de.rub.nds.tlsattacker.core.protocol.message.ServerHelloMessage;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.workflow.ParallelExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTraceUtil;
import de.rub.nds.tlsattacker.core.workflow.factory.WorkflowTraceType;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.probe.result.HelloRetryResult;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import de.rub.nds.tlsscanner.serverscanner.selector.ConfigSelector;
import java.util.LinkedList;

/**
 * Test the servers Hello Retry Request
 */
public class HelloRetryProbe extends TlsServerProbe<ConfigSelector, ServerReport, HelloRetryResult> {

    private TestResult sendsHelloRetryRequest = TestResults.FALSE;
    private TestResult issuesCookie = TestResults.FALSE;
    private NamedGroup serversChosenGroup = null;

    public HelloRetryProbe(ConfigSelector configSelector, ParallelExecutor parallelExecutor) {
        super(parallelExecutor, TlsProbeType.HELLO_RETRY, configSelector);
    }

    @Override
    public HelloRetryResult executeTest() {
        testHelloRetry();
        return new HelloRetryResult(sendsHelloRetryRequest, issuesCookie, serversChosenGroup);
    }

    @Override
    public boolean canBeExecuted(ServerReport report) {
        return report.isProbeAlreadyExecuted(TlsProbeType.PROTOCOL_VERSION)
            && report.getResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3) == TestResults.TRUE;
    }

    @Override
    public HelloRetryResult getCouldNotExecuteResult() {
        return new HelloRetryResult(TestResults.COULD_NOT_TEST, TestResults.COULD_NOT_TEST, serversChosenGroup);
    }

    @Override
    public void adjustConfig(ServerReport report) {
    }

    private void testHelloRetry() {
        Config tlsConfig = configSelector.getTls13BaseConfig();
        tlsConfig.setWorkflowTraceType(WorkflowTraceType.DYNAMIC_HELLO);
        // enforce HRR by sending empty key share
        tlsConfig.setDefaultClientKeyShareNamedGroups(new LinkedList<>());
        State state = new State(tlsConfig);
        executeState(state);
        if (WorkflowTraceUtil.didReceiveMessage(HandshakeMessageType.SERVER_HELLO, state.getWorkflowTrace())
            && ((ServerHelloMessage) WorkflowTraceUtil.getFirstReceivedMessage(HandshakeMessageType.SERVER_HELLO,
                state.getWorkflowTrace())).isTls13HelloRetryRequest()) {
            sendsHelloRetryRequest = TestResults.TRUE;
            serversChosenGroup = state.getTlsContext().getSelectedGroup();
            if (((ServerHelloMessage) WorkflowTraceUtil.getFirstReceivedMessage(HandshakeMessageType.SERVER_HELLO,
                state.getWorkflowTrace())).containsExtension(ExtensionType.COOKIE)) {
                issuesCookie = TestResults.TRUE;
            }
        }
    }

}
