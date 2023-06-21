/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.report;

import static org.junit.Assert.assertEquals;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.scanner.core.passive.ExtractedValueContainer;
import de.rub.nds.scanner.core.passive.TrackableValue;
import de.rub.nds.tlsattacker.core.crypto.keys.CustomDhPublicKey;
import de.rub.nds.tlsscanner.core.passive.TrackableValueType;
import de.rub.nds.tlsscanner.serverscanner.afterprobe.DhValueAfterProbe;
import java.math.BigInteger;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * TestSet should check if DhValueAfterProbe detects insecure DH-Parameters as insecure ones and secure parameters as
 * secure ones
 */
public class DhValueAfterProbeTest {

    private static final Logger LOGGER = (Logger) LogManager.getLogger();

    private ServerReport report;
    private HashMap<TrackableValue, ExtractedValueContainer> cipherMap;
    private ExtractedValueContainer<CustomDhPublicKey> pubkeyContainer;
    private DhValueAfterProbe test;

    // initializes attributes
    @Before
    public void setup() {
        report = new ServerReport("sample", 443);
        cipherMap = new HashMap<>();
        pubkeyContainer = new ExtractedValueContainer<CustomDhPublicKey>(TrackableValueType.DHE_PUBLICKEY);
        test = new DhValueAfterProbe();

    }

    /**
     * Test if method analyze of class DhValueAfterProbe recognizes a secure public key as such
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void secureDhParamTestAnalyze() {
        BigInteger securePubkey, secureMod;
        securePubkey = new BigInteger("65537");
        // openssl command for dhparams: openssl dhparam -text -noout 1024
        secureMod = new BigInteger(
            "00e8cc972fc56fe640588194e455522facbe4b09d88f5070e" + "88f4b9b12acc85ee3d354f6fa85b81e46b557b0e0d75f71"
                + "43b266a6346962fba3184bd30ca3a94bebb4b23ae269325" + "dc15ac34b7bf38aa3dde5c6b2d9fe857237d3a7e5c7e9be"
                + "938b187cd9781de993970e73a3fbf79a049a6d804a487de" + "1013f71167cbf78aa65f3",
            16);

        // store pubKey into list
        pubkeyContainer.put(new CustomDhPublicKey(secureMod, new BigInteger("2"), securePubkey));

        analyseDhParams();

        assertEquals(TestResults.TRUE, test.getOnlyPrime());
        assertEquals(TestResults.TRUE, test.getOnlySafePrime());
        assertEquals(TestResults.FALSE, test.getUsesCommonDhPrimes());
        assertEquals(TestResults.COULD_NOT_TEST, test.getReuse());

    }

    /**
     * Tests if method analyze recognizes an insecure parameters as such
     */
    @Test
    public void insecureDhParamTestAnalyze() {
        // examples for insecure values
        BigInteger insecureKey = new BigInteger("12");
        BigInteger insecureMod = new BigInteger("18");

        pubkeyContainer.put(new CustomDhPublicKey(insecureMod, new BigInteger("2"), insecureKey));

        analyseDhParams();

        assertEquals(TestResults.FALSE, test.getOnlyPrime());
        assertEquals(TestResults.FALSE, test.getOnlySafePrime());
        assertEquals(TestResults.FALSE, test.getUsesCommonDhPrimes());
        assertEquals(TestResults.COULD_NOT_TEST, test.getReuse());

    }

    /**
     * Test if method analyze detects reused publickey
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void secureReusedDhPubkeyTestAnalyze() {
        BigInteger secureKey, secureMod;

        secureKey = new BigInteger("65537");
        secureMod = new BigInteger(
            "00e8a678364bb6f7d85d4b29ecfedab6d6caa88eb90c4ca1" + "5a43a3542cdd5c39ef42bbde1b4b9b5715ae14bdedd78d"
                + "6b5262f5ac9c2fdec09a612ef3aea969ce1327a6b5c9f3" + "ac052faafebbabc9c9679bd14e0a26114ff032c95d2ed7"
                + "3ed60cd64f497094bd4cb5839f9d7ad58fd4ccac343db5" + "81c4bf8032259bc1a3d7ee4d03",
            16);

        // reuse pubKey
        pubkeyContainer.put(new CustomDhPublicKey(secureMod, new BigInteger("2"), secureKey));
        pubkeyContainer.put(new CustomDhPublicKey(secureMod, new BigInteger("2"), secureKey));
        pubkeyContainer.put(new CustomDhPublicKey(secureMod, new BigInteger("2"), secureKey));

        analyseDhParams();

        assertEquals(TestResults.TRUE, test.getOnlyPrime());
        assertEquals(TestResults.TRUE, test.getOnlySafePrime());
        assertEquals(TestResults.FALSE, test.getUsesCommonDhPrimes());
        assertEquals(TestResults.TRUE, test.getReuse());

    }

    /**
     * Executes the analysis: determines how secure the Diffie-Hellmann parameters are
     */
    private void analyseDhParams() {
        cipherMap.put(TrackableValueType.DHE_PUBLICKEY, pubkeyContainer);

        report.setExtractedValueContainerList(cipherMap);
        test.analyze(report);

    }

}
