package Model;

public class Industries {

    private boolean automotive = false;

    private boolean finance = false;

    private boolean commerce = false;

    private boolean pharma_Helthcare = false;

    private boolean public_Sector = false;

    public Industries() {}

    public Industries(
            boolean automotive,
            boolean finance,
            boolean commerce,
            boolean pharma_Helthcare,
            boolean public_Sector) {
        this.automotive = automotive;
        this.finance = finance;
        this.commerce = commerce;
        this.pharma_Helthcare = pharma_Helthcare;
        this.public_Sector = public_Sector;
    }

    public boolean isAutomotive() {
        return automotive;
    }

    public void setAutomotive(boolean automotive) {
        this.automotive = automotive;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isCommerce() {
        return commerce;
    }

    public void setCommerce(boolean commerce) {
        this.commerce = commerce;
    }

    public boolean isPharma_Helthcare() {
        return pharma_Helthcare;
    }

    public void setPharma_Helthcare(boolean pharma_Helthcare) {
        this.pharma_Helthcare = pharma_Helthcare;
    }

    public boolean isPublic_Sector() {
        return public_Sector;
    }

    public void setPublic_Sector(boolean public_Sector) {
        this.public_Sector = public_Sector;
    }

    public String toString(){
        String res="";
        if(automotive)
            res += "automotive, ";

        if(finance)
            res += "finance, ";

        if(commerce)
            res += "commerce, ";

        if(pharma_Helthcare)
            res += "pharma_Helthcare, ";

        if(public_Sector)
            res += "public_Sector, ";

        return res.substring(0, res.length()-2);
    }
}
