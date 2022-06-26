# Courier-Service

### Example I/O

```
//input
100 5
PKG1 50 30 OFR001
PKG2 75 125 OFR008
PKG3 175 100 OFR003
PKG4 110 60 OFR002
PKG5 155 95 NA
2 70 200

//output
PKG2 0.0000 1475.0000 1.7857
PKG4 105.0000 1395.0000 0.8571
PKG3 0.0000 2350.0000 1.4286
PKG5 0.0000 2125.0000 4.2143
PKG1 0.0000 750.0000 4.0000
```

Note: Due to decimal precision, output may not match as specified in the question.

## Design

### Offer

Offer has a base abstract class which can be extended to implement different offer criteria. Offer
uses `boolean isApplicable(Pack pack)` to check for offer applicability on a Package.

Example `OfferByWeightAndDistance` uses both pack weight and distance to determine applicability, while `OfferByWeight`
uses only weight for applicability check.

All offers are defined in the config yaml file.

OfferFactory is used to read config and populate cache to get different Offer objects.

### Pack

Packages are defined in `Pack` abstract class which can be implemented. `NormalPack` is the pack which is used for this
question.

### CourierService

`CourierService` is an abstract class which can be overridden for implementing service for different Pack types. Service
calculates delivery cost and delivery time.