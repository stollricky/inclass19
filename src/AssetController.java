

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final RestTemplate restTemplate;
    private final ConfigurationService configurationService;

    public AssetController(
            AssetRepository assetRepository,
            RestTemplate restTemplate,
            ConfigurationService configurationService
    ) {
        this.assetRepository = assetRepository;
        this.restTemplate = restTemplate;
        this.configurationService = configurationService;
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            setLicenseInfo(asset);
        }
        return assets;
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable("id") int id) {
        Asset asset = assetRepository.findById(id);
        setLicenseInfo(asset);
        return asset;
    }

    @PostMapping
    public Asset createAsset(@RequestBody AssetRequest request) {
        Asset asset = new Asset(request.getName(), request.getLicenseId());
        assetRepository.save(asset);
        setLicenseInfo(asset);
        return asset;
    }

    private void setLicenseInfo(Asset asset) {
        String licenseUrl = configurationService.getLicenseUrl();
        ResponseEntity<License> response = restTemplate.getForEntity(licenseUrl + "/licenses/" + asset.getLicenseId(), License.class);
        License license = response.getBody();
        asset.setLicenseType(license.getType());
        asset.setLicenseDescription(license.getDescription());
    }
}
