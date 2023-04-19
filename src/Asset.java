import java.beans.Transient;

public class Asset {

    private int id;
    private String name;
    private int licenseId;
    @Transient
    private String licenseType;
    @Transient
    private String licenseDescription;

    public Asset(String name, int licenseId) {
        this.name = name;
        this.licenseId = licenseId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLicenseId() {
        return licenseId;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseDescription() {
        return licenseDescription;
    }

    public void setLicenseDescription(String licenseDescription) {
        this.licenseDescription = licenseDescription;
    }
}
