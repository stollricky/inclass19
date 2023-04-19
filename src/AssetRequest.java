
public class AssetRequest {
    private String name;
    private int licenseId;

    public AssetRequest() {
    }

    public AssetRequest(String name, int licenseId) {
        this.name = name;
        this.licenseId = licenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }
}
