package contracts

import org.springframework.cloud.contract.spec.internal.HttpMethods

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method HttpMethods.HttpMethod.POST
        url '/accept'
        body(
                """{
              "from": "7861d56c-8b65-4ebc-be0b-0e25cf230079",
              "to": "ecc2094a-c0b9-4eb4-8f07-c019961aa3f0",
              "amount": {
                "value": 5000,
                "currency": "USD"
              }
            }"""
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                messsge:anyNonEmptyString(),
                transferStatus: anyOf('COMPLETED', 'FAILED')
        )
    }

}
