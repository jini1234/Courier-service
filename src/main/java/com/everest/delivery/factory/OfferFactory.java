package com.everest.delivery.factory;

import com.everest.delivery.offer.Offer;
import com.everest.delivery.offer.OfferByWeight;
import com.everest.delivery.offer.OfferByWeightAndDistance;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OfferFactory {
    static Map<String, Offer> offerCache;

    public static Offer getOfferById(String offerId) {
        if (offerCache == null) {
            offerCache = new HashMap<>();
            populateOfferCache();
        }
        return offerCache.get(offerId);
    }

    private static void populateOfferCache() {
        parseOffersConfigList(getOffers("OffersByWeightAndDistanceConfig.yaml", OfferByWeightAndDistance.class));
        parseOffersConfigList(getOffers("OffersByWeightConfig.yaml", OfferByWeight.class));

    }

    private static List<Offer> getOffers(String configFileName, Class<? extends Offer> offerClass) {
        Yaml yaml = new Yaml(new Constructor(offerClass));
        InputStream inputStream = OfferFactory.class
                .getClassLoader()
                .getResourceAsStream(configFileName);
        return StreamSupport.stream(yaml.loadAll(inputStream).spliterator(), false)
                .filter(offerClass::isInstance)
                .map(offerClass::cast)
                .collect(Collectors.toList());
    }

    private static void parseOffersConfigList(List<Offer> offersConfigList) {
        for (Offer offer : offersConfigList) {
            offerCache.put(offer.getId(), offer);
        }
    }
}
