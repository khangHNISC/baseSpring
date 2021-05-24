package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return Booked"
    request {
        method GET()
        url("/booked")
    }
    response {
        fixedDelayMilliseconds 100000
        status 200
        headers {
            header 'Content-Type': applicationJson()
        }
        body(
                id: 1,
                name: "Jane"
        )
    }
}