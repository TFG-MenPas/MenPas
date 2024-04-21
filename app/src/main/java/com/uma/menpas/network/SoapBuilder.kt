package com.uma.menpas.network

import org.ksoap2.serialization.SoapObject

class SoapBuilder {
    companion object {
        private const val NAMESPACE = "http://tempuri.org/"

        fun createSoapObject(operationName: String): SoapObject {
            return SoapObject(NAMESPACE, operationName)
        }

        fun createSoapObject(namespace: String, operationName: String): SoapObject {
            return SoapObject(namespace, operationName)
        }

        fun soapObjectABoolean(result: SoapObject): Boolean {
            return result.getPropertyAsString(0).toBoolean()
        }
    }
}

