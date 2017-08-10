#!/usr/bin/env groovy

import hudson.util.Secret

def call(String cipherText) {
    return Secret.decrypt(cipherText)
}
