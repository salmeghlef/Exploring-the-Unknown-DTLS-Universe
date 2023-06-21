/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import de.rub.nds.tlsscanner.core.vector.response.ResponseFingerprint;
import de.rub.nds.tlsattacker.core.constants.AlertDescription;
import de.rub.nds.tlsattacker.core.constants.AlertLevel;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.transport.socket.SocketState;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ResponseFingerprintDeserializer extends StdDeserializer<ResponseFingerprint> {

    public ResponseFingerprintDeserializer() {
        super(ResponseFingerprint.class);
    }

    @Override
    public ResponseFingerprint deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String socketState = node.get("socketState").asText();
        ArrayNode arrayNode = (ArrayNode) node.get("receivedMessages");
        List<ProtocolMessage> messageList = new LinkedList<>();
        for (int i = 0; i < arrayNode.size(); i++) {
            String compactString = arrayNode.get(i).asText();
            if (compactString.startsWith("Alert")) {
                compactString = compactString.replace("Alert", "");
                compactString = compactString.replace(")", "");
                compactString = compactString.replace("(", "");
                String[] split = compactString.split(",");
                AlertLevel alertLevel = AlertLevel.valueOf(split[0]);
                AlertDescription alertDescription = AlertDescription.valueOf(split[1]);
                AlertMessage alertMessage = new AlertMessage();
                alertMessage.setConfig(alertLevel, alertDescription);
                alertMessage.setDescription(alertDescription.getValue());
                alertMessage.setLevel(alertLevel.getValue());
                messageList.add(alertMessage);
            } else if (compactString.equals("UNKNOWN_MESSAGE")) {
                messageList.add(new UnknownMessage());
            } else if (compactString.equals("SERVER_HELLO")) {
                messageList.add(new ServerHelloMessage());
            } else if (compactString.equals("CHANGE_CIPHER_SPEC")) {
                messageList.add(new ChangeCipherSpecMessage());
            } else if (compactString.equals("APPLICATION")) {
                messageList.add(new ApplicationMessage());
            }
        }
        return new ResponseFingerprint(messageList, null, SocketState.valueOf(socketState));
    }
}
