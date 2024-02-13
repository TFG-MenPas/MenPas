package com.uma.menpas.network

import org.ksoap2.serialization.SoapObject

class SoapBuilder {
    companion object {
        private const val NAMESPACE = "http://tempuri.org/"

        fun createSoapObject(operationName: String): SoapObject {
            return SoapObject(NAMESPACE, operationName)
        }
    }
}

