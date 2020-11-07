public class Product {
    String poNum;
    String item;
    String containerNo;
    private String eta;
    private int pcs;
    public Product(){

    }

    public Product(String poNum, String item, String containerNo, String eta, int pcs){
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
    String getPoNum(){
        return poNum;
    }

    String getItem(){
        return item;
    }


    String getContainerNo(){
        return containerNo;
    }

    String getEta(){
        return eta;
    }

    int getPcs(){
        return  pcs;
    }
}
