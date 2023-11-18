import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PitchData {
    private Integer episode;
    private Integer season;
    private  Integer picht;
    private String projectName;
    private String category;
    private String description;
    private String entrepeneurGender;
    private  List<String> entrepeneurNames;
    private String website;
    private Double askedValue;
    private Boolean deal;
    private Double dealValue;
    private Double percentageOfProject;
    private Integer numberOfSharksInDeal;
    private Double percentageOfCompanyPerShark;
    private Double investmentAmountPerShark;
    private Set<String> sharks;

    public PitchData() {
    }
    //Use builder?
    public PitchData(Integer episode,
                     Integer season,
                     Integer picht,
                     String projectName,
                     String category,
                     String description,
                     String entrepeneurGender,
                     List<String> entrepeneurNames,
                     String website,
                     Double askedValue,
                     Boolean deal,
                     Double dealValue,
                     Double percentageOfProject,
                     Integer numberOfSharksInDeal,
                     Double percentageOfCompanyPerShark,
                     Double investmentAmountPerShark, Set<String> sharks ) {
        this.episode = episode;
        this.season = season;
        this.picht = picht;
        this.projectName = projectName;
        this.category = category;
        this.description = description;
        this.entrepeneurGender = entrepeneurGender;
        this.entrepeneurNames = entrepeneurNames;
        this.website = website;
        this.askedValue = askedValue;
        this.deal = deal;
        this.dealValue = dealValue;
        this.percentageOfProject = percentageOfProject;
        this.numberOfSharksInDeal = numberOfSharksInDeal;
        this.percentageOfCompanyPerShark = percentageOfCompanyPerShark;
        this.investmentAmountPerShark = investmentAmountPerShark;
        this.sharks = sharks;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getPicht() {
        return picht;
    }

    public void setPicht(Integer picht) {
        this.picht = picht;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntrepeneurGender() {
        return entrepeneurGender;
    }

    public void setEntrepeneurGender(String entrepeneurGender) {
        this.entrepeneurGender = entrepeneurGender;
    }

    public List<String> getEntrepeneurNames() {
        return entrepeneurNames;
    }

    public void setEntrepeneurNames(List<String> entrepeneurNames) {
        this.entrepeneurNames = entrepeneurNames;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getAskedValue() {
        return askedValue;
    }

    public void setAskedValue(Double askedValue) {
        this.askedValue = askedValue;
    }

    public Boolean getDeal() {
        return deal;
    }

    public void setDeal(Boolean deal) {
        this.deal = deal;
    }

    public Double getDealValue() {
        return dealValue;
    }

    public void setDealValue(Double dealValue) {
        this.dealValue = dealValue;
    }

    public Double getPercentageOfProject() {
        return percentageOfProject;
    }

    public void setPercentageOfProject(Double percentageOfProject) {
        this.percentageOfProject = percentageOfProject;
    }

    public Integer getNumberOfSharksInDeal() {
        return numberOfSharksInDeal;
    }

    public void setNumberOfSharksInDeal(Integer numberOfSharksInDeal) {
        this.numberOfSharksInDeal = numberOfSharksInDeal;
    }

    public Double getPercentageOfCompanyPerShark() {
        return percentageOfCompanyPerShark;
    }

    public void setPercentageOfCompanyPerShark(Double percentageOfCompanyPerShark) {
        this.percentageOfCompanyPerShark = percentageOfCompanyPerShark;
    }

    public Double getInvestmentAmountPerShark() {
        return investmentAmountPerShark;
    }

    public void setInvestmentAmountPerShark(Double investmentAmountPerShark) {
        this.investmentAmountPerShark = investmentAmountPerShark;
    }

    public Set<String> getSharks() {
        return sharks;
    }

    public void setSharks(Set<String> sharks) {
        this.sharks = sharks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchData pitchData = (PitchData) o;
        return Objects.equals(getPicht(), pitchData.getPicht());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPicht());
    }

    @Override
    public String toString() {
        return "Episode=" + episode +
                ", season=" + season +
                ", picht=" + picht +
                ", projectName='" + projectName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", entrepeneurGender='" + entrepeneurGender + '\'' +
                ", entrepeneurNames=" + entrepeneurNames +
                ", website='" + website + '\'' +
                ", askedValue=" + askedValue +
                ", deal=" + deal +
                ", dealValue=" + dealValue +
                ", percentageOfProject=" + percentageOfProject +
                ", numberOfSharksInDeal=" + numberOfSharksInDeal +
                ", percentageOfCompanyPerShark=" + percentageOfCompanyPerShark +
                ", investmentAmountPerShark=" + investmentAmountPerShark +
                ", sharks=";
    }
}
