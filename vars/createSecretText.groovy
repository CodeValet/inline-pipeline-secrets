#!/usr/bin/env groovy

import hudson.util.Secret

def call(String text) {
    return Secret.fromString(text)
}
