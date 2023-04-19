package edu.iu.c322.assetmanagement.assetservice.client;

import edu.iu.c322.assetmanagement.assetservice.model.Asset;
import edu.iu.c322.assetmanagement.assetservice.model.License;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AssetClient {
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public AssetClient(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    public List<Asset> getAllAssets() {
        ResponseEntity<Asset[]> restExchange =
                restTemplate.exchange(
                        "http://localhost:8080/assets",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        Asset[].class);
        return Arrays.asList(restExchange.getBody());
    }

    public Optional<Asset> getAssetById(int id) {
        ResponseEntity<Asset> restExchange =
                restTemplate.exchange(
                        "http://localhost:8080/assets/{id}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        Asset.class,
                        id);

        Asset asset = restExchange.getBody();

        if (asset == null) {
            return Optional.empty();
        }

        License license = restTemplate.getForObject(
                "http://localhost:8081/licenses/{id}",
                License.class,
                asset.getLicenseId());

        asset.setLicenseType(license.getType());
        asset.setLicenseDescription(license.getDescription());

        return Optional.of(asset);
    }

    public Asset createAsset(Asset asset) {
        return restTemplate.postForObject(
                "http://localhost:8080/assets",
                asset,
                Asset.class);
    }
}
