public class Product {
    String poNum;
    String item;
    String date;
    String containerNo;
    private String eta;
    private int pcs;

    public Product(String poNum, String item, String date, String containerNo, String eta, int pcs){
        this.poNum = poNum;
        this.item = item;
        this.date = date;
        this.containerNo = containerNo;
        this.eta = eta;
        this.pcs = pcs;
    }

    String getPoNum(){
        return poNum;
    }

    String getItem(){
        return item;
    }

    String getDate(){
        return date;
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
