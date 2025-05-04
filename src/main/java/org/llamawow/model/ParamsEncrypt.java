package org.llamawow.model;

import java.math.*;

/**
 * Clase para cifrar la informacion sencible lo requiere el emulador trinity core
 */

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

public class ParamsEncrypt {
    public final int N_length_bits;       // Longitud de N en bits
    public final BigInteger N;             // Número primo grande
    public final BigInteger g;             // Generador
    public final String hash;              // Función hash
    public final int identityMaxLength;    // Longitud máxima de identidad
    public final int passwordMaxLength;    // Longitud máxima de contraseña

    /**
     *
     * For more information about how to encrypt with SPR6 for TC
     * https://github.com/TrinityCore/TrinityCore/issues/25157
     *
     */
    public ParamsEncrypt(int N_length_bits, BigInteger N, BigInteger g, String hash, int identityMaxLength,
                         int passwordMaxLength) {
        this.N_length_bits = N_length_bits;
        this.N = N;
        this.g = g;
        this.hash = hash;
        this.identityMaxLength = identityMaxLength;
        this.passwordMaxLength = passwordMaxLength;
    }

    // Instancia estática de parámetros para TrinityCore
    public static final ParamsEncrypt trinitycore = new ParamsEncrypt(
            256,
            new BigInteger("894B645E89E1535BBDAD5B8B290650530801B18EBFBF5E8FAB3C82872A3E9BB7", 16),
            BigInteger.valueOf(7),
            "SHA-1",
            16,
            16
    );
}