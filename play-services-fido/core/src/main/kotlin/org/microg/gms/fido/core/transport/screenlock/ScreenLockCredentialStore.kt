/*
 * SPDX-FileCopyrightText: 2022 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package org.microg.gms.fido.core.transport.screenlock

import android.content.Context
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION.SDK_INT
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import org.microg.gms.utils.toBase64
import java.security.*
import java.security.cert.Certificate
import java.security.spec.ECGenParameterSpec
import kotlin.random.Random

@RequiresApi(23)
class ScreenLockCredentialStore(val context: Context) {
    private val keyStore by lazy { KeyStore.getInstance("AndroidKeyStore").apply { load(null) } }

    private fun getAlias(rpId: String, keyId: ByteArray): String =
        "1." + keyId.toBase64(Base64.NO_PADDING, Base64.NO_WRAP) + "." + rpId

    private fun getPrivateKey(rpId: String, keyId: ByteArray) = keyStore.getKey(getAlias(rpId, keyId), null) as? PrivateKey

    @RequiresApi(23)
    fun createKey(rpId: String, challenge: ByteArray): ByteArray {
        var useStrongbox = false
        if (SDK_INT >= 28) useStrongbox = context.packageManager.hasSystemFeature(PackageManager.FEATURE_STRONGBOX_KEYSTORE)
        val keyId = Random.nextBytes(32)
        val identifier = getAlias(rpId, keyId)
        Log.d(TAG, "Creating key for $identifier")
        val generator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore")
        val builder = KeyGenParameterSpec.Builder(identifier, KeyProperties.PURPOSE_SIGN)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
            .setUserAuthenticationRequired(true)
        if (SDK_INT >= 28) builder.setIsStrongBoxBacked(useStrongbox)
        if (SDK_INT >= 24) builder.setAttestationChallenge(challenge)
        try {
            generator.initialize(builder.build())
            generator.generateKeyPair()
        } catch (e: ProviderException) {
            // If attestation does not work, the generateKeyPair method throws a ProviderException.
            // Try to generate a key again without attestation, and let the exception propagate
            // if it turns out that something else caused the error
            if (SDK_INT >= 24) builder.setAttestationChallenge(null)
            generator.initialize(builder.build())
            generator.generateKeyPair()
        }
        return keyId
    }

    fun getPublicKey(rpId: String, keyId: ByteArray): PublicKey? =
        keyStore.getCertificate(getAlias(rpId, keyId))?.publicKey

    fun getPublicKeys(rpId: String): Collection<Pair<String, PublicKey>> {
        val keys = ArrayList<Pair<String, PublicKey>>()
        for (alias in keyStore.aliases()) {
            if (alias.endsWith(".$rpId")) {
                val key = keyStore.getCertificate(alias).publicKey
                keys.add(Pair(alias, key))
            }
        }

        return keys
    }


    fun getCertificateChain(rpId: String, keyId: ByteArray): Array<Certificate> =
        keyStore.getCertificateChain(getAlias(rpId, keyId))

    fun getSignature(rpId: String, keyId: ByteArray): Signature? {
        try {
            val privateKey = getPrivateKey(rpId, keyId) ?: return null
            val signature = Signature.getInstance("SHA256withECDSA")
            signature.initSign(privateKey)
            return signature
        } catch (e: KeyPermanentlyInvalidatedException) {
            keyStore.deleteEntry(getAlias(rpId, keyId))
            throw e
        }
    }

    fun containsKey(rpId: String, keyId: ByteArray): Boolean = keyStore.containsAlias(getAlias(rpId, keyId))

    companion object {
        const val TAG = "FidoLockStore"
    }
}
