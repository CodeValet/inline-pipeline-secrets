#!/usr/bin/env groovy

def call() {
    def s = input(message: 'Text',
                       ok: 'Encrypt',
               parameters: [password(defaultValue: '',
                                      description: 'Text for encryption',
                                             name: 'Plain text')])
    echo "Use this encrypted value in your Jenkinsfile: ${s.encryptedValue}"
}
