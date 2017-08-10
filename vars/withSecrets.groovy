#!/usr/bin/env groovy
import hudson.util.Secret

def call(Map ciphers, Closure body) {
    List<Map> cipherPairs = []
    /* https://issues.jenkins-ci.org/browse/JENKINS-27392 */
    List cipherEnv = []

    body.resolveStrategy = Closure.DELEGATE_FIRST

    ciphers.each { String key, String cipherText ->
        String plainText = Secret.decrypt(cipherText).plainText
        cipherEnv.add("${key}=${plainText}")
        cipherPairs.add([var: key,
                    password: plainText])
    }

    try {
        wrap([$class: 'MaskPasswordsBuildWrapper',
            varPasswordPairs: cipherPairs]) {
            withEnv(cipherEnv) { body.call() }
        }

    }
    catch (java.lang.IllegalArgumentException e) {
        error 'Cannot use withSecret() without installing the Mask Passwords plugin'
    }
}
