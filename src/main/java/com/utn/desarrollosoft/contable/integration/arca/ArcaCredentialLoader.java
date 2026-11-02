package com.utn.desarrollosoft.contable.integration.arca;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ArcaCredentialLoader {
    // Método para leer la clave privada desde el archivo .key
    public static PrivateKey cargarClavePrivada(String rutaKey) throws Exception {
        String content = new String(Files.readAllBytes(Path.of(rutaKey)));
        content = content.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(content);
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
    }

    // Método para leer el certificado desde el archivo .crt
    public static X509Certificate cargarCertificado(String rutaCert) throws Exception {
        try (var fis = Files.newInputStream(Path.of(rutaCert))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(fis);
        }
    }
}
