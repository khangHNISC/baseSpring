package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all Reservations"
    request {
        method GET()
        url("/reservations")
    }
    response {
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