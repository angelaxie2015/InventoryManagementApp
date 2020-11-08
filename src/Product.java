public class Product {
    String poNum;
    String item;
    String containerNo;
    private String eta;
    private double pcs;
    public Product(){

    }

    public Product(String poNum, String item, String containerNo, String eta, double pcs){
        this.poNum = poNum;
        this.item = item;
        this.containerNo = containerNo;
        this.eta = eta;
        this.pcs = pcs;
    }

    public void setPoNum(String poNum){
        this.poNum = poNum;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setEta(String eta){
        this.eta = eta;
    }

    public void setContainerNo(String containerNo){

      this.containerNo = containerNo;
    }

    public void setPCs(int pcs){
        this.pcs = pcs;
    }
    public String getPoNum(){
        return poNum;
    }

    public String getItem(){
        return item;
    }


    public String getContainerNo(){
        return containerNo;
    }

    public String getEta(){
        return eta;
    }

    public double getPcs(){
        return  pcs;
    }
}
